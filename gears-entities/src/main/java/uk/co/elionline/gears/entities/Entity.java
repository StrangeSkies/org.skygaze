package uk.co.elionline.gears.entities;

import java.util.UUID;

import uk.co.elionline.gears.entities.management.EntityProcessingContext;

public class Entity {
	private final EntityProcessingContext manager;
	private final UUID identifier;

	public Entity(EntityProcessingContext manager, UUID identifier) {
		this.manager = manager;
		this.identifier = identifier;
	}

	public EntityProcessingContext getManager() {
		return manager;
	}

	public UUID getIdentifier() {
		return identifier;
	}
}
