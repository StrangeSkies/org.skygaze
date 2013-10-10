package uk.co.strangeskies.gears.entity.processing.impl;

import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.strangeskies.gears.entity.management.EntityManager;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public class BehaviourProcessingContextImpl implements
		BehaviourProcessingContext {
	private final BehaviourComponent behaviour;
	private final EntityManager entityManager;
	private final StripedReadWriteLockRelease<StateComponent<?>> locks;

	public BehaviourProcessingContextImpl(BehaviourComponent behaviour,
			final EntityManager entityManager,
			StripedReadWriteLockRelease<StateComponent<?>> locks) {
		this.behaviour = behaviour;
		this.entityManager = new EntityManagerProcessingWrapper(entityManager, locks);
		this.locks = locks;
	}

	@Override
	public EntityManager entities() {
		return entityManager;
	}

	@Override
	public StripedReadWriteLockRelease<StateComponent<?>> getLockReleases() {
		return locks;
	}

	@Override
	public BehaviourComponent getBehaviour() {
		return behaviour;
	}

	@Override
	public Set<? extends Entity> getEntities() {
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
		Set<StateComponent<?>> stateDependencies = behaviour.getStateDependencies();

		Set<Entity> entities;

		if (!stateDependencies.isEmpty()) {
			entities = new HashSet<>(entities().state().getEntitiesWith(
					stateDependencies));

			if (!entities().behaviour().isUniversal(behaviour)) {
				entities.retainAll(entities().behaviour().getEntitiesExplicitlyWith(
						behaviour));
			}
		} else {
			if (entities().behaviour().isUniversal(behaviour)) {
				entities = entities().getAll();
			} else {
				entities = entities().behaviour().getEntitiesExplicitlyWith(behaviour);
			}
		}

		return entities;
	}
}
