package uk.co.strangeskies.extengine.entity.scheduling.schedulers;

import uk.co.strangeskies.extengine.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.extengine.entity.scheduling.terminating.TerminatingScheduler;

public class PeriodicScheduler extends CyclicScheduler {
	private long cycleDurationNanoseconds;
	private long processingStartTimeNanoseconds;

	public PeriodicScheduler(TerminatingScheduler scheduler) {
		super(scheduler);
	}

	public void setPeriodFrequency(double frequency) {
		cycleDurationNanoseconds = (long) (1000000000 / frequency);
	}

	public void setPeriodLengthMilliseconds(double milliseconds) {
		cycleDurationNanoseconds = (long) (milliseconds * 1000000);
	}

	public void setPeriodLengthNanoseconds(long nanoseconds) {
		cycleDurationNanoseconds = nanoseconds;
	}

	@Override
	protected final void processCycle(ScheduleProcessingContext processingContext) {
		super.processCycle(processingContext);

		if (!isStopped()) {
			long processingDurationNanoseconds = System.nanoTime()
					- processingStartTimeNanoseconds;

			if (processingDurationNanoseconds < cycleDurationNanoseconds) {
				processingStartTimeNanoseconds += cycleDurationNanoseconds;
				waitUntil(processingStartTimeNanoseconds);
			} else {
				processingStartTimeNanoseconds = System.nanoTime();
			}
		}
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
}
