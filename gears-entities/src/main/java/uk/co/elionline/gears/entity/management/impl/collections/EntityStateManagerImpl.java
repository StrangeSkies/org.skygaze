package uk.co.elionline.gears.entity.management.impl.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.management.impl.AbstractEntityStateManager;
import uk.co.elionline.gears.entity.state.StateComponent;

public class EntityStateManagerImpl extends AbstractEntityStateManager {
	private final Map<StateComponent<?>, Map<Entity, Object>> entityStateData;

	public EntityStateManagerImpl() {
		entityStateData = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <D> D attachAndSet(Entity entity,
			StateComponent<D> stateComponent, D stateData) {
		Map<Entity, Object> entityData = entityStateData.get(stateComponent);

		if (entityData == null) {
			entityData = new HashMap<>();
			entityStateData.put(stateComponent, entityData);
		}

		stateData = (D) entityData.put(entity, stateData);

		return stateData;
	}

	@Override
	public synchronized boolean detach(Entity entity,
			StateComponent<?> stateComponent) {
		Map<Entity, ?> entities = entityStateData.get(stateComponent);

		boolean removed = entities != null && entities.remove(entity) != null;

		if (removed && entities.isEmpty()) {
			entityStateData.remove(stateComponent);
		}

		return removed;
	}

	@Override
	public synchronized void clear(Entity entity) {
		Iterator<Map<Entity, Object>> entityDataIterator = entityStateData.values()
				.iterator();
		while (entityDataIterator.hasNext()) {
			Map<Entity, ?> entityData = entityDataIterator.next();

			entityData.remove(entity);

			if (entityData.isEmpty()) {
				entityDataIterator.remove();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <D> D getData(Entity entity,
			StateComponent<D> stateComponent) {
		return (D) entityStateData.get(stateComponent).get(entity);
	}

	@Override
	public synchronized boolean has(Entity entity,
			StateComponent<?> stateComponent) {
		Map<Entity, Object> dataMap = entityStateData.get(stateComponent);
		return dataMap != null && dataMap.containsKey(entity);
	}

	@Override
	public synchronized Set<Entity> getEntitiesWith(
			StateComponent<?> stateComponent) {
		Map<Entity, Object> dataMap = entityStateData.get(stateComponent);
		if (dataMap != null) {
			return new HashSet<>(dataMap.keySet());
		} else {
			return new HashSet<>();
		}
	}

	@Override
	public synchronized Set<StateComponent<?>> getAll() {
		return entityStateData.keySet();
	}
}
