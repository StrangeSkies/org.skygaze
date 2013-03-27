package uk.co.elionline.emerge.entities.implementations.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.emerge.entities.implementations.AbstractStateManager;
import uk.co.elionline.emerge.entities.state.StateComponent;
import uk.co.elionline.emerge.entities.state.StateManager;
import uk.co.elionline.emerge.utilities.flowcontrol.HashingStripedReadWriteLock;
import uk.co.elionline.emerge.utilities.flowcontrol.StripedReadWriteLock;

@Component(service = StateManager.class)
public class CollectionsStateManager extends AbstractStateManager {
	private final Map<StateComponent<?>, Map<UUID, Object>> entityStateData;

	private final HashingStripedReadWriteLock<StateComponent<?>> locks;
	private boolean lockingEnabled;

	public CollectionsStateManager() {
		entityStateData = new HashMap<>();

		locks = new HashingStripedReadWriteLock<>();
		lockingEnabled = false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> D attachAndSet(UUID entity, StateComponent<D> stateComponent,
			D stateData) {
		if (isLockingEnabled()
				&& !locks.isWriteLockHeldByCurrentThread(stateComponent)) {
			throw new IllegalMonitorStateException();
		}

		Map<UUID, Object> entityData = entityStateData.get(stateComponent);

		if (entityData == null) {
			entityData = new HashMap<>();
			entityStateData.put(stateComponent, entityData);
		}

		stateData = (D) entityData.put(entity, stateData);

		return stateData;
	}

	@Override
	public boolean detach(UUID entity, StateComponent<?> stateComponent) {
		if (isLockingEnabled()
				&& !locks.isWriteLockHeldByCurrentThread(stateComponent)) {
			throw new IllegalMonitorStateException();
		}

		Map<UUID, ?> entities = entityStateData.get(stateComponent);

		boolean removed = entities != null && entities.remove(entity) != null;

		if (removed && entities.isEmpty()) {
			entityStateData.remove(stateComponent);
		}

		return removed;
	}

	@Override
	public void detachAll(UUID entity) {
		Iterator<Map<UUID, Object>> entityDataIterator = entityStateData.values()
				.iterator();
		while (entityDataIterator.hasNext()) {
			Map<UUID, ?> entityData = entityDataIterator.next();

			entityData.remove(entity);

			if (entityData.isEmpty()) {
				entityDataIterator.remove();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> D getData(UUID entity, StateComponent<D> stateComponent) {
		if (isLockingEnabled() && !locks.isLockHeldByCurrentThread(stateComponent)) {
			throw new IllegalMonitorStateException();
		}

		return (D) entityStateData.get(stateComponent).get(entity);
	}

	@Override
	public boolean has(UUID entity, StateComponent<?> stateComponent) {
		Map<UUID, Object> dataMap = entityStateData.get(stateComponent);
		return dataMap != null && dataMap.containsKey(entity);
	}

	@Override
	public Set<UUID> getEntitiesWith(StateComponent<?> stateComponent) {
		Map<UUID, Object> dataMap = entityStateData.get(stateComponent);
		if (dataMap != null) {
			return new HashSet<>(dataMap.keySet());
		} else {
			return new HashSet<>();
		}
	}

	@Override
	public Set<StateComponent<?>> getAll() {
		return entityStateData.keySet();
	}

	@Override
	public void setLockingEnabled(boolean enabled) {
		lockingEnabled = enabled;
	}

	@Override
	public boolean isLockingEnabled() {
		return lockingEnabled;
	}

	@Override
	public StripedReadWriteLock<StateComponent<?>> getLocks() {
		return locks;
	}
}
