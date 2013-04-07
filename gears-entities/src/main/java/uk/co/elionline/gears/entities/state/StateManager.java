package uk.co.elionline.gears.entities.state;

import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLock;

public interface StateManager extends StateManagerProcessingContext {
	public void setLockingEnabled(boolean enabled);

	public StripedReadWriteLock<StateComponent<?>> getLocks();
}
