package uk.co.strangeskies.extengine.entity.behaviour.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourProcess;
import uk.co.strangeskies.extengine.entity.state.StateComponent;

public class BehaviourComponentImpl implements BehaviourComponent {
	private final String name;
	private final String description;
	private final BehaviourProcess process;

	private final Set<BehaviourComponent> behaviourDependencies;
	private final Set<BehaviourComponent> behaviourDependents;

	private final Set<StateComponent<?>> readDependencies;
	private final Set<StateComponent<?>> writeDependencies;

	private final Set<StateComponent<?>> optionalReadDependencies;
	private final Set<StateComponent<?>> optionalWriteDependencies;

	public BehaviourComponentImpl(String name, String description,
			BehaviourProcess process) {
		this.name = name;
		this.description = description;

		this.process = process;

		this.behaviourDependencies = new HashSet<>();
		this.behaviourDependents = new HashSet<>();

		this.readDependencies = new HashSet<>();
		this.writeDependencies = new HashSet<>();

		this.optionalReadDependencies = new HashSet<>();
		this.optionalWriteDependencies = new HashSet<>();
	}

	public BehaviourComponentImpl(String name, String description,
			BehaviourProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents) {
		this(name, description, process);

		this.behaviourDependencies.addAll(behaviourDependencies);
		this.behaviourDependents.addAll(behaviourDependents);
	}

	public BehaviourComponentImpl(String name, String description,
			BehaviourProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents,
			Collection<? extends StateComponent<?>> readDependencies,
			Collection<? extends StateComponent<?>> writeDependencies) {
		this(name, description, process, behaviourDependencies, behaviourDependents);

		this.readDependencies.addAll(readDependencies);
		this.writeDependencies.addAll(writeDependencies);

		// Add all indirect read and write dependencies
		for (StateComponent<?> readDependency : readDependencies) {
			optionalReadDependencies.addAll(readDependency.getReadDependencies());
		}
		for (StateComponent<?> writeDependency : writeDependencies) {
			optionalWriteDependencies.addAll(writeDependency.getWriteDependencies());
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
	public BehaviourComponentImpl(String name, String description,
			BehaviourProcess process,
			Collection<? extends BehaviourComponent> behaviourDependencies,
			Collection<? extends BehaviourComponent> behaviourDependents,
			Collection<? extends StateComponent<?>> readDependencies,
			Collection<? extends StateComponent<?>> writeDependencies,
			Collection<? extends StateComponent<?>> optionalReadDependencies,
			Collection<? extends StateComponent<?>> optionalWriteDependencies) {
		this(name, description, process, behaviourDependencies,
				behaviourDependents, readDependencies, writeDependencies);

		// Add all explicitly added indirect read and write dependencies
		for (StateComponent<?> readDependency : optionalReadDependencies) {
			if (this.optionalReadDependencies.add(readDependency)) {
				this.optionalReadDependencies.addAll(readDependency
						.getReadDependencies());
			}
		}
		for (StateComponent<?> writeDependency : optionalWriteDependencies) {
			if (this.optionalWriteDependencies.add(writeDependency)) {
				this.optionalWriteDependencies.addAll(writeDependency
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
	public BehaviourProcess getProcess() {
		return process;
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
	public final Set<StateComponent<?>> getOptionalStateDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(readDependencies);
		stateDependencies.addAll(writeDependencies);
		stateDependencies.addAll(optionalReadDependencies);
		stateDependencies.addAll(optionalWriteDependencies);

		return stateDependencies;
	}

	/**
	 * Get the set of state components which this behaviour component needs read
	 * access to for the entities it operates on.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getReadDependencies() {
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
	public final Set<StateComponent<?>> getOptionalReadDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(readDependencies);
		stateDependencies.addAll(optionalReadDependencies);

		return stateDependencies;
	}

	/**
	 * Get the set of state components which this behaviour component needs write
	 * access to for the entities it operates on.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getWriteDependencies() {
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
	public final Set<StateComponent<?>> getOptionalWriteDependencies() {
		Set<StateComponent<?>> stateDependencies = new HashSet<>(writeDependencies);
		stateDependencies.addAll(optionalWriteDependencies);

		return stateDependencies;
	}
}