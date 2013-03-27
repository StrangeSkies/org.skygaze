package uk.co.elionline.gears.entities.implementations.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entities.Entity;
import uk.co.elionline.gears.entities.EntityManager;
import uk.co.elionline.gears.entities.behaviour.BehaviourManager;
import uk.co.elionline.gears.entities.implementations.AbstractEntityManager;
import uk.co.elionline.gears.entities.state.StateManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
@Component(service = EntityManager.class)
public class CollectionsEntityManager extends AbstractEntityManager {
	private final Set<UUID> entities;

	public CollectionsEntityManager() {
		this(new CollectionsStateManager(), new CollectionsBehaviourManager());
	}

	public CollectionsEntityManager(StateManager stateManager,
			BehaviourManager behaviourManager) {
		super(stateManager, behaviourManager);

		entities = new HashSet<>();
	}

	@Override
	protected void add(UUID identifier) {
		entities.add(identifier);
	}

	@Override
	public Entity get(UUID entityIdentifier) {
		if (!exists(entityIdentifier)) {
			throw new IllegalArgumentException();
		}
		return new Entity(this, entityIdentifier);
	}

	@Override
	public boolean exists(UUID entityIdentifier) {
		return entities.contains(entityIdentifier);
	}

	@Override
	public Set<UUID> getAll() {
		return new HashSet<>(entities);
	}

	@Override
	protected boolean detach(UUID entity) {
		return entities.remove(entity);
	}
}
