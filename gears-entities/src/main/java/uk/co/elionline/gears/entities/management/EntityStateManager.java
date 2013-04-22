package uk.co.elionline.gears.entities.management;

import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLock;

public interface EntityStateManager extends EntityStateManagerProcessingContext {
	public void setLockingEnabled(boolean enabled);

	public StripedReadWriteLock<StateComponent<?>> getLocks();
}
