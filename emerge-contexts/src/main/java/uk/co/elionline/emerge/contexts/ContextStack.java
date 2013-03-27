package uk.co.elionline.emerge.contexts;

import java.util.Stack;

import uk.co.elionline.emerge.input.BasicInputController;
import uk.co.elionline.emerge.mathematics.MovingAverage;
import uk.co.elionline.emerge.rendering.Renderer2D;

/**
 * an object to manage Context objects on a stack
 * 
 * @author eli
 * 
 */
public class ContextStack {
	private final Stack<Context> contextStack;
	private final Renderer2D renderer;
	private final BasicInputController inputController;
	private final RenderingRunner renderingRunner;
	private long logicCycleStartTime;
	private long lastBufferEndTime;

	private static final int movingAverageSize = 15;
	private final MovingAverage bufferDurationMovingAverage;
	private final MovingAverage logicEndTimeOffsetMovingAverage;

	// proportion of logic cycle duration that the buffer will try to start
	// earlier by each cycle
	private static final double bufferStartTimeCreep = 0.01;

	public ContextStack(Renderer2D renderer, BasicInputController inputController) {
		this.renderer = renderer;
		this.inputController = inputController;

		bufferDurationMovingAverage = new MovingAverage(movingAverageSize);
		logicEndTimeOffsetMovingAverage = new MovingAverage(movingAverageSize);

		contextStack = new Stack<Context>();

		renderingRunner = new RenderingRunner();
		renderingRunner.suspend();
		new Thread(renderingRunner).start();
	}

	/**
	 * push a Context onto the top of the stack
	 * 
	 * @param push
	 * @return
	 */
	boolean push(Context push) {
		// if the context being pushed doesn't want to be added, then fail
		if (!push.pushOnto(this)) {
			return false;
		}

		// the context has accepted the push so pause the current context...
		if (!contextStack.isEmpty()) {
			contextStack.peek().pause();
		}
		// ...push, start and run the new context until it exits...
		contextStack.push(push);

		if (push.start()) {
			// buffer the input controller to prepare for the first frame
			inputController.buffer();

			long bufferStartTimeOffset = 0;
			bufferDurationMovingAverage.clear();
			logicEndTimeOffsetMovingAverage.clear();

			// initialise logic start time
			logicCycleStartTime = System.nanoTime();
			// logic loop
			while (push.logic()) {
				// get the logic cycle duration time requested by the currently
				// executing context in nanoseconds
				long cycleDuration = 1000000000 / push.getLogicFrequency();
				long logicEndTimeOffset = System.nanoTime() - logicCycleStartTime;
				logicEndTimeOffsetMovingAverage.addValue(logicEndTimeOffset);
				if (logicEndTimeOffset < cycleDuration) { // if the logic was processed
																									// within the requested time
																									// constraints then figure out
																									// the best time to buffer and
																									// then wait until the next
																									// cycle
					/*
					 * This sleep period is split into two halves, one before the buffer
					 * and one after. The one before the buffer tries to be as low as
					 * possible, tending towards 0, but will not be so low that the buffer
					 * occurs significantly sooner than one step after the last buffer
					 * occurred.
					 */

					long startBufferingEarlierBy = (long) (cycleDuration * bufferStartTimeCreep);
					if (startBufferingEarlierBy == 0) {
						startBufferingEarlierBy = 1;
					}
					bufferStartTimeOffset -= startBufferingEarlierBy;
					// don't start the buffering so early that it is likely that we will
					// not be ready to buffer this early next frame
					long expectedLogicEndTimeOffsetUpperBound = (long) (logicEndTimeOffsetMovingAverage
							.getAverage() + logicEndTimeOffsetMovingAverage
							.getMeanAbsoluteDeviation() * 2);
					if (bufferStartTimeOffset < expectedLogicEndTimeOffsetUpperBound) {
						bufferStartTimeOffset = expectedLogicEndTimeOffsetUpperBound;
					}
					// don't start the buffering so late that it will last until after the
					// cycle should be finished
					long expectedBufferDurationUpperBound = (long) (bufferDurationMovingAverage
							.getAverage() + bufferDurationMovingAverage
							.getMeanAbsoluteDeviation() * 2);
					if (bufferStartTimeOffset > (cycleDuration - expectedBufferDurationUpperBound)) {
						bufferStartTimeOffset = cycleDuration
								- expectedBufferDurationUpperBound;
					}
					if (bufferStartTimeOffset > logicEndTimeOffset) {
						// wait until 'buffer start time offset' after 'logic cycle start
						// time'
						waitUntil(logicCycleStartTime + bufferStartTimeOffset);
					} else {
						// we are supposed to have buffered already, so just do everything
						// as quickly as possible and set the buffer start time to be later
						// next frame to accommodate
						bufferStartTimeOffset = logicEndTimeOffset;
					}

					// buffer the logic state for rendering
					buffer(push);

					// now wait until the end of the cycle if necessary
					logicCycleStartTime += cycleDuration;
					waitUntil(logicCycleStartTime);
				} else { // if the logic was not processed within the requested time
									// constraints then just buffer as soon as possible and
									// continue
					bufferStartTimeOffset = cycleDuration;

					// buffer the logic state for rendering
					buffer(push);

					logicCycleStartTime = System.nanoTime();
				}

				// if we are not rendering (i.e. just been pushed / returned from
				// previous push) then start rendering now
				if (!renderingRunner.isRunning()) {
					renderingRunner.setRenderingContext(push);
					renderingRunner.resume();
				}

				// buffer the input controller to prepare for the next frame
				inputController.buffer();
			}

			// if we started rendering then stop
			if (renderingRunner.isRunning()) {
				renderingRunner.stop();
				renderingRunner.setRenderingContext(null);
			}
			push.stop();
		}

		// ...then pop it off to resume the last context
		contextStack.pop();
		if (contextStack.isEmpty()) {
			renderingRunner.suspend();
			renderingRunner.setRenderingContext(null);
		} else {
			contextStack.peek().resume();
		}

		// when we return we will drop back into the logic() method of the context
		// that pushed this one.
		return true;
	}

