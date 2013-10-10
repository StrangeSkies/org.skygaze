package uk.co.strangeskies.gears.entity.scheduling.terminating.schedulers;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.gears.entity.scheduling.Scheduler;

public class CriticalPathScheduler implements Scheduler {
	@Override
	public boolean process(ScheduleProcessingContext processingContext) {
		for (BehaviourComponent behaviour : processingContext.getBehaviours()) {
			boolean done = false;
			do {
				try {
					processingContext.processBehaviour(behaviour);
					done = true;
				} catch (InterruptedException e) {
				}
			} while (!done);
		}

		return true;
	}

	@Override
	public boolean stopProcessing() {
		return false;
	}
}