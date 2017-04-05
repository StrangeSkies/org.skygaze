package uk.co.strangeskies.extengine.entity.impl;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentManager;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponentManager;
import uk.co.strangeskies.utilities.flowcontrol.StripedReadWriteLockRelease;

public class EntityComponentSystemWrapper implements EntityComponentSystem {
	private final EntityComponentSystem component;
	private final StripedReadWriteLockRelease<StateComponent<?, ?>> locks;

	public EntityComponentSystemWrapper(EntityComponentSystem entityManager,
			StripedReadWriteLockRelease<StateComponent<?, ?>> locks) {
		component = entityManager;

		this.locks = locks;
	}

	protected EntityComponentSystem getComponent() {
		return component;
	}

	@Override
	public Entity create() {
		return getComponent().create();
	}

	@Override
	public StateComponentManager state() {
		return new StateComponentManagerProcessingWrapper(getComponent().state(), locks);
	}

	@Override
	public BehaviourComponentManager behaviour() {
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
