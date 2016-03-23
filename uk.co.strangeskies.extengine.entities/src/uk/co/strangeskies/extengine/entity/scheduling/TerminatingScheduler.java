package uk.co.strangeskies.extengine.entity.scheduling;

public interface TerminatingScheduler extends Scheduler {
	/**
	 * Run the Scheduler within the given context, unless the Scheduler is already
	 * running. Blocks until the Scheduler is complete.
	 * 
	 * @param processingContext
	 * @return True if the Scheduler was started, false otherwise.
	 */
	@Override
	public abstract boolean process(ScheduleProcessingContext processingContext);

	@Override
	default boolean stopProcessing() {
		return true;
	}
}
