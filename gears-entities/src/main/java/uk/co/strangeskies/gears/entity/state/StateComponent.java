package uk.co.strangeskies.gears.entity.state;

import java.util.Set;

import uk.co.strangeskies.gears.utilities.Described;
import uk.co.strangeskies.gears.utilities.Factory;
import uk.co.strangeskies.gears.utilities.Named;

public interface StateComponent<D> extends Named, Described, Factory<D> {
	/**
	 * The set of state components which also need to be locked for reading when
	 * we get a read lock on this component.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getReadDependencies();

	/**
	 * The set of state components which also need to be locked for writing when
	 * we get a write lock on this component.
	 * 
	 * @return
	 */
	public Set<StateComponent<?>> getWriteDependencies();
}
