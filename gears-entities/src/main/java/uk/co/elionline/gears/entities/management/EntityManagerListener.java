package uk.co.elionline.gears.entities.management;

import java.util.Collection;
import java.util.UUID;

import uk.co.elionline.gears.entities.processing.EntityProcessor;

public interface EntityManagerListener {
	public void registered(EntityManager entityManager);

	public void unregistered(EntityManager entityManager);

	public void entityCreated(EntityManager entityManager, UUID entityIdentifier);

	public void entityDestroyed(EntityManager entityManager, UUID entityIdentifier);

	public void processingStarted(EntityManager entityManager,
			Collection<EntityProcessor> processors);

	public void processingStopped(EntityManager entityManager);
}
