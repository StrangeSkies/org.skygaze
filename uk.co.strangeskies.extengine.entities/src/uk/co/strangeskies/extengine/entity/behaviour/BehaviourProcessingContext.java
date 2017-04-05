package uk.co.strangeskies.extengine.entity.behaviour;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponentManager;
import uk.co.strangeskies.utilities.flowcontrol.StripedReadWriteLockRelease;

public interface BehaviourProcessingContext {
	public EntityComponentSystem entity();

	public default BehaviourComponentManager behaviour() {
		return entity().behaviour();
	}

	public default StateComponentManager state() {
		return entity().state();
	}

	public StripedReadWriteLockRelease<StateComponent<?, ?>> lockReleases();

	BehaviourComponent processingBehaviour();

	public Set<? extends Entity> participatingEntities();
}
