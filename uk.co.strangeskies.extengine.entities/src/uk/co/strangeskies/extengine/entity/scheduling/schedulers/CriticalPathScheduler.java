package uk.co.strangeskies.extengine.entity.scheduling.schedulers;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.extengine.entity.scheduling.TerminatingScheduler;

public class CriticalPathScheduler implements TerminatingScheduler {
	private final String name;

	public CriticalPathScheduler(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean process(ScheduleProcessingContext processingContext) {
		for (BehaviourComponent behaviour : processingContext.getBehaviours()) {
			processingContext.processBehaviour(behaviour);
		}

		return true;
	}
}
