package uk.co.strangeskies.gears.entity.behaviour;

import java.util.Set;

import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.utilities.Described;
import uk.co.strangeskies.utilities.Named;

public interface BehaviourComponent extends Named, Described {
	public BehaviourProcess getProcess();

	/**
	 * Get the set of behaviours which this behaviour should only be executed
	 * after the completion of, if they are present in the same execution DAG.
	 * (i.e. if they are scheduled to process on the same tick.)
	 * 
	 * @return
	 */
	public Set<BehaviourComponent> getBehaviourDependencies();

	/**
	 * Get the set of behaviours which should not be processed until this
	 * behaviour completes, if they are present in the same execution DAG. (e.g.
	 * if they are scheduled cyclicly to process on the same tick.)
	 * 
	 * @return
	 */
	public Set<BehaviourComponent> getBehaviourDependents();

	/**
	 * Get the set of state components which this behaviour component needs read
	 * or write access to for the entities it operates on.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getStateDependencies();

	/**
	 * Get the set of state components which this behaviour component does not
	 * directly require an entity to possess to operate on it, but which locks
	 * must be obtained for due to indirect dependencies through those state
	 * dependencies which it does require.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getOptionalStateDependencies();

	/**
	 * Get the set of state components which this behaviour component needs read
	 * access to for the entities it operates on.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getReadDependencies();

	/**
	 * Get the set of state components which this behaviour component does not
	 * directly require an entity to possess to operate on it, but which locks
	 * must be obtained for due to indirect dependencies through those read
	 * dependencies which it does require.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getOptionalReadDependencies();

	/**
	 * Get the set of state components which this behaviour component needs write
	 * access to for the entities it operates on.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getWriteDependencies();

	/**
	 * Get the set of state components which this behaviour component does not
	 * directly require an entity to possess to operate on it, but which locks
	 * must be obtained for due to indirect dependencies through those write
	 * dependencies which it does require.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getOptionalWriteDependencies();
}