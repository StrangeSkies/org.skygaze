package uk.co.elionline.gears.entities;

import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourProcessingContextEntityManager;

public class Entity {
	private final BehaviourProcessingContextEntityManager manager;
	private final UUID identifier;

	public Entity(BehaviourProcessingContextEntityManager manager, UUID identifier) {
		this.manager = manager;
		this.identifier = identifier;
	}

	public BehaviourProcessingContextEntityManager getManager() {
		return manager;
	}

	public UUID getIdentifier() {
		return identifier;
	}
}
