package uk.co.strangeskies.extengine.entity.state;

import java.util.Collections;
import java.util.Set;

public interface WriteOnlyStateComponent<D> extends StateComponent<D, Object> {
	/**
	 * The set of state components which also need to be locked for reading when
	 * we get a read lock on this component.
	 *
	 * @return
	 */
	public default Set<StateComponent<?, ?>> getReadDependencies() {
		return Collections.emptySet();
	}

	/**
	 * The set of state components which also need to be locked for writing when
	 * we get a write lock on this component.
	 *
	 * @return
	 */
	public Set<StateComponent<?, ?>> getWriteDependencies();
}
