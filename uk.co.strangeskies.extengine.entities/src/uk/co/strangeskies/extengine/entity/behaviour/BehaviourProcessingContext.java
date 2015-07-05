package uk.co.strangeskies.extengine.entity.behaviour;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.management.EntityBehaviourManager;
import uk.co.strangeskies.extengine.entity.management.EntityManager;
import uk.co.strangeskies.extengine.entity.management.EntityStateManager;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.flowcontrol.StripedReadWriteLockRelease;

public interface BehaviourProcessingContext {
	public EntityManager entity();

	public default EntityBehaviourManager behaviour() {
		return entity().behaviour();
	}

	public default EntityStateManager state() {
		return entity().state();
	}

	public StripedReadWriteLockRelease<StateComponent<?, ?>> lockReleases();

	BehaviourComponent processingBehaviour();

	public Set<? extends Entity> participatingEntities();
}
