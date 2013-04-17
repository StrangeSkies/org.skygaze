package uk.co.elionline.gears.entities.management.implementation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.management.EntityStateManager;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentData;

public abstract class AbstractStateManager implements EntityStateManager {
	@Override
	public final <D> D attach(UUID entity, StateComponent<D> stateComponent) {
		if (has(entity, stateComponent)) {
			return getData(entity, stateComponent);
		} else {
			return attachAndReset(entity, stateComponent);
		}
	}

	@Override
	public void attachAll(UUID entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			attach(entity, stateComponent);
		}
	}

	@Override
	public void attachAll(UUID entity, StateComponent<?>... stateComponents) {
		attachAll(entity, Arrays.asList(stateComponents));
	}

	@Override
	public final <D> D attachAndReset(UUID entity,
			StateComponent<D> stateComponent) {
		return attachAndSet(entity, stateComponent, stateComponent.create());
	}

	@Override
	public void attachAndResetAll(UUID entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			attachAndReset(entity, stateComponent);
		}
	}

	@Override
	public void attachAndResetAll(UUID entity,
			StateComponent<?>... stateComponents) {
		attachAndResetAll(entity, Arrays.asList(stateComponents));
	}

	@Override
	public <D> D attachAndSet(UUID entity, /* ReadOnly */
			StateComponentData<D> stateData) {
		return attachAndSet(entity, stateData.getComponent(), stateData.get());
	}

	@Override
	public void attachAndSetAll(UUID entity,
			Collection<? extends StateComponentData<?>> stateData) {
		for (StateComponentData<?> stateComponent : stateData) {
			attachAndSet(entity, stateComponent);
		}
	}

	@Override
	public void attachAndSetAll(UUID entity, StateComponentData<?>... stateData) {
		attachAndSetAll(entity, Arrays.asList(stateData));
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
	public final <D> StateComponentData<D> getStateComponentData(UUID entity,
			StateComponent<D> stateComponent) {
		return new StateComponentData<D>(entity, stateComponent, getData(entity,
				stateComponent));
	}

	@Override
	public final Set<UUID> getEntitiesWith(StateComponent<?>... stateComponents) {
		return getEntitiesWith(Arrays.asList(stateComponents));
	}

	@Override
	public Set<UUID> getEntitiesWith(Collection<StateComponent<?>> stateComponents) {
		if (stateComponents.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Set<UUID> entitiesWithState = new HashSet<>();

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
