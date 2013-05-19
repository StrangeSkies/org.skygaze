package uk.co.elionline.gears.entity.management.impl;

import java.util.Collection;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.management.EntityBehaviourManager;

public abstract class AbstractEntityBehaviourManager implements
		EntityBehaviourManager {
	@Override
	public final boolean attachAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		boolean changed = false;

		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			changed = attach(entity, behaviourComponent) || changed;
		}

		return changed;
	}

	@Override
	public final boolean detachAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		boolean changed = false;

		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			changed = detach(entity, behaviourComponent) || changed;
		}

		return changed;
	}

	@Override
	public final boolean has(Entity entity, BehaviourComponent behaviourComponent) {
		return isUniversal(behaviourComponent)
				|| hasExplicitly(entity, behaviourComponent);
	}

	@Override
	public boolean hasAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			if (!has(entity, behaviourComponent)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean hasAllExplicitly(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			if (!hasExplicitly(entity, behaviourComponent)) {
				return false;
			}
		}

		return true;
	}
}