	private void waitUntil(long nanoTime) {
		long delay = nanoTime - System.nanoTime();
		while (delay > 0) {
			try {
				Thread.sleep(delay / 1000000);
			} catch (InterruptedException e) {
			}
			delay = nanoTime - System.nanoTime();
		}
	}

	synchronized private void buffer(Context context) {
		// buffer the current context for rendering and record time of
		// buffer, as well as returning time taken to buffer
		long bufferDuration = System.nanoTime();
		context.buffer();
		lastBufferEndTime = System.nanoTime();
		bufferDuration = lastBufferEndTime - bufferDuration;

		bufferDurationMovingAverage.addValue(bufferDuration);
	}

	synchronized private long getElapsedTime() {
		return System.nanoTime() - lastBufferEndTime;
	}

	public Renderer2D getRenderer() {
		return renderer;
	}

	public BasicInputController getInputController() {
		return inputController;
	}

	protected class RenderingRunner implements Runnable {
		private Context renderingContext;
		private boolean isRunning;
		private boolean isStopped;

		public RenderingRunner() {
			isRunning = true;
			isStopped = false;
		}

		synchronized public void suspend() {
			isRunning = false;
		}

		synchronized public void resume() {
			isRunning = true;
			notifyAll();
		}

		synchronized public void stop() {
			isStopped = true;
			notifyAll();
		}

		synchronized public boolean isRunning() {
			return isRunning;
		}

		synchronized public void setRenderingContext(Context renderingContext) {
			this.renderingContext = renderingContext;
		}

		synchronized private Context getRenderingContext() {
			return renderingContext;
		}

		@Override
		public void run() {
			Context renderingContext = getRenderingContext();

			while (!isStopped) {
				if (!waitOnSuspend()) {
					double delta = ((double) getElapsedTime()) / 1000000000
							* renderingContext.getLogicFrequency();
					delta = Math.max(delta, 0);
					delta = Math.min(delta, 1);
					renderingContext.render(delta);
				}
				renderingContext = getRenderingContext();
			}
		}

		synchronized private boolean waitOnSuspend() {
			if (isRunning) {
				return false;
			}

			try {
				wait();
			} catch (InterruptedException e) {
			}
			return true;
		}
	}
}
