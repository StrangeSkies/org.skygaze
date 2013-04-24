package uk.co.elionline.gears.entities.management.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.management.EntityBehaviourManager;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.management.EntityStateManager;
import uk.co.elionline.gears.entities.processing.EntityProcessor;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLock;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public abstract class AbstractEntityManager implements EntityManager {
	private final EntityStateManager stateManager;
	private final EntityBehaviourManager behaviourManager;

	private final Set<EntityProcessor> processors;
	private boolean wasLockingEnabled;

	public AbstractEntityManager(EntityStateManager stateManager,
			EntityBehaviourManager behaviourManager) {
		this.stateManager = stateManager;
		this.behaviourManager = behaviourManager;
		processors = new HashSet<>();
	}

	@Override
	public EntityStateManager getStateManager() {
		return stateManager;
	}

	@Override
	public EntityBehaviourManager getBehaviourManager() {
		return behaviourManager;
	}

	/**
	 * Create an entity to be managed by this class.
	 */
	@Override
	public final UUID create() {
		UUID identifier = UUID.randomUUID();

		add(identifier);

		return identifier;
	}

	protected abstract void add(UUID identifier);

	/**
	 * Remove an entity from this managers management.
	 * 
	 * @param entityIdentifier
	 *          The identity of the Entity we want to remove.
	 * @return True if the entity was removed, false otherwise.
	 */
	@Override
	public final boolean destroy(UUID entity) {
		boolean removed = detach(entity);

		if (removed) {
			getStateManager().detachAll(entity);

			getBehaviourManager().detachAll(entity);
		}

		return removed;
	}

	protected abstract boolean detach(UUID entity);

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
	protected Set<? extends UUID> getWithBehaviourAndState(
			BehaviourComponent behaviour) {
		Set<StateComponent<?>> stateDependencies = behaviour.getStateDependencies();

		Set<UUID> entities;

		if (!stateDependencies.isEmpty()) {
			entities = new HashSet<>(getStateManager().getEntitiesWith(
					stateDependencies));

			if (!getBehaviourManager().isUniversal(behaviour)) {
				entities.retainAll(getBehaviourManager().getEntitiesExplicitlyWith(
						behaviour));
			}
		} else {
			if (getBehaviourManager().isUniversal(behaviour)) {
				entities = getAll();
			} else {
				entities = getBehaviourManager().getEntitiesExplicitlyWith(behaviour);
			}
		}

		return entities;
	}

	@Override
	public boolean startProcessing() {
		return startProcessing(getBehaviourManager().getAllProcessors());
	}

	@Override
	public boolean startProcessing(Collection<EntityProcessor> processors) {
		synchronized (this.processors) {
			if (!this.processors.isEmpty()) {
				return false;
			}

			wasLockingEnabled = getStateManager().isLockingEnabled();
			getStateManager().setLockingEnabled(true);

			boolean addedProcesses = this.processors.addAll(processors);

			for (final EntityProcessor processor : this.processors) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						processor.start(AbstractEntityManager.this);
					}
				}).start();
			}

			return addedProcesses;
		}
	}

	@Override
	public boolean stopProcessing() {
		synchronized (this.processors) {
			if (processors.isEmpty()) {
				return false;
			}

			for (final EntityProcessor processor : processors) {
				processor.stop();
			}
			processors.clear();

			getStateManager().setLockingEnabled(wasLockingEnabled);

			return true;
		}
	}

	@Override
	public void processBehaviour(BehaviourComponent behaviour)
			throws InterruptedException {
		Set<StateComponent<?>> readKeys = behaviour
				.getIndirectStateReadDependencies();
		Set<StateComponent<?>> writeKeys = behaviour
				.getIndirectStateWriteDependencies();
		StripedReadWriteLock<StateComponent<?>> locks = getStateManager()
				.getLocks();
		locks.obtainLocks(readKeys, writeKeys);
		behaviour.process(getWithBehaviourAndState(behaviour), this);
		locks.releaseLocks(readKeys, writeKeys);
	}
}
