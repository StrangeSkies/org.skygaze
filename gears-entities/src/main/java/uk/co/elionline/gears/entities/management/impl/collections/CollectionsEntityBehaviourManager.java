package uk.co.elionline.gears.entities.management.impl.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.management.impl.AbstractEntityBehaviourManager;
import uk.co.elionline.gears.entities.scheduling.Scheduler;
import uk.co.elionline.gears.utilities.collections.HashSetMultiMap;
import uk.co.elionline.gears.utilities.collections.SetMultiMap;

public class CollectionsEntityBehaviourManager extends
		AbstractEntityBehaviourManager {
	private final SetMultiMap<BehaviourComponent, UUID> entityBehaviours;
	private final Set<BehaviourComponent> universalBehaviours;

	private final SetMultiMap<BehaviourComponent, BehaviourComponent> dependencies;
	private final SetMultiMap<BehaviourComponent, BehaviourComponent> dependents;

	private Scheduler defaultBehaviourProcessor;
	private final SetMultiMap<Scheduler, BehaviourComponent> processorBehaviourAssociations;

	public CollectionsEntityBehaviourManager() {
		entityBehaviours = new HashSetMultiMap<>();
		universalBehaviours = new HashSet<>();

		dependencies = new HashSetMultiMap<>();
		dependents = new HashSetMultiMap<>();

		processorBehaviourAssociations = new HashSetMultiMap<>();
	}

	@Override
	public boolean attach(UUID entity, BehaviourComponent behaviourComponent) {
		return entityBehaviours.add(behaviourComponent, entity);
	}

	@Override
	public boolean detach(UUID entity, BehaviourComponent behaviourComponent) {
		return entityBehaviours.remove(behaviourComponent, entity);
	}

	@Override
	public void detachAll(UUID entity) {
		entityBehaviours.removeFromAll(entity);
	}

	@Override
	public boolean hasExplicitly(UUID entity,
			BehaviourComponent behaviourComponent) {
		return entityBehaviours.contains(behaviourComponent, entity);
	}

	@Override
	public Set<UUID> getEntitiesExplicitlyWith(
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