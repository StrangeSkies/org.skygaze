package uk.co.elionline.gears.entities.processing;

import uk.co.elionline.gears.entities.EntityManager;

public interface EntityProcessor {
	public abstract void start(EntityManager entityManager);

	public abstract void stop();
}
