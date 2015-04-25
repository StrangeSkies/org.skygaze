package uk.co.strangeskies.extengine.entity.management.impl;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.management.EntityBehaviourManager;
import uk.co.strangeskies.extengine.entity.management.EntityManager;
import uk.co.strangeskies.extengine.entity.management.EntityStateManager;

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
	public EntityStateManager state() {
		return stateManager;
	}

	@Override
	public EntityBehaviourManager behaviour() {
		return behaviourManager;
	}

	/**
	 * Create an entity to be managed by this class.
	 */
	@Override
	public final Entity create() {
		Entity entity = new Entity() {
		};

		add(entity);

		return entity;
	}

	protected abstract void add(Entity identifier);

	/**
	 * Remove an entity from this managers management.
	 * 
	 * @param entity
	 *          The identity of the Entity we want to remove.
	 * @return True if the entity was removed, false otherwise.
	 */
	@Override
	public final boolean destroy(Entity entity) {
		boolean removed = detach(entity);

		if (removed) {
			state().clear(entity);

			behaviour().clear(entity);
		}

		return removed;
	}

	protected abstract boolean detach(Entity entity);
}
