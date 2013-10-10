package uk.co.strangeskies.gears.entity.processing.impl;

import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.management.EntityBehaviourManager;
import uk.co.strangeskies.gears.entity.management.EntityManager;
import uk.co.strangeskies.gears.entity.management.EntityStateManager;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.utilities.Decorator;
import uk.co.strangeskies.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public class EntityManagerProcessingWrapper extends Decorator<EntityManager>
		implements EntityManager {
	private final StripedReadWriteLockRelease<StateComponent<?>> locks;

	public EntityManagerProcessingWrapper(EntityManager entityManager,
			StripedReadWriteLockRelease<StateComponent<?>> locks) {
		super(entityManager);

		this.locks = locks;
	}

	@Override
	public Entity create() {
		return getComponent().create();
	}

	@Override
	public EntityStateManager state() {
		return new EntityStateManagerProcessingWrapper(getComponent().state(),
				locks);
	}

	@Override
	public EntityBehaviourManager behaviour() {
		return getComponent().behaviour();
	}

	@Override
	public Set<Entity> getAll() {
		return getComponent().getAll();
	}

	@Override
	public boolean exists(Entity identifier) {
		return getComponent().exists(identifier);
	}

	@Override
	public boolean destroy(Entity entity) {
		return getComponent().destroy(entity);
	}
}
