package uk.co.strangeskies.extengine.entity.state;

import java.util.Set;

public interface UnsafeStateComponent<D> extends StateComponent<D, D> {
	/**
	 * The set of state components which also need to be locked for reading when
	 * we get a read lock on this component.
	 *
	 * @return
	 */
	public Set<StateComponent<?, ?>> getReadDependencies();

	/**
	 * The set of state components which also need to be locked for writing when
	 * we get a write lock on this component.
	 *
	 * @return
	 */
	public Set<StateComponent<?, ?>> getWriteDependencies();
}
