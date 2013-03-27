package uk.co.elionline.gears.entities.processing;

import uk.co.elionline.gears.entities.EntityManager;
import uk.co.elionline.gears.entities.processing.scheduling.BehaviourScheduler;

public class CyclicProcessor implements EntityProcessor {
	private boolean stopped;

	private final BehaviourScheduler scheduler;

	public CyclicProcessor(BehaviourScheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public final void start(EntityManager entityManager) {
		setStopped(false);
		do {
			processCycle(entityManager);
		} while (!isStopped());
	}

	@Override
	public final void stop() {
		setStopped(true);
	}

	protected final synchronized boolean isStopped() {
		return stopped;
	}

	protected final synchronized void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	protected void processCycle(EntityManager entityManager) {
		scheduler.setBehaviours(entityManager.getBehaviourManager()
				.getBehavioursForProcessor(this));
		scheduler.setEntityManager(entityManager);
		scheduler.start();
	}
}
