package uk.co.strangeskies.extengine.entity.scheduling.terminating.schedulers;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.extengine.entity.scheduling.terminating.TerminatingScheduler;

public class CriticalPathScheduler implements TerminatingScheduler {
	@Override
	public boolean process(ScheduleProcessingContext processingContext) {
		for (BehaviourComponent behaviour : processingContext.getBehaviours()) {
			processingContext.processBehaviour(behaviour);
		}

		return true;
	}
}
