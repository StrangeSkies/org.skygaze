package uk.co.strangeskies.gears.entity.behaviour;

import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.management.EntityManager;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.utilities.flowcontrol.StripedReadWriteLockRelease;

public interface BehaviourProcessingContext {
	public EntityManager entity();

	public StripedReadWriteLockRelease<StateComponent<?>> getLockReleases();

	BehaviourComponent getBehaviour();

	public Set<? extends Entity> getEntities();
}
