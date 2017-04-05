package uk.co.strangeskies.extengine.entity.impl;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.flowcontrol.LimitedReadWriteLockReleaseWrapper;
import uk.co.strangeskies.utilities.flowcontrol.StripedReadWriteLock;

public class ScheduleProcessingContextImpl implements ScheduleProcessingContext {
	private final EntityComponentSystem entityManager;
	private final StripedReadWriteLock<StateComponent<?, ?>> locks;
	private final Set<BehaviourComponent> behaviours;

	public ScheduleProcessingContextImpl(Scheduler scheduler,
			EntityComponentSystem entityManager, StripedReadWriteLock<StateComponent<?, ?>> locks) {
		this.entityManager = entityManager;
		this.locks = locks;
		behaviours = entityManager.behaviour().getBehavioursForScheduler(scheduler);
	}

	@Override
	public void processBehaviour(final BehaviourComponent behaviour) {
		try {
			LimitedReadWriteLockReleaseWrapper<StateComponent<?, ?>> locks = new LimitedReadWriteLockReleaseWrapper<>(
					this.locks, behaviour.getOptionalReadDependencies(),
					behaviour.getOptionalWriteDependencies());

			behaviour.getProcess().process(
					new BehaviourProcessingContextImpl(behaviour, entityManager, locks));

			locks.release();
		} catch (InterruptedException e) {
			// TODO
		}
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return behaviours;
	}
}
