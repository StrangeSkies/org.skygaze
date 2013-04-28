package uk.co.elionline.gears.entities.management.impl.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.management.impl.AbstractEntityStateManager;
import uk.co.elionline.gears.entities.state.StateComponent;

public class CollectionsEntityStateManager extends AbstractEntityStateManager {
	private final Map<StateComponent<?>, Map<UUID, Object>> entityStateData;

	public CollectionsEntityStateManager() {
		entityStateData = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> D attachAndSet(UUID entity, StateComponent<D> stateComponent,
			D stateData) {
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
}
