package uk.co.elionline.gears.entities;

import java.util.Collection;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourManager;
import uk.co.elionline.gears.entities.processing.EntityProcessor;
import uk.co.elionline.gears.entities.state.StateManager;

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
