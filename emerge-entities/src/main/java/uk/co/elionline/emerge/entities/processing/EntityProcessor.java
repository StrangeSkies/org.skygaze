package uk.co.elionline.emerge.entities.processing;

import uk.co.elionline.emerge.entities.EntityManager;

public interface EntityProcessor {
	public abstract void start(EntityManager entityManager);

	public abstract void stop();
}
