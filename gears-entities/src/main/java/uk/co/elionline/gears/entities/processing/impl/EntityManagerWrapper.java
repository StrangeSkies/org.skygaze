package uk.co.elionline.gears.entities.processing.impl;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.management.EntityBehaviourManager;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.management.EntityStateManager;
import uk.co.elionline.gears.utilities.Decorator;

public class EntityManagerWrapper extends Decorator<EntityManager> implements
		EntityManager {
	public EntityManagerWrapper(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public UUID create() {
		return getComponent().create();
	}

	@Override
	public EntityStateManager getStateManager() {
		return new EntityStateManagerWrapper(getComponent().getStateManager());
	}

	@Override
	public EntityBehaviourManager getBehaviourManager() {
		return getComponent().getBehaviourManager();
	}

	@Override
	public Set<UUID> getAll() {
		return getComponent().getAll();
	}

	@Override
	public boolean exists(UUID identifier) {
		return getComponent().exists(identifier);
	}

	@Override
	public boolean destroy(UUID entityIdentifier) {
		return getComponent().destroy(entityIdentifier);
	}
}