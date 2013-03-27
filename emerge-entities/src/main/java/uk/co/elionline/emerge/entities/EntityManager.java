package uk.co.elionline.emerge.entities;

import java.util.Collection;

import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;
import uk.co.elionline.emerge.entities.behaviour.BehaviourManager;
import uk.co.elionline.emerge.entities.processing.EntityProcessor;
import uk.co.elionline.emerge.entities.state.StateManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface EntityManager extends LockedEntityManager {
	@Override
	public StateManager getStateManager();

	@Override
	public BehaviourManager getBehaviourManager();

	public boolean startProcessing();

	public boolean startProcessing(Collection<EntityProcessor> processors);

	public void processBehaviour(BehaviourComponent behaviour)
			throws InterruptedException;
}
