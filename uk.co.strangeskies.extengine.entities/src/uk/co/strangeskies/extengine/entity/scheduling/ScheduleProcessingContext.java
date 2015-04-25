package uk.co.strangeskies.extengine.entity.scheduling;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;

public interface ScheduleProcessingContext {
	public abstract void processBehaviour(BehaviourComponent behaviour);

	public Set<BehaviourComponent> getBehaviours();
}
