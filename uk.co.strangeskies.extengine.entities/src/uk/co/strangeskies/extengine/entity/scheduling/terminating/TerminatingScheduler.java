package uk.co.strangeskies.extengine.entity.scheduling.terminating;

import uk.co.strangeskies.extengine.entity.scheduling.ScheduleProcessingContext;

public interface TerminatingScheduler {
	/**
	 * Run the Scheduler within the given context, unless the Scheduler is already
	 * running. Blocks until the Scheduler is complete.
	 * 
	 * @param processingContext
	 * @return True if the Scheduler was started, false otherwise.
	 */
	public abstract boolean process(ScheduleProcessingContext processingContext);
}
