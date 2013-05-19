package uk.co.elionline.gears.entity.behaviour;

import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public interface BehaviourProcessingContext {
	public EntityManager entities();

	public StripedReadWriteLockRelease<StateComponent<?>> getLockReleases();

	BehaviourComponent getBehaviour();

	public Set<? extends Entity> getEntities();
}
