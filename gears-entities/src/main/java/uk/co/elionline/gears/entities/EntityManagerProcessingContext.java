package uk.co.elionline.gears.entities;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourManagerProcessingContext;
import uk.co.elionline.gears.entities.state.StateManagerProcessingContext;
import uk.co.elionline.gears.utilities.Factory;

public interface EntityManagerProcessingContext extends Factory<UUID> {
	public StateManagerProcessingContext getStateManager();

	public BehaviourManagerProcessingContext getBehaviourManager();

	/**
	 * This should get the Entity with the appropriate UUID, if it is under the
	 * management of this manager, otherwise it should return null.
	 * 
	 * @param entityIdentifier
	 *          The identifier of the entity we want.
	 * @return The entity with this UUID, or null if that entity is not currently
	 *         under this managers control.
	 */
	public Entity get(UUID entityIdentifier);

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

	public boolean stopProcessing();
}