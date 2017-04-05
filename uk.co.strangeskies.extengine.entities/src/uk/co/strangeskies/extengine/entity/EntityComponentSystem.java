package uk.co.strangeskies.extengine.entity;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentManager;
import uk.co.strangeskies.extengine.entity.state.StateComponentManager;
import uk.co.strangeskies.utilities.factory.Factory;

/**
 * Naming convention suggests 'entity' for instances of {@link EntityComponentSystem},
 * as it is semantically consistent with the state() and behaviour() methods in
 * that it is a description of the domain of operations provided.
 *
 * @author Elias N Vasylenko
 *
 */
public interface EntityComponentSystem extends Factory<Entity> {
	public boolean exists(Entity identifier);

	public Set<Entity> getAll();

	/**
	 * Remove an entity from this managers management.
	 *
	 * @param entity
	 *          The identity of the Entity we want to remove.
	 * @return True if the entity was removed, false otherwise.
	 */
	public boolean destroy(Entity entity);

	public StateComponentManager state();

	public BehaviourComponentManager behaviour();
}
