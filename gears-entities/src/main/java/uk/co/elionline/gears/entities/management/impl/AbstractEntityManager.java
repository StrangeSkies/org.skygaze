package uk.co.elionline.gears.entities.management.impl;

import java.util.UUID;

import uk.co.elionline.gears.entities.management.EntityBehaviourManager;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.management.EntityStateManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public abstract class AbstractEntityManager implements EntityManager {
	private final EntityStateManager stateManager;
	private final EntityBehaviourManager behaviourManager;

	public AbstractEntityManager(EntityStateManager stateManager,
			EntityBehaviourManager behaviourManager) {
		this.stateManager = stateManager;
		this.behaviourManager = behaviourManager;
	}

	@Override
	public EntityStateManager getStateManager() {
		return stateManager;
	}

	@Override
	public EntityBehaviourManager getBehaviourManager() {
		return behaviourManager;
	}

	/**
	 * Create an entity to be managed by this class.
	 */
	@Override
	public final UUID create() {
		UUID identifier = UUID.randomUUID();

		add(identifier);

		return identifier;
	}

	protected abstract void add(UUID identifier);

	/**
	 * Remove an entity from this managers management.
	 * 
	 * @param entityIdentifier
	 *          The identity of the Entity we want to remove.
	 * @return True if the entity was removed, false otherwise.
	 */
	@Override
	public final boolean destroy(UUID entity) {
		boolean removed = detach(entity);

		if (removed) {
			getStateManager().detachAll(entity);

			getBehaviourManager().detachAll(entity);
		}

		return removed;
	}

	protected abstract boolean detach(UUID entity);
}
