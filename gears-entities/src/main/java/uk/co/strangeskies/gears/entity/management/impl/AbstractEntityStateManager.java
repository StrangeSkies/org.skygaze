package uk.co.strangeskies.gears.entity.management.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.management.EntityStateManager;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.mathematics.functions.collections.SetTransformationFunction;

public abstract class AbstractEntityStateManager implements EntityStateManager {
	@Override
	public final <D> D attach(Entity entity, StateComponent<D> stateComponent) {
		if (!has(entity, stateComponent)) {
			attachAndReset(entity, stateComponent);
		}
		return getData(entity, stateComponent);
	}

	@Override
	public void attachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents) {
		for (StateComponent<?> stateComponent : stateComponents) {
			attach(entity, stateComponent);
		}
	}

	@Override
	public void attachAll(Entity entity, StateComponent<?>... stateComponents) {
		attachAll(entity, Arrays.asList(stateComponents));
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

	@Override
	public void attachAndResetAll(Entity entity,
			StateComponent<?>... stateComponents) {
		attachAndResetAll(entity, Arrays.asList(stateComponents));
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
	public boolean detachAll(Entity entity, StateComponent<?>... stateComponents) {
		return detachAll(entity, Arrays.asList(stateComponents));
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

	@Override
	public boolean hasAll(Entity entity, StateComponent<?>... stateComponents) {
		return hasAll(entity, Arrays.asList(stateComponents));
	}

	@Override
	public <D> D getReadOnlyData(Entity entity, StateComponent<D> stateComponent) {
		return getData(entity, stateComponent);
	}

	@Override
	public <D> Set<D> getAllData(final StateComponent<D> stateComponent) {
		return SetTransformationFunction.applyTo(getEntitiesWith(stateComponent),
				new Function<D, Entity>() {
					@Override
					public D applyTo(Entity input) {
						return getData(input, stateComponent);
					}
				});
	}

	@Override
	public <D> Set<D> getAllReadOnlyData(final StateComponent<D> stateComponent) {
		return getAllData(stateComponent);
	}

	@Override
	public Set<Entity> getEntitiesWith(
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

	@Override
	public Set<Entity> getEntitiesWith(StateComponent<?>... stateComponents) {
		return getEntitiesWith(Arrays.asList(stateComponents));
	}
}
