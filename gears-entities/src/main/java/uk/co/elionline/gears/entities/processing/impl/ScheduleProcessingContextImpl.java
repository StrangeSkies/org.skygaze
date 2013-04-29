package uk.co.elionline.gears.entities.processing.impl;

import java.util.Set;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.scheduling.ScheduleProcessingContext;
import uk.co.elionline.gears.entities.scheduling.Scheduler;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLock;

public class ScheduleProcessingContextImpl implements ScheduleProcessingContext {
	private final EntityManager entityManager;
	private final StripedReadWriteLock<StateComponent<?>> locks;
	private final Set<BehaviourComponent> behaviours;

	public ScheduleProcessingContextImpl(Scheduler scheduler,
			EntityManager entityManager, StripedReadWriteLock<StateComponent<?>> locks) {
		this.entityManager = entityManager;
		this.locks = locks;
		behaviours = getEntityManager().getBehaviourManager()
				.getBehavioursForScheduler(scheduler);
	}

	@Override
	public void processBehaviour(final BehaviourComponent behaviour)
			throws InterruptedException {
		Set<StateComponent<?>> readKeys = behaviour
				.getIndirectStateReadDependencies();
		Set<StateComponent<?>> writeKeys = behaviour
				.getIndirectStateWriteDependencies();

		locks.obtainLocks(readKeys, writeKeys);
		behaviour.getProcess()
				.process(
						new BehaviourProcessingContextImpl(behaviour, getEntityManager(),
								locks));
		locks.releaseLocks(readKeys, writeKeys);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return behaviours;
	}
}
