package uk.co.strangeskies.extengine.entity;

import java.util.Collection;

import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;

public interface Processor {
	public abstract boolean startProcessing(EntityComponentSystem entityManager);

	public abstract boolean startProcessing(EntityComponentSystem entityManager,
			Collection<Scheduler> processors);

	public abstract boolean stopProcessing();
}