package uk.co.elionline.gears.entities.scheduling;

import java.util.Set;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.management.EntityManager;

public interface ScheduleProcessingContext {
	public abstract EntityManager getEntityManager();

	public abstract void processBehaviour(BehaviourComponent behaviour)
			throws InterruptedException;

	public Set<BehaviourComponent> getBehaviours();
}
