package uk.co.elionline.gears.entity.management.impl.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.management.impl.AbstractEntityBehaviourManager;
import uk.co.elionline.gears.entity.scheduling.Scheduler;
import uk.co.elionline.gears.utilities.collections.HashSetMultiHashMap;
import uk.co.elionline.gears.utilities.collections.SetMultiMap;

public class CollectionsEntityBehaviourManager extends
		AbstractEntityBehaviourManager {
	private final SetMultiMap<BehaviourComponent, Entity> entityBehaviours;
	private final Set<BehaviourComponent> universalBehaviours;

	private final SetMultiMap<BehaviourComponent, BehaviourComponent> dependencies;
	private final SetMultiMap<BehaviourComponent, BehaviourComponent> dependents;

	private Scheduler defaultBehaviourProcessor;
	private final SetMultiMap<Scheduler, BehaviourComponent> processorBehaviourAssociations;

	public CollectionsEntityBehaviourManager() {
		entityBehaviours = new HashSetMultiHashMap<>();
		universalBehaviours = new HashSet<>();

		dependencies = new HashSetMultiHashMap<>();
		dependents = new HashSetMultiHashMap<>();

		processorBehaviourAssociations = new HashSetMultiHashMap<>();
	}

	@Override
	public boolean attach(Entity entity, BehaviourComponent behaviourComponent) {
		return entityBehaviours.add(behaviourComponent, entity);
	}

	@Override
	public boolean detach(Entity entity, BehaviourComponent behaviourComponent) {
		return entityBehaviours.remove(behaviourComponent, entity);
	}

	@Override
	public void clear(Entity entity) {
		entityBehaviours.removeFromAll(entity);
	}

	@Override
	public boolean hasExplicitly(Entity entity,
			BehaviourComponent behaviourComponent) {
		return entityBehaviours.contains(behaviourComponent, entity);
	}

	@Override
	public Set<Entity> getEntitiesExplicitlyWith(
			BehaviourComponent behaviourComponent) {
		return entityBehaviours.get(behaviourComponent);
	}

	@Override
	public boolean addUniversal(BehaviourComponent behaviourComponent) {
		return universalBehaviours.add(behaviourComponent);
	}

	@Override
	public boolean removeUniversal(BehaviourComponent behaviourComponent) {
		return universalBehaviours.remove(behaviourComponent);
	}

	@Override
	public Set<BehaviourComponent> getUniversal() {
		return Collections.unmodifiableSet(universalBehaviours);
	}

	@Override
	public boolean isUniversal(BehaviourComponent behaviourComponent) {
		return universalBehaviours.contains(behaviourComponent);
	}

	@Override
	public Set<BehaviourComponent> getAll() {
		HashSet<BehaviourComponent> behaviours = new HashSet<>();

		behaviours.addAll(universalBehaviours);
		behaviours.addAll(entityBehaviours.keySet());

		return behaviours;
	}

	@Override
	public Set<BehaviourComponent> getDependencies(BehaviourComponent behaviour) {
		return getDependencies().get(behaviour);
	}

	@Override
	public Set<BehaviourComponent> getDependents(BehaviourComponent behaviour) {
		return getDependents().get(behaviour);
	}

	@Override
	public SetMultiMap<BehaviourComponent, BehaviourComponent> getDependencies() {
		return dependencies;
	}

	@Override
	public SetMultiMap<BehaviourComponent, BehaviourComponent> getDependents() {
		return dependents;
	}

	@Override
	public void setDefaultScheduler(Scheduler scheduler) {
		defaultBehaviourProcessor = scheduler;
	}

	@Override
	public void setBehaviourScheduler(Scheduler scheduler,
			BehaviourComponent behaviourComponent) {
		setBehaviourSchedulerToDefault(behaviourComponent);
		processorBehaviourAssociations.add(scheduler, behaviourComponent);
	}

	@Override
	public void setBehaviourSchedulerToDefault(
			BehaviourComponent behaviourComponent) {
		processorBehaviourAssociations.removeFromAll(behaviourComponent);
	}

	@Override
	public Set<BehaviourComponent> getBehavioursForScheduler(Scheduler scheduler) {
		Set<BehaviourComponent> behaviours;

		if (scheduler == defaultBehaviourProcessor) {
			behaviours = getAll();
			behaviours.removeAll(processorBehaviourAssociations.getAllValues());
		} else {
			behaviours = processorBehaviourAssociations.get(scheduler);
		}

		return behaviours;
	}

	@Override
	public Set<Scheduler> getAllSchedulers() {
		Set<Scheduler> processors = new HashSet<>(
				processorBehaviourAssociations.keySet());
		if (defaultBehaviourProcessor != null) {
			processors.add(defaultBehaviourProcessor);
		}
		return processors;
	}
}
