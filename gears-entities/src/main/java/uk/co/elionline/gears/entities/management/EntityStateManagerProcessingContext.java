package uk.co.elionline.gears.entities.management;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentData;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public interface EntityStateManagerProcessingContext {
	public <D> D attach(UUID entity, StateComponent<D> stateComponent);

	public void attachAll(UUID entity,
			Collection<? extends StateComponent<?>> stateComponents);

	public void attachAll(UUID entity, StateComponent<?>... stateComponents);

	public <D> D attachAndReset(UUID entity, StateComponent<D> stateComponent);

	public void attachAndResetAll(UUID entity,
			Collection<? extends StateComponent<?>> stateComponents);

	public void attachAndResetAll(UUID entity,
			StateComponent<?>... stateComponents);

	public <D> D attachAndSet(UUID entity, StateComponent<D> stateComponent, /* ReadOnly */
			D stateData);

	public <D> D attachAndSet(UUID entity, /* ReadOnly */
			StateComponentData<D> stateData);

	public void attachAndSetAll(UUID entity, /* ReadOnly */
			Collection<? extends StateComponentData<?>> stateData);

	public void attachAndSetAll(UUID entity, /* ReadOnly */
			StateComponentData<?>... stateData);

	public boolean detach(UUID entity, StateComponent<?> stateComponent);

	public void detachAll(UUID entity);

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
	public <D> StateComponentData<D> getStateComponentData(UUID entity,
			StateComponent<D> stateComponent);

	public <D> D getData(UUID entity, StateComponent<D> stateComponent);

	public boolean has(UUID entity, StateComponent<?> stateComponent);

	public Set<UUID> getEntitiesWith(StateComponent<?> stateComponents);

	public Set<UUID> getEntitiesWith(StateComponent<?>... stateComponents);

	public Set<UUID> getEntitiesWith(Collection<StateComponent<?>> stateComponents);

	public boolean isLockingEnabled();

	public StripedReadWriteLockRelease<StateComponent<?>> getLocks();
}