package uk.co.elionline.gears.entity.scheduling.terminating.schedulers;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.scheduling.ScheduleProcessingContext;
import uk.co.elionline.gears.entity.scheduling.Scheduler;

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
