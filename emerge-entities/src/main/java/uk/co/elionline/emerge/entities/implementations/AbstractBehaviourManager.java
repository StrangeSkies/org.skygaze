package uk.co.elionline.emerge.entities.implementations;

import java.util.Collection;
import java.util.UUID;

import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;
import uk.co.elionline.emerge.entities.behaviour.BehaviourManager;

public abstract class AbstractBehaviourManager implements BehaviourManager {
	@Override
	public final boolean attachAll(UUID entity,
			Collection<BehaviourComponent> behaviourComponents) {
		boolean changed = false;

		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			changed = changed || attach(entity, behaviourComponent);
		}

		return changed;
	}

	@Override
	public final boolean has(UUID entity, BehaviourComponent behaviourComponent) {
		return isUniversal(behaviourComponent)
				|| hasExplicitly(entity, behaviourComponent);
	}
}
