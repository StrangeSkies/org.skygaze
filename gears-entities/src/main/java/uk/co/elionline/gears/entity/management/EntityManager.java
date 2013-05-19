package uk.co.elionline.gears.entity.management;

import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.utilities.Factory;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface EntityManager extends Factory<Entity> {
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

	public EntityStateManager state();

	public EntityBehaviourManager behaviour();
}
