package uk.co.elionline.gears.entities.management;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.utilities.Factory;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface EntityManager extends Factory<UUID> {
	public boolean exists(UUID identifier);

	public Set<UUID> getAll();

	/**
	 * Remove an entity from this managers management.
	 * 
	 * @param entityIdentifier
	 *          The identity of the Entity we want to remove.
	 * @return True if the entity was removed, false otherwise.
	 */
	public boolean destroy(UUID entityIdentifier);

	public EntityStateManager getStateManager();

	public EntityBehaviourManager getBehaviourManager();
}
