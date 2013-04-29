package uk.co.elionline.gears.entities.processing.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public class BehaviourProcessingContextImpl implements
		BehaviourProcessingContext {
	private BehaviourComponent behaviour;
	private EntityManager entityManager;
	private StripedReadWriteLockRelease<StateComponent<?>> locks;

	public BehaviourProcessingContextImpl(BehaviourComponent behaviour,
			final EntityManager entityManager,
			StripedReadWriteLockRelease<StateComponent<?>> locks) {
		this.behaviour = behaviour;
		this.entityManager = new EntityManagerWrapper(entityManager);
		this.locks = locks;
	}

	@Override
	public EntityManager getEntityManager() {
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
	public Set<? extends UUID> getEntities() {
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
	protected Set<? extends UUID> getEntitiesWithBehaviourAndState(
			BehaviourComponent behaviour) {
		Set<StateComponent<?>> stateDependencies = behaviour.getStateDependencies();

		Set<UUID> entities;

		if (!stateDependencies.isEmpty()) {
			entities = new HashSet<>(getEntityManager().getStateManager()
					.getEntitiesWith(stateDependencies));

			if (!getEntityManager().getBehaviourManager().isUniversal(behaviour)) {
				entities.retainAll(getEntityManager().getBehaviourManager()
						.getEntitiesExplicitlyWith(behaviour));
			}
		} else {
			if (getEntityManager().getBehaviourManager().isUniversal(behaviour)) {
				entities = getEntityManager().getAll();
			} else {
				entities = getEntityManager().getBehaviourManager()
						.getEntitiesExplicitlyWith(behaviour);
			}
		}

		return entities;
	}
}
