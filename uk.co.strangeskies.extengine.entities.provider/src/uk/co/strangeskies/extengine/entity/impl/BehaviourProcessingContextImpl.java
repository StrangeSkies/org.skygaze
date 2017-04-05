package uk.co.strangeskies.extengine.entity.impl;

import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourProcessingContext;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.flowcontrol.StripedReadWriteLockRelease;

public class BehaviourProcessingContextImpl implements
		BehaviourProcessingContext {
	private final BehaviourComponent behaviour;
	private final EntityComponentSystem entityManager;
	private final StripedReadWriteLockRelease<StateComponent<?, ?>> locks;

	public BehaviourProcessingContextImpl(BehaviourComponent behaviour,
			final EntityComponentSystem entityManager,
			StripedReadWriteLockRelease<StateComponent<?, ?>> locks) {
		this.behaviour = behaviour;
		this.entityManager = new EntityComponentSystemWrapper(entityManager, locks);
		this.locks = locks;
	}

	@Override
	public EntityComponentSystem entity() {
		return entityManager;
	}

	@Override
	public StripedReadWriteLockRelease<StateComponent<?, ?>> lockReleases() {
		return locks;
	}

	@Override
	public BehaviourComponent processingBehaviour() {
		return behaviour;
	}

	@Override
	public Set<? extends Entity> participatingEntities() {
		return getEntitiesWithBehaviourAndState(behaviour);
	}

	/**
	 * Get the set of all entities which currently have the given behaviour
	 * attached (either explicitly or universally), and which have all the state
	 * components attached necessary to satisfy the behaviours direct
	 * dependencies.
	 * 
	 * @param behaviourComponent
	 *          The behaviour component for which we want to find all applicable
	 *          entities.
	 * @return The set of entities which are attached to this behaviour.
	 */
	protected Set<? extends Entity> getEntitiesWithBehaviourAndState(
			BehaviourComponent behaviour) {
		Set<StateComponent<?, ?>> stateDependencies = behaviour.getStateDependencies();

		Set<Entity> entities;

		if (!stateDependencies.isEmpty()) {
			entities = new HashSet<>(entity().state().getEntitiesWith(
					stateDependencies));

			if (!entity().behaviour().isUniversal(behaviour)) {
				entities.retainAll(entity().behaviour().getEntitiesExplicitlyWith(
						behaviour));
			}
		} else {
			if (entity().behaviour().isUniversal(behaviour)) {
				entities = entity().getAll();
			} else {
				entities = entity().behaviour().getEntitiesExplicitlyWith(behaviour);
			}
		}

		return entities;
	}
}
