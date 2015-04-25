package uk.co.strangeskies.extengine.entity.scheduling;

public interface Scheduler {
	/**
	 * Run the Scheduler within the given context, unless the Scheduler is already
	 * running. Blocks until the Scheduler is complete.
	 * 
	 * @param processingContext
	 * @return True if the Scheduler was started, false otherwise.
	 */
	public abstract boolean process(ScheduleProcessingContext processingContext);

	/**
	 * This stop the Scheduler, if running.
	 * 
	 * @return True if the Scheduler was running, false otherwise.
	 */
	public abstract boolean stopProcessing();
}
