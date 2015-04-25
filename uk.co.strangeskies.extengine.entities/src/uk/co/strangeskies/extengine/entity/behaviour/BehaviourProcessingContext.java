package uk.co.strangeskies.extengine.entity.behaviour;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.management.EntityManager;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.flowcontrol.StripedReadWriteLockRelease;

public interface BehaviourProcessingContext {
	public EntityManager entity();

	public StripedReadWriteLockRelease<StateComponent<?>> getLockReleases();

	BehaviourComponent getBehaviour();

	public Set<? extends Entity> getEntities();
}
