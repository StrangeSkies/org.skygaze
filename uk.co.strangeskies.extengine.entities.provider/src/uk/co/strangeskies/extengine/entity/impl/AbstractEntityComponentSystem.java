package uk.co.strangeskies.extengine.entity.impl;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentManager;
import uk.co.strangeskies.extengine.entity.state.StateComponentManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public abstract class AbstractEntityComponentSystem implements EntityComponentSystem {
	private final StateComponentManager stateManager;
	private final BehaviourComponentManager behaviourManager;

	public AbstractEntityComponentSystem(StateComponentManager stateManager,
			BehaviourComponentManager behaviourManager) {
		this.stateManager = stateManager;
		this.behaviourManager = behaviourManager;
	}

	@Override
	public StateComponentManager state() {
		return stateManager;
	}

	@Override
	public BehaviourComponentManager behaviour() {
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
