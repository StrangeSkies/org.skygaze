package uk.co.elionline.gears.entities;

import java.util.UUID;

import uk.co.elionline.gears.entities.management.EntityManagerProcessingContext;

public class Entity {
	private final EntityManagerProcessingContext manager;
	private final UUID identifier;

	public Entity(EntityManagerProcessingContext manager, UUID identifier) {
		this.manager = manager;
		this.identifier = identifier;
	}

	public EntityManagerProcessingContext getManager() {
		return manager;
	}

	public UUID getIdentifier() {
		return identifier;
	}
}
