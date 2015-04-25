package uk.co.strangeskies.extengine.entity.management;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;

public interface EntityBehaviourManager {
	public boolean attach(Entity entity, BehaviourComponent behaviourComponent);

	public default boolean attachAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		boolean changed = false;

		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			changed = attach(entity, behaviourComponent) || changed;
		}

		return changed;
	}

	public default boolean attachAll(Entity entity,
			BehaviourComponent... behaviourComponents) {
		return attachAll(entity, Arrays.asList(behaviourComponents));
	}

	public boolean detach(Entity entity, BehaviourComponent behaviourComponent);

	public default boolean detachAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		boolean changed = false;

		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			changed = detach(entity, behaviourComponent) || changed;
		}

		return changed;
	}

	public default boolean detachAll(Entity entity,
			BehaviourComponent... behaviourComponents) {
		return detachAll(entity, Arrays.asList(behaviourComponents));
	}

	public void clear(Entity entity);

	public default boolean has(Entity entity,
			BehaviourComponent behaviourComponent) {
		return isUniversal(behaviourComponent)
				|| hasExplicitly(entity, behaviourComponent);
	}

	public default boolean hasAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			if (!has(entity, behaviourComponent)) {
				return false;
			}
		}

		return true;
	}

	public default boolean hasAll(Entity entity,
			BehaviourComponent... behaviourComponents) {
		return hasAll(entity, Arrays.asList(behaviourComponents));
	}

	public boolean hasExplicitly(Entity entity,
			BehaviourComponent behaviourComponent);

	public default boolean hasAllExplicitly(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents) {
		for (BehaviourComponent behaviourComponent : behaviourComponents) {
			if (!hasExplicitly(entity, behaviourComponent)) {
				return false;
			}
		}

		return true;
	}

	public default boolean hasAllExplicitly(Entity entity,
			BehaviourComponent... behaviourComponents) {
		return hasAllExplicitly(entity, Arrays.asList(behaviourComponents));
	}

	/**
	 * Get the set of all entities which currently have the given behaviour
	 * attached (either explicitly or universally).
	 * 
	 * @param behaviourComponent
	 *          The behaviour component for which we want to find all applicable
	 *          entities.
	 * @return The set of entities which are attached to this behaviour.
	 */
	public Set<Entity> getEntitiesExplicitlyWith(
			BehaviourComponent behaviourComponent);

	public boolean addUniversal(BehaviourComponent behaviourComponent);

	public boolean removeUniversal(BehaviourComponent behaviourComponent);

	public Set<BehaviourComponent> getUniversal();

	public boolean isUniversal(BehaviourComponent behaviourComponent);

	public Set<BehaviourComponent> getAll();

	public void setDefaultScheduler(Scheduler scheduler);

	public void setBehaviourScheduler(Scheduler scheduler,
			BehaviourComponent behaviourComponent);

	public void setBehaviourSchedulerToDefault(
			BehaviourComponent behaviourComponent);

	public Set<BehaviourComponent> getBehavioursForScheduler(Scheduler scheduler);

	public Set<Scheduler> getAllSchedulers();
}
