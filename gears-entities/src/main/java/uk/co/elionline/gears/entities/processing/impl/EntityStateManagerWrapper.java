package uk.co.elionline.gears.entities.processing.impl;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.management.EntityStateManager;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentData;
import uk.co.elionline.gears.utilities.Decorator;

public class EntityStateManagerWrapper extends Decorator<EntityStateManager>
		implements EntityStateManager {
	public EntityStateManagerWrapper(EntityStateManager entityStateManager) {
		super(entityStateManager);
	}

	@Override
	public boolean has(UUID entity, StateComponent<?> stateComponent) {
		return getComponent().has(entity, stateComponent);
	}

	@Override
	public <D> StateComponentData<D> getStateComponentData(UUID entity,
			StateComponent<D> stateComponent) {
		return getStateComponentData(entity, stateComponent);
	}

	@Override
	public Set<UUID> getEntitiesWith(Collection<StateComponent<?>> stateComponents) {
		return getEntitiesWith(stateComponents);
	}

	@Override
	public Set<UUID> getEntitiesWith(StateComponent<?>... stateComponents) {
		return getEntitiesWith(stateComponents);
	}

	@Override
	public Set<UUID> getEntitiesWith(StateComponent<?> stateComponents) {
		return getEntitiesWith(stateComponents);
	}

	@Override
	public <D> D getData(UUID entity, StateComponent<D> stateComponent) {
		return getData(entity, stateComponent);
	}

	@Override
	public Set<StateComponent<?>> getAll() {
		return getAll();
	}

	@Override
	public void detachAll(UUID entity) {
		detachAll(entity);
	}

	@Override
	public boolean detach(UUID entity, StateComponent<?> stateComponent) {
		return detach(entity, stateComponent);
	}

	@Override
	public void attachAndSetAll(UUID entity, StateComponentData<?>... stateData) {
		attachAndSetAll(entity, stateData);
	}

	@Override
	public void attachAndSetAll(UUID entity,
			Collection<? extends StateComponentData<?>> stateData) {
		attachAndSetAll(entity, stateData);
	}

	@Override
	public <D> D attachAndSet(UUID entity, StateComponentData<D> stateData) {
		return attachAndSet(entity, stateData);
	}

	@Override
	public <D> D attachAndSet(UUID entity, StateComponent<D> stateComponent,
			D stateData) {
		return attachAndSet(entity, stateComponent, stateData);
	}

	@Override
	public void attachAndResetAll(UUID entity,
			StateComponent<?>... stateComponents) {
		attachAndResetAll(entity, stateComponents);
	}

	@Override
	public void attachAndResetAll(UUID entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		attachAndResetAll(entity, stateComponents);
	}

	@Override
	public <D> D attachAndReset(UUID entity, StateComponent<D> stateComponent) {
		return attachAndReset(entity, stateComponent);
	}

	@Override
	public void attachAll(UUID entity, StateComponent<?>... stateComponents) {
		attachAll(entity, stateComponents);
	}

	@Override
	public void attachAll(UUID entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		attachAll(entity, stateComponents);
	}

	@Override
	public <D> D attach(UUID entity, StateComponent<D> stateComponent) {
		return attach(entity, stateComponent);
	}
}