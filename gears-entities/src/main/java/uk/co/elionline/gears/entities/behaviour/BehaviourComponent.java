package uk.co.elionline.gears.entities.behaviour;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.EntityManagerProcessingContext;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.utilities.Described;
import uk.co.elionline.gears.utilities.Factory;
import uk.co.elionline.gears.utilities.Named;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public class BehaviourComponent implements Named, Described {
	private final String name;
	private final String description;
	private final BehaviourComponentProcess process;

	private final Set<BehaviourComponent> behaviourDependencies;
	private final Set<BehaviourComponent> behaviourDependents;

	private final Set<StateComponent<?>> readDependencies;
	private final Set<StateComponent<?>> writeDependencies;

	private final Set<StateComponent<?>> indirectReadDependencies;
	private final Set<StateComponent<?>> indirectWriteDependencies;

	public BehaviourComponent(String name, String description,
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

	public BehaviourComponent(String name, String description,
			BehaviourComponentProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents) {
		this(name, description, process);

		this.behaviourDependencies.addAll(behaviourDependencies);
		this.behaviourDependents.addAll(behaviourDependents);

		// Add all indirect behaviour dependencies and dependents
		for (BehaviourComponent dependency : behaviourDependencies) {
			this.behaviourDependencies.addAll(dependency.getBehaviourDependencies());
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
			dependency.behaviourDependents.addAll(behaviourDependents);
			dependency.behaviourDependents.add(this);
		}
		for (BehaviourComponent dependent : behaviourDependents) {
			dependent.behaviourDependencies.addAll(behaviourDependencies);
			dependent.behaviourDependencies.add(this);
		}
	}

	public BehaviourComponent(String name, String description,
			BehaviourComponentProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents,
			Collection<? extends StateComponent<?>> readDependencies,
			Collection<? extends StateComponent<?>> writeDependencies) {
		this(name, description, process, behaviourDependencies, behaviourDependents);

		this.readDependencies.addAll(readDependencies);
		this.writeDependencies.addAll(writeDependencies);

		// Add all indirect read and write dependencies
		for (StateComponent<?> readDependency : readDependencies) {
			indirectReadDependencies.addAll(readDependency.getReadDependencies());
		}
		for (StateComponent<?> writeDependency : writeDependencies) {
			indirectWriteDependencies.addAll(writeDependency.getWriteDependencies());
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
	 *          that they can be read from when this behaviour is applied to them.
	 * @param writeDependencies
	 *          States which this behaviour requires an Entity to possess such
	 *          that they can be written to when this behaviour is applied to
	 *          them.
	 * @param behaviourDependencies
	 *          The set of behaviours which this behaviour should only be executed
	 *          after the completion of, if they are present in the same execution
	 *          DAG. (i.e. if they are scheduled to process on the same tick.)
	 * @param behaviourDependents
	 *          The set of behaviours which should not be processed until this
	 *          behaviour completes, if they are present in the same execution
	 *          DAG. (i.e. if they are scheduled to process on the same tick.)
	 */
	public BehaviourComponent(String name, String description,
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

	public final void process(Set<? extends UUID> entities,
			EntityManagerProcessingContext context) {
		process.process(entities, context);
	}

	/**
	 * Get the set of behaviours which this behaviour should only be executed
	 * after the completion of, if they are present in the same execution DAG.
	 * (i.e. if they are scheduled to process on the same tick.)
	 * 
	 * @return
	 */
	public final Set<BehaviourComponent> getBehaviourDependencies() {
		return Collections.unmodifiableSet(behaviourDependencies);
	}

	public final void addBehaviourDependency(
			BehaviourComponent behaviourDependency) {
		addBehaviourDependencies(behaviourDependency);
	}

	public final void addBehaviourDependencies(
			BehaviourComponent... behaviourDependencies) {
		addBehaviourDependencies(Arrays.asList(behaviourDependencies));
	}

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
			dependency.behaviourDependents.addAll(behaviourDependents);
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
	public final Set<BehaviourComponent> getBehaviourDependents() {
		return Collections.unmodifiableSet(behaviourDependents);
	}

	public final void addBehaviourDependents(BehaviourComponent behaviourDependent) {
		addBehaviourDependents(behaviourDependent);
	}

	public final void addBehaviourDependents(
			BehaviourComponent... behaviourDependents) {
		addBehaviourDependents(Arrays.asList(behaviourDependents));
	}

	public final void addBehaviourDependents(
			Collection<BehaviourComponent> behaviourDependents) {
		Set<BehaviourComponent> newIndirectBehaviourDependents = new HashSet<>();
		newIndirectBehaviourDependents.addAll(behaviourDependents);

		// Add all indirect behaviour dependencies
		for (BehaviourComponent dependent : behaviourDependents) {
			newIndirectBehaviourDependents.addAll(dependent.getBehaviourDependents());
		}

		// Throw an exception if we detect a cycle
		if (newIndirectBehaviourDependents.contains(this)) {
			throw new IllegalArgumentException("Dependency graph cycle detected");
		}

		// Add reciprocals of all new direct and indirect dependencies
		for (BehaviourComponent dependent : behaviourDependents) {
			dependent.behaviourDependencies.addAll(behaviourDependencies);
		}

		this.behaviourDependents.addAll(newIndirectBehaviourDependents);
	}

	/**
	 * Get the set of state components which this behaviour component needs read
	 * or write access to for the entities it operates on.
	 * 
	 * @return
	 */
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
	public final Set<StateComponent<?>> getIndirectStateReadDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(readDependencies);
		stateDependencies.addAll(indirectReadDependencies);

		return stateDependencies;
	}

	/**
	 * Get the set of state components which this behaviour component needs write
	 * access to for the entities it operates on.
	 * 
	 * @return
	 */
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
	public final Set<StateComponent<?>> getIndirectStateWriteDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(writeDependencies);
		stateDependencies.addAll(indirectWriteDependencies);

		return stateDependencies;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements Factory<BehaviourComponent> {
		private String name;
		private String description;

		private BehaviourComponentProcess process;

		private Collection<? extends BehaviourComponent> behaviourDependencies;
		private Collection<? extends BehaviourComponent> behaviourDependents;

		private Collection<? extends StateComponent<?>> readDependencies;
		private Collection<? extends StateComponent<?>> writeDependencies;

		private Collection<? extends StateComponent<?>> indirectReadDependencies;
		private Collection<? extends StateComponent<?>> indirectWriteDependencies;

		protected Builder() {
			name = "";
			description = "";

			this.behaviourDependencies = Collections.emptySet();
			this.behaviourDependents = Collections.emptySet();

			this.readDependencies = Collections.emptySet();
			this.writeDependencies = Collections.emptySet();

			this.indirectReadDependencies = Collections.emptySet();
			this.indirectWriteDependencies = Collections.emptySet();
		}

		public Builder process(BehaviourComponentProcess process) {
			this.process = process;

			return this;
		}

		public Builder name(String name) {
			this.name = name;

			return this;
		}

		public Builder description(String description) {
			this.description = description;

			return this;
		}

		public Builder behaviourDependencies(
				Collection<? extends BehaviourComponent> behaviourDependencies) {
			this.behaviourDependencies = behaviourDependencies;

			return this;
		}

		public Builder behaviourDependencies(
				BehaviourComponent... behaviourDependencies) {
			return behaviourDependencies(Arrays.asList(behaviourDependencies));
		}

		public Builder behaviourDependents(
				Collection<? extends BehaviourComponent> behaviourDependents) {
			this.behaviourDependents = behaviourDependents;

			return this;
		}

		public Builder behaviourDependents(
				BehaviourComponent... behaviourDependents) {
			return behaviourDependents(Arrays.asList(behaviourDependents));
		}

		public Builder readDependencies(
				Collection<? extends StateComponent<?>> readDependencies) {
			this.readDependencies = readDependencies;

			return this;
		}

		public Builder readDependencies(StateComponent<?>... readDependencies) {
			return readDependencies(Arrays.asList(readDependencies));
		}

		public Builder writeDependencies(
				Collection<? extends StateComponent<?>> writeDependencies) {
			this.writeDependencies = writeDependencies;

			return this;
		}

		public Builder writeDependencies(StateComponent<?>... writeDependencies) {
			return writeDependencies(Arrays.asList(writeDependencies));
		}

		public Builder indirectReadDependencies(
				Collection<? extends StateComponent<?>> readDependencies) {
			this.indirectReadDependencies = readDependencies;

			return this;
		}

		public Builder indirectReadDependencies(
				StateComponent<?>... readDependencies) {
			return indirectReadDependencies(Arrays.asList(readDependencies));
		}

		public Builder indirectWriteDependencies(
				Collection<? extends StateComponent<?>> writeDependencies) {
			this.indirectWriteDependencies = writeDependencies;

			return this;
		}

		public Builder indirectWriteDependencies(
				StateComponent<?>... writeDependencies) {
			return indirectWriteDependencies(Arrays.asList(writeDependencies));
		}

		@Override
		public BehaviourComponent create() {
			return new BehaviourComponent(name, description, process,
					behaviourDependencies, behaviourDependents, readDependencies,
					writeDependencies, indirectReadDependencies,
					indirectWriteDependencies);
		}
	}
}
