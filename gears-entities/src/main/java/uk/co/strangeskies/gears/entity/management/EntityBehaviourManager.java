package uk.co.strangeskies.gears.entity.management;

import java.util.Collection;
import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.scheduling.Scheduler;
import uk.co.strangeskies.gears.utilities.collections.SetMultiMap;

public interface EntityBehaviourManager {
	public boolean attach(Entity entity, BehaviourComponent behaviourComponent);

	public boolean attachAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents);

	public boolean detach(Entity entity, BehaviourComponent behaviourComponent);

	public boolean detachAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents);

	public void clear(Entity entity);

	public boolean has(Entity entity, BehaviourComponent behaviourComponent);

	public boolean hasAll(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents);

	public boolean hasExplicitly(Entity entity,
			BehaviourComponent behaviourComponent);

	public boolean hasAllExplicitly(Entity entity,
			Collection<? extends BehaviourComponent> behaviourComponents);

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

	public Set<BehaviourComponent> getDependencies(BehaviourComponent behaviour);

	public Set<BehaviourComponent> getDependents(BehaviourComponent behaviour);

	public SetMultiMap<BehaviourComponent, BehaviourComponent> getDependencies();

	public SetMultiMap<BehaviourComponent, BehaviourComponent> getDependents();

	public void setDefaultScheduler(Scheduler scheduler);

	public void setBehaviourScheduler(Scheduler scheduler,
			BehaviourComponent behaviourComponent);

	public void setBehaviourSchedulerToDefault(
			BehaviourComponent behaviourComponent);

	public Set<BehaviourComponent> getBehavioursForScheduler(Scheduler scheduler);

	public Set<Scheduler> getAllSchedulers();
}
