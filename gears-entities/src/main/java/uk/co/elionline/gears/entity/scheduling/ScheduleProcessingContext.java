package uk.co.elionline.gears.entity.scheduling;

import java.util.Set;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;

public interface ScheduleProcessingContext {
	public abstract void processBehaviour(BehaviourComponent behaviour)
			throws InterruptedException;

	public Set<BehaviourComponent> getBehaviours();
}
