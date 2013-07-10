package uk.co.elionline.gears.entity.management;

import java.util.Collection;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.state.StateComponent;

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

	public <D> D getData(Entity entity, StateComponent<D> stateComponent);

	public <D> /*@ReadOnly*/D getReadOnlyData(Entity entity,
			StateComponent<D> stateComponent);

	public boolean has(Entity entity, StateComponent<?> stateComponent);

	public boolean hasAll(Entity entity,
			Collection<? extends StateComponent<?>> stateComponents);

	public Set<Entity> getEntitiesWith(StateComponent<?> stateComponent);

	public Set<Entity> getEntitiesWith(
			Collection<StateComponent<?>> stateComponents);
}
