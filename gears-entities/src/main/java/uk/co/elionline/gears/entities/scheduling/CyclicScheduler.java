package uk.co.elionline.gears.entities.scheduling;

public class CyclicScheduler implements Scheduler {
	private boolean stopped;

	private final Scheduler scheduler;

	public CyclicScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
		stopped = true;
	}

	@Override
	public final boolean process(ScheduleProcessingContext processingContext) {
		boolean wasStopped = setStopped(false);
		if (wasStopped == false) {
			return false;
		}
		do {
			processCycle(processingContext);
		} while (!isStopped());
		return true;
	}

	@Override
	public final boolean stopProcessing() {
		return !setStopped(true);
	}

	protected final synchronized boolean isStopped() {
		return stopped;
	}

	protected final synchronized boolean setStopped(boolean stopped) {
		boolean wasStopped = this.stopped;
		this.stopped = stopped;
		return wasStopped;
	}

	protected void processCycle(ScheduleProcessingContext processingContext) {
		scheduler.process(processingContext);
	}
}
