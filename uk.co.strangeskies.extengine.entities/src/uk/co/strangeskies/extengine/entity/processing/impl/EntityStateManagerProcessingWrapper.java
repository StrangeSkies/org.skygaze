package uk.co.strangeskies.extengine.entity.processing.impl;

import java.util.Collection;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.management.EntityStateManager;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.Decorator;
import uk.co.strangeskies.utilities.flowcontrol.StripedReadWriteLockRelease;

public class EntityStateManagerProcessingWrapper extends
		Decorator<EntityStateManager> implements EntityStateManager {
	private final StripedReadWriteLockRelease<StateComponent<?>> locks;

	public EntityStateManagerProcessingWrapper(
			EntityStateManager entityStateManager,
			StripedReadWriteLockRelease<StateComponent<?>> locks) {
		super(entityStateManager);
		this.locks = locks;
	}

	@Override
	public boolean has(Entity entity, StateComponent<?> stateComponent) {
		return getComponent().has(entity, stateComponent);
	}

	@Override
	public Set<Entity> getEntitiesWith(
			Collection<? extends StateComponent<?>> stateComponents) {
		return getComponent().getEntitiesWith(stateComponents);
	}

	@Override
	public Set<Entity> getEntitiesWith(StateComponent<?>... stateComponents) {
		return getComponent().getEntitiesWith(stateComponents);
	}

	@Override
	public Set<Entity> getEntitiesWith(StateComponent<?> stateComponents) {
		return getComponent().getEntitiesWith(stateComponents);
	}

	@Override
	public <D> D getData(Entity entity, StateComponent<D> stateComponent) {
		if (!locks.isWriteLockHeldByCurrentThread(stateComponent))
			throw new IllegalStateException("Attempting to write to state '"
					+ stateComponent.getName() + "' without lock.");
		return getComponent().getData(entity, stateComponent);
	}

	@Override
	public <D> /* @ReadOnly */D getReadOnlyData(Entity entity,
			StateComponent<D> stateComponent) {
		if (!locks.isLockHeldByCurrentThread(stateComponent))
			throw new IllegalStateException("Attempting to read from state '"
					+ stateComponent.getName() + "' without lock.");
		return getComponent().getReadOnlyData(entity, stateComponent);
	}

	@Override
	public <D> Set<D> getAllData(StateComponent<D> stateComponent) {
		if (!locks.isWriteLockHeldByCurrentThread(stateComponent))
			throw new IllegalStateException();
		return getComponent().getAllData(stateComponent);
	}

	@Override
	public <D> Set<D> getAllReadOnlyData(StateComponent<D> stateComponent) {
		if (!locks.isLockHeldByCurrentThread(stateComponent))
			throw new IllegalStateException();
		return getComponent().getAllReadOnlyData(stateComponent);
	}

	@Override
	public Set<StateComponent<?>> getAll() {
		return getComponent().getAll();
	}

	@Override
	public boolean detach(Entity entity, StateComponent<?> stateComponent) {
		if (locks.isWriteLockHeldByCurrentThread(stateComponent))
			throw new IllegalStateException();
		return getComponent().detach(entity, stateComponent);
	}

	@Override
	public void attachAndResetAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents)
			if (!locks.isWriteLockHeldByCurrentThread(stateComponent))
				throw new IllegalStateException();
		getComponent().attachAndResetAll(entity, stateComponents);
	}

	@Override
	public void attachAndResetAll(Entity entity,
			StateComponent<?>... stateComponents) {
		getComponent().attachAndResetAll(entity, stateComponents);
	}

	@Override
	public <D> D attachAndReset(Entity entity, StateComponent<D> stateComponent) {
		if (!locks.isWriteLockHeldByCurrentThread(stateComponent))
			throw new IllegalStateException();
		return getComponent().attachAndReset(entity, stateComponent);
	}

	@Override
	public void attachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents)
			if (!locks.isWriteLockHeldByCurrentThread(stateComponent))
				throw new IllegalStateException();
		getComponent().attachAll(entity, stateComponents);
	}

	@Override
	public void attachAll(Entity entity, StateComponent<?>... stateComponents) {
		getComponent().attachAll(entity, stateComponents);
	}

	@Override
	public <D> D attach(Entity entity, StateComponent<D> stateComponent) {
		if (!locks.isWriteLockHeldByCurrentThread(stateComponent))
			throw new IllegalStateException();
		return getComponent().attach(entity, stateComponent);
	}

	@Override
	public boolean detachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents)
			if (!locks.isWriteLockHeldByCurrentThread(stateComponent))
				throw new IllegalStateException();
		return getComponent().detachAll(entity, stateComponents);
	}

	@Override
	public boolean detachAll(Entity entity, StateComponent<?>... stateComponents) {
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

	@Override
	public boolean hasAll(Entity entity, StateComponent<?>... stateComponents) {
		return getComponent().hasAll(entity, stateComponents);
	}
}