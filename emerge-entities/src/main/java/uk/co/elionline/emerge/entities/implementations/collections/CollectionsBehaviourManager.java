package uk.co.elionline.emerge.entities.implementations.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;
import uk.co.elionline.emerge.entities.behaviour.BehaviourManager;
import uk.co.elionline.emerge.entities.implementations.AbstractBehaviourManager;
import uk.co.elionline.emerge.entities.processing.EntityProcessor;
import uk.co.elionline.emerge.utilities.collections.HashSetMultiMap;
import uk.co.elionline.emerge.utilities.collections.SetMultiMap;

@Component(service = BehaviourManager.class)
public class CollectionsBehaviourManager extends AbstractBehaviourManager {
	private final SetMultiMap<BehaviourComponent, UUID> entityBehaviours;
	private final Set<BehaviourComponent> universalBehaviours;

	private final SetMultiMap<BehaviourComponent, BehaviourComponent> dependencies;
	private final SetMultiMap<BehaviourComponent, BehaviourComponent> dependents;

	private EntityProcessor defaultBehaviourProcessor;
	private final SetMultiMap<EntityProcessor, BehaviourComponent> processorBehaviourAssociations;

	public CollectionsBehaviourManager() {
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
	public void setDefaultProcessor(EntityProcessor processor) {
		defaultBehaviourProcessor = processor;
	}

	@Override
	public void setProcessor(EntityProcessor processor,
			BehaviourComponent behaviourComponent) {
		setProcessorToDefault(behaviourComponent);
		processorBehaviourAssociations.add(processor, behaviourComponent);
	}

	@Override
	public void setProcessorToDefault(BehaviourComponent behaviourComponent) {
		processorBehaviourAssociations.removeFromAll(behaviourComponent);
	}

	@Override
	public Set<BehaviourComponent> getBehavioursForProcessor(
			EntityProcessor processor) {
		Set<BehaviourComponent> behaviours;

		if (processor == defaultBehaviourProcessor) {
			behaviours = getAll();
			behaviours.removeAll(processorBehaviourAssociations.getAllValues());
		} else {
			behaviours = processorBehaviourAssociations.get(processor);
		}

		return behaviours;
	}

	@Override
	public Set<EntityProcessor> getAllProcessors() {
		Set<EntityProcessor> processors = new HashSet<>(
				processorBehaviourAssociations.keySet());
		if (defaultBehaviourProcessor != null) {
			processors.add(defaultBehaviourProcessor);
		}
		return processors;
	}
}
