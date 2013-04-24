package uk.co.elionline.gears.entities.behaviour.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.gears.entities.management.EntityProcessingContext;
import uk.co.elionline.gears.entities.state.StateComponent;

public class BehaviourComponentImpl implements BehaviourComponent {
	private final String name;
	private final String description;
	private final BehaviourComponentProcess process;

	private final Set<BehaviourComponent> behaviourDependencies;
	private final Set<BehaviourComponent> behaviourDependents;

	private final Set<StateComponent<?>> readDependencies;
	private final Set<StateComponent<?>> writeDependencies;

	private final Set<StateComponent<?>> indirectReadDependencies;
	private final Set<StateComponent<?>> indirectWriteDependencies;

	public BehaviourComponentImpl(String name, String description,
			BehaviourComponentProcess process) {
		this.name = name;
		this.description = description;

		this.process = process;

		this.behaviourDependencies = new HashSet<>();
		this.behaviourDependents = new HashSet<>();

		this.readDependencies = new HashSet<>();
		this.writeDependencies = new HashSet<>();

		this.indirectReadDependencies = new HashSet<>();
		this.indirectWriteDependencies = new HashSet<>();
	}

	public BehaviourComponentImpl(String name, String description,
			BehaviourComponentProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents) {
		this(name, description, process);

		this.behaviourDependencies.addAll(behaviourDependencies);
		this.behaviourDependents.addAll(behaviourDependents);

		// Add all indirect behaviour dependencies and dependents
		for (BehaviourComponent dependency : behaviourDependencies) {
			this.behaviourDependencies
					.addAll(dependency.getBehaviourDependencies());
		}
		for (BehaviourComponent dependent : behaviourDependents) {
			this.behaviourDependents.addAll(dependent.getBehaviourDependents());
		}

		// Throw an exception if we detect a cycle
		if (this.behaviourDependencies.contains(this)
				|| this.behaviourDependents.contains(this)) {
			throw new IllegalArgumentException("Dependency graph cycle detected");
		}

		// Add reciprocals of all new direct and indirect dependencies
		for (BehaviourComponent dependency : behaviourDependencies) {
			dependency.getBehaviourDependents().addAll(behaviourDependents);
			dependency.getBehaviourDependents().add(this);
		}
		for (BehaviourComponent dependent : behaviourDependents) {
			dependent.getBehaviourDependencies().addAll(behaviourDependencies);
			dependent.getBehaviourDependencies().add(this);
		}
	}

	public BehaviourComponentImpl(String name, String description,
			BehaviourComponentProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents,
			Collection<? extends StateComponent<?>> readDependencies,
			Collection<? extends StateComponent<?>> writeDependencies) {
		this(name, description, process, behaviourDependencies,
				behaviourDependents);

		this.readDependencies.addAll(readDependencies);
		this.writeDependencies.addAll(writeDependencies);

		// Add all indirect read and write dependencies
		for (StateComponent<?> readDependency : readDependencies) {
			indirectReadDependencies.addAll(readDependency.getReadDependencies());
		}
		for (StateComponent<?> writeDependency : writeDependencies) {
			indirectWriteDependencies
					.addAll(writeDependency.getWriteDependencies());
		}
	}

