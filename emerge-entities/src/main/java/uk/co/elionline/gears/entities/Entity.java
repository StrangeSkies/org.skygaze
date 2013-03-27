package uk.co.elionline.gears.entities;

import java.util.UUID;

public class Entity {
	private final LockedEntityManager manager;
	private final UUID identifier;

	public Entity(LockedEntityManager manager, UUID identifier) {
		this.manager = manager;
		this.identifier = identifier;
	}

	public LockedEntityManager getManager() {
		return manager;
	}

	public UUID getIdentifier() {
		return identifier;
	}
}
