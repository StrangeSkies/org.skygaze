package uk.co.elionline.gears.entity.processing.impl;

import java.util.Collection;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.management.EntityStateManager;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentData;
import uk.co.elionline.gears.utilities.Decorator;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public class EntityStateManagerWrapper extends Decorator<EntityStateManager>
		implements EntityStateManager {
	private final StripedReadWriteLockRelease<StateComponent<?>> locks;

	public EntityStateManagerWrapper(EntityStateManager entityStateManager,
			StripedReadWriteLockRelease<StateComponent<?>> locks) {
		super(entityStateManager);
		this.locks = locks;
	}

	@Override
	public boolean has(Entity entity, StateComponent<?> stateComponent) {
		return getComponent().has(entity, stateComponent);
	}

	@Override
	public <D> StateComponentData<D> getStateComponentData(Entity entity,
			StateComponent<D> stateComponent) {
		return getComponent().getStateComponentData(entity, stateComponent);
	}

	@Override
	public Set<Entity> getEntitiesWith(
			Collection<StateComponent<?>> stateComponents) {
		return getComponent().getEntitiesWith(stateComponents);
	}

	@Override
	public Set<Entity> getEntitiesWith(StateComponent<?> stateComponents) {
		return getComponent().getEntitiesWith(stateComponents);
	}

	@Override
	public <D> D getData(Entity entity, StateComponent<D> stateComponent) {
		return getComponent().getData(entity, stateComponent);
	}

	@Override
	public Set<StateComponent<?>> getAll() {
		return getComponent().getAll();
	}

	@Override
	public boolean detach(Entity entity, StateComponent<?> stateComponent) {
		return getComponent().detach(entity, stateComponent);
	}

	@Override
	public void attachAndResetAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		getComponent().attachAndResetAll(entity, stateComponents);
	}

	@Override
	public <D> D attachAndReset(Entity entity, StateComponent<D> stateComponent) {
		return getComponent().attachAndReset(entity, stateComponent);
	}

	@Override
	public void attachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		getComponent().attachAll(entity, stateComponents);
	}

	@Override
	public <D> D attach(Entity entity, StateComponent<D> stateComponent) {
		return getComponent().attach(entity, stateComponent);
	}

	@Override
	public boolean detachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		return getComponent().detachAll(entity, stateComponents);
	}

	@Override
	public void clear(Entity entity) {
		getComponent().clear(entity);
	}

	@Override
	public boolean hasAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		return getComponent().hasAll(entity, stateComponents);
	}
}