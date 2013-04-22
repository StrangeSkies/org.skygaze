package uk.co.elionline.gears.entities.management;

import java.util.Collection;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.processing.EntityProcessor;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface EntityManager extends EntityManagerProcessingContext {
	@Override
	public EntityStateManager getStateManager();

	@Override
	public EntityBehaviourManager getBehaviourManager();

	public boolean startProcessing();

	public boolean startProcessing(Collection<EntityProcessor> processors);

	public void processBehaviour(BehaviourComponent behaviour)
			throws InterruptedException;

	public void addEntityManagerListener(
			EntityManagerListener entityManagerListener);

	public void removeEntityManagerListener(
			EntityManagerListener entityManagerListener);
}
