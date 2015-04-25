package uk.co.strangeskies.extengine.entity.management.impl.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.management.EntityBehaviourManager;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;
import uk.co.strangeskies.utilities.collection.multimap.MultiHashMap;
import uk.co.strangeskies.utilities.collection.multimap.MultiMap;

public class EntityBehaviourManagerImpl implements EntityBehaviourManager {
	private final MultiMap<BehaviourComponent, Entity, ? extends Set<Entity>> entityBehaviours;
	private final Set<BehaviourComponent> universalBehaviours;

	private Scheduler defaultBehaviourProcessor;
	private final MultiMap<Scheduler, BehaviourComponent, ? extends Set<BehaviourComponent>> processorBehaviourAssociations;

	public EntityBehaviourManagerImpl() {
		entityBehaviours = new MultiHashMap<>(HashSet::new);
		universalBehaviours = new HashSet<>();

		processorBehaviourAssociations = new MultiHashMap<>(HashSet::new);
	}

	@Override
	public boolean attach(Entity entity, BehaviourComponent behaviourComponent) {
		return entityBehaviours.add(behaviourComponent, entity);
	}

	@Override
	public boolean detach(Entity entity, BehaviourComponent behaviourComponent) {
		return entityBehaviours.removeValue(behaviourComponent, entity);
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
			behaviours.removeAll(processorBehaviourAssociations.getAll());
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
