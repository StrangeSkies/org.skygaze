package uk.co.elionline.gears.entities.scheduling.terminating;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.scheduling.ScheduleProcessingContext;
import uk.co.elionline.gears.entities.scheduling.Scheduler;

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
