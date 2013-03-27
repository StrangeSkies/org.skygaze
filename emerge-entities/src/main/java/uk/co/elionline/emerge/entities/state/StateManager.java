package uk.co.elionline.emerge.entities.state;

import uk.co.elionline.emerge.utilities.flowcontrol.StripedReadWriteLock;

public interface StateManager extends LockedStateManager {
	public void setLockingEnabled(boolean enabled);

	public StripedReadWriteLock<StateComponent<?>> getLocks();
}
