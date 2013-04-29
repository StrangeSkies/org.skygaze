package uk.co.elionline.gears.entities.behaviour;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public interface BehaviourProcessingContext {
	public EntityManager getEntityManager();

	public StripedReadWriteLockRelease<StateComponent<?>> getLockReleases();

	BehaviourComponent getBehaviour();

	public Set<? extends UUID> getEntities();
}
