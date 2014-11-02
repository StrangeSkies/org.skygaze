package uk.co.strangeskies.gears.entity.scheduling.terminating.schedulers;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.gears.entity.scheduling.terminating.TerminatingScheduler;

public class CriticalPathScheduler implements TerminatingScheduler {
	@Override
	public boolean process(ScheduleProcessingContext processingContext) {
		for (BehaviourComponent behaviour : processingContext.getBehaviours()) {
			processingContext.processBehaviour(behaviour);
		}

		return true;
	}
}