	/**
	 * Create a new behaviour component with the provided parameters.
	 * 
	 * @param name
	 *          The name of this behaviour
	 * @param description
	 *          A description of what this behaviour does
	 * @param process
	 *          The implementation of this behaviour
	 * @param readDependencies
	 *          States which this behaviour requires an Entity to possess such
	 *          that they can be read from when this behaviour is applied to
	 *          them.
	 * @param writeDependencies
	 *          States which this behaviour requires an Entity to possess such
	 *          that they can be written to when this behaviour is applied to
	 *          them.
	 * @param behaviourDependencies
	 *          The set of behaviours which this behaviour should only be
	 *          executed after the completion of, if they are present in the
	 *          same execution DAG. (i.e. if they are scheduled to process on
	 *          the same tick.)
	 * @param behaviourDependents
	 *          The set of behaviours which should not be processed until this
	 *          behaviour completes, if they are present in the same execution
	 *          DAG. (i.e. if they are scheduled to process on the same tick.)
	 */
	public BehaviourComponentImpl(String name, String description,
			BehaviourComponentProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents,
			Collection<? extends StateComponent<?>> readDependencies,
			Collection<? extends StateComponent<?>> writeDependencies,
			Collection<? extends StateComponent<?>> indirectReadDependencies,
			Collection<? extends StateComponent<?>> indirectWriteDependencies) {
		this(name, description, process, behaviourDependencies,
				behaviourDependents, readDependencies, writeDependencies);

		// Add all explicitly added indirect read and write dependencies
		for (StateComponent<?> readDependency : indirectReadDependencies) {
			if (this.indirectReadDependencies.add(readDependency)) {
				this.indirectReadDependencies.addAll(readDependency
						.getReadDependencies());
			}
		}
		for (StateComponent<?> writeDependency : indirectWriteDependencies) {
			if (this.indirectWriteDependencies.add(writeDependency)) {
				this.indirectWriteDependencies.addAll(writeDependency
						.getWriteDependencies());
			}
		}
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final String toString() {
		return getName();
	}

	@Override
	public final String getDescription() {
		return description;
	}

	@Override
	public final boolean equals(Object object) {
		return super.equals(object);
	}

	@Override
	public final void process(Set<? extends UUID> entities,
			EntityProcessingContext context) {
		process.process(entities, context);
	}

	/**
	 * Get the set of behaviours which this behaviour should only be executed
	 * after the completion of, if they are present in the same execution DAG.
	 * (i.e. if they are scheduled to process on the same tick.)
	 * 
	 * @return
	 */
	@Override
	public final Set<BehaviourComponent> getBehaviourDependencies() {
		return Collections.unmodifiableSet(behaviourDependencies);
	}

	@Override
	public final void addBehaviourDependency(
			BehaviourComponent behaviourDependency) {
		addBehaviourDependencies(behaviourDependency);
	}

	@Override
	public final void addBehaviourDependencies(
			BehaviourComponent... behaviourDependencies) {
		addBehaviourDependencies(Arrays.asList(behaviourDependencies));
	}

	@Override
	public final void addBehaviourDependencies(
			Collection<BehaviourComponent> behaviourDependencies) {
		Set<BehaviourComponent> newIndirectBehaviourDependencies = new HashSet<>();
		newIndirectBehaviourDependencies.addAll(behaviourDependencies);

		// Add all indirect behaviour dependencies
		for (BehaviourComponent dependency : behaviourDependencies) {
			newIndirectBehaviourDependencies.addAll(dependency
					.getBehaviourDependencies());
		}

		// Throw an exception if we detect a cycle
		if (newIndirectBehaviourDependencies.contains(this)) {
			throw new IllegalArgumentException("Dependency graph cycle detected");
		}

		// Add reciprocals of all new direct and indirect dependencies
		for (BehaviourComponent dependency : behaviourDependencies) {
			dependency.getBehaviourDependents().addAll(behaviourDependents);
		}

		this.behaviourDependencies.addAll(newIndirectBehaviourDependencies);
	}

	/**
	 * Get the set of behaviours which should not be processed until this
	 * behaviour completes, if they are present in the same execution DAG. (e.g.
	 * if they are scheduled cyclicly to process on the same tick.)
	 * 
	 * @return
	 */
	@Override
	public final Set<BehaviourComponent> getBehaviourDependents() {
		return Collections.unmodifiableSet(behaviourDependents);
	}

	@Override
	public final void addBehaviourDependent(
			BehaviourComponent behaviourDependent) {
		addBehaviourDependents(behaviourDependent);
	}

	@Override
	public final void addBehaviourDependents(
			BehaviourComponent... behaviourDependents) {
		addBehaviourDependents(Arrays.asList(behaviourDependents));
	}

	@Override
	public final void addBehaviourDependents(
			Collection<BehaviourComponent> behaviourDependents) {
		Set<BehaviourComponent> newIndirectBehaviourDependents = new HashSet<>();
		newIndirectBehaviourDependents.addAll(behaviourDependents);

		// Add all indirect behaviour dependencies
		for (BehaviourComponent dependent : behaviourDependents) {
			newIndirectBehaviourDependents.addAll(dependent
					.getBehaviourDependents());
		}

		// Throw an exception if we detect a cycle
		if (newIndirectBehaviourDependents.contains(this)) {
			throw new IllegalArgumentException("Dependency graph cycle detected");
		}

		// Add reciprocals of all new direct and indirect dependencies
		for (BehaviourComponent dependent : behaviourDependents) {
			dependent.getBehaviourDependencies().addAll(behaviourDependencies);
		}

		this.behaviourDependents.addAll(newIndirectBehaviourDependents);
	}

	/**
	 * Get the set of state components which this behaviour component needs read
	 * or write access to for the entities it operates on.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getStateDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(readDependencies);
		stateDependencies.addAll(writeDependencies);

		return stateDependencies;
	}

	/**
	 * Get the set of state components which this behaviour component does not
	 * directly require an entity to possess to operate on it, but which locks
	 * must be obtained for due to indirect dependencies through those state
	 * dependencies which it does require.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getIndirectStateDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(readDependencies);
		stateDependencies.addAll(writeDependencies);
		stateDependencies.addAll(indirectReadDependencies);
		stateDependencies.addAll(indirectWriteDependencies);

		return stateDependencies;
	}

	/**
	 * Get the set of state components which this behaviour component needs read
	 * access to for the entities it operates on.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getStateReadDependencies() {
		return Collections.unmodifiableSet(readDependencies);
	}

	/**
	 * Get the set of state components which this behaviour component does not
	 * directly require an entity to possess to operate on it, but which locks
	 * must be obtained for due to indirect dependencies through those read
	 * dependencies which it does require.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getIndirectStateReadDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(readDependencies);
		stateDependencies.addAll(indirectReadDependencies);

		return stateDependencies;
	}

	/**
	 * Get the set of state components which this behaviour component needs
	 * write access to for the entities it operates on.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getStateWriteDependencies() {
		return Collections.unmodifiableSet(writeDependencies);
	}

	/**
	 * Get the set of state components which this behaviour component does not
	 * directly require an entity to possess to operate on it, but which locks
	 * must be obtained for due to indirect dependencies through those write
	 * dependencies which it does require.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getIndirectStateWriteDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(
				writeDependencies);
		stateDependencies.addAll(indirectWriteDependencies);

		return stateDependencies;
	}
}