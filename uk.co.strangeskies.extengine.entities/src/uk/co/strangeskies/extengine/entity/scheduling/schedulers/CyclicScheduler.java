package uk.co.strangeskies.extengine.entity.scheduling.schedulers;

import uk.co.strangeskies.extengine.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;
import uk.co.strangeskies.extengine.entity.scheduling.TerminatingScheduler;

public class CyclicScheduler implements Scheduler {
	private boolean stopped;

	private final TerminatingScheduler scheduler;

	public CyclicScheduler(TerminatingScheduler scheduler) {
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

	public TerminatingScheduler getCycle() {
		return scheduler;
	}
}
