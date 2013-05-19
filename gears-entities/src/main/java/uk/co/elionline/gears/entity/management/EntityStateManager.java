package uk.co.elionline.gears.entity.management;

import java.util.Collection;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentData;

public interface EntityStateManager {
	public <D> D attach(Entity entity, StateComponent<D> stateComponent);

	public void attachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents);

	public <D> D attachAndReset(Entity entity, StateComponent<D> stateComponent);

	public void attachAndResetAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents);

	public boolean detach(Entity entity, StateComponent<?> stateComponent);

	public boolean detachAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents);

	public void clear(Entity entity);

	public Set<StateComponent<?>> getAll();

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
	public <D> StateComponentData<D> getStateComponentData(Entity entity,
			StateComponent<D> stateComponent);

	public <D> D getData(Entity entity, StateComponent<D> stateComponent);

	public boolean has(Entity entity, StateComponent<?> stateComponent);

	public boolean hasAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents);

	public Set<Entity> getEntitiesWith(StateComponent<?> stateComponent);

	public Set<Entity> getEntitiesWith(
			Collection<StateComponent<?>> stateComponents);
}
