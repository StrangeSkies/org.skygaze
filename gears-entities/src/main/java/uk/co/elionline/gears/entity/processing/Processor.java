package uk.co.elionline.gears.entity.processing;

import java.util.Collection;

import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.scheduling.Scheduler;

public interface Processor {
	public abstract boolean startProcessing(EntityManager entityManager);

	public abstract boolean startProcessing(EntityManager entityManager,
			Collection<Scheduler> processors);

	public abstract boolean stopProcessing();
}