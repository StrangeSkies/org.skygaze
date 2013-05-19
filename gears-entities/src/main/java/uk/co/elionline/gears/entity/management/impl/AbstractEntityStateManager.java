package uk.co.elionline.gears.entity.management.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.management.EntityStateManager;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentData;

public abstract class AbstractEntityStateManager implements EntityStateManager {
	@Override
	public final <D> D attach(Entity entity, StateComponent<D> stateComponent) {
		if (has(entity, stateComponent)) {
			return getData(entity, stateComponent);
		} else {
			return attachAndReset(entity, stateComponent);
		}
	}

	@Override
	public void attachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			attach(entity, stateComponent);
		}
	}

	@Override
	public final <D> D attachAndReset(Entity entity,
			StateComponent<D> stateComponent) {
		return attachAndSet(entity, stateComponent, stateComponent.create());
	}

	@Override
	public void attachAndResetAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			attachAndReset(entity, stateComponent);
		}
	}

	protected abstract <D> D attachAndSet(Entity entity, /* ReadOnly */
			StateComponent<D> stateComponent, D stateData);

	@Override
	public boolean detachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		boolean removed = false;

		for (StateComponent<?> stateComponent : stateComponents) {
			detach(entity, stateComponent);
		}

		return removed;
	}

	@Override
	public boolean hasAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			if (!has(entity, stateComponent)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Get the instance of a particular type of data component that is associated
	 * with this entity.
	 * 
	 * @param entity
	 *          The entity for whom we want to retrieve the data.
	 * @param dataComponentClass
	 *          The class of the data component for which we want the data.
	 * @return The data of the given data component type for the given entity, if
	 *         it exists, otherwise null.
	 */
	@Override
	public final <D> StateComponentData<D> getStateComponentData(Entity entity,
			StateComponent<D> stateComponent) {
		return new StateComponentDataImpl<D>(entity, stateComponent, getData(
				entity, stateComponent));
	}

	@Override
	public Set<Entity> getEntitiesWith(
			Collection<StateComponent<?>> stateComponents) {
		if (stateComponents.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Set<Entity> entitiesWithState = new HashSet<>();

		Iterator<StateComponent<?>> stateComponentIterator = stateComponents
				.iterator();

		entitiesWithState.addAll(getEntitiesWith(stateComponentIterator.next()));
		while (stateComponentIterator.hasNext() && !entitiesWithState.isEmpty()) {
			entitiesWithState
					.retainAll(getEntitiesWith(stateComponentIterator.next()));
		}

		return entitiesWithState;
	}
}
