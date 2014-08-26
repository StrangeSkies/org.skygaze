package uk.co.strangeskies.gears.entity.management;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.utilities.function.collection.SetTransformationFunction;

public interface EntityStateManager {
	public default <D> D attach(Entity entity, StateComponent<D> stateComponent) {
		if (!has(entity, stateComponent)) {
			attachAndReset(entity, stateComponent);
		}
		return getData(entity, stateComponent);
	}

	public default void attachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			attach(entity, stateComponent);
		}
	}

	public default void attachAll(Entity entity,
			StateComponent<?>... stateComponents) {
		attachAll(entity, Arrays.asList(stateComponents));
	}

	public <D> D attachAndReset(Entity entity, StateComponent<D> stateComponent);

	public default void attachAndResetAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			attachAndReset(entity, stateComponent);
		}
	}

	public default void attachAndResetAll(Entity entity,
			StateComponent<?>... stateComponents) {
		attachAndResetAll(entity, Arrays.asList(stateComponents));
	}

	public boolean detach(Entity entity, StateComponent<?> stateComponent);

	public default boolean detachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		boolean removed = false;

		for (StateComponent<?> stateComponent : stateComponents) {
			detach(entity, stateComponent);
		}

		return removed;
	}

	public default boolean detachAll(Entity entity,
			StateComponent<?>... stateComponents) {
		return detachAll(entity, Arrays.asList(stateComponents));
	}

	public void clear(Entity entity);

	public Set<StateComponent<?>> getAll();

	public <D> D getData(Entity entity, StateComponent<D> stateComponent);

	public default <D> D getReadOnlyData(Entity entity,
			StateComponent<D> stateComponent) {
		return getData(entity, stateComponent);
	}

	public default <D> Set<D> getAllData(final StateComponent<D> stateComponent) {
		return SetTransformationFunction.apply(getEntitiesWith(stateComponent),
				e -> getData(e, stateComponent));
	}

	public default <D> Set<D> getAllReadOnlyData(
			final StateComponent<D> stateComponent) {
		return getAllData(stateComponent);
	}

	public boolean has(Entity entity, StateComponent<?> stateComponent);

	public default boolean hasAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			if (!has(entity, stateComponent)) {
				return false;
			}
		}

		return true;
	}

	public default boolean hasAll(Entity entity,
			StateComponent<?>... stateComponents) {
		return hasAll(entity, Arrays.asList(stateComponents));
	}

	public Set<Entity> getEntitiesWith(StateComponent<?> stateComponent);

	public default Set<Entity> getEntitiesWith(
			Collection<? extends StateComponent<?>> stateComponents) {
		if (stateComponents.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Set<Entity> entitiesWithState = new HashSet<>();

		Iterator<? extends StateComponent<?>> stateComponentIterator = stateComponents
				.iterator();

		entitiesWithState.addAll(getEntitiesWith(stateComponentIterator.next()));
		while (stateComponentIterator.hasNext() && !entitiesWithState.isEmpty()) {
			entitiesWithState
					.retainAll(getEntitiesWith(stateComponentIterator.next()));
		}

		return entitiesWithState;
	}

	public default Set<Entity> getEntitiesWith(
			StateComponent<?>... stateComponents) {
		return getEntitiesWith(Arrays.asList(stateComponents));
	}
}
