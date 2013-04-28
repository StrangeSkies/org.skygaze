package uk.co.elionline.gears.entities.management.impl.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entities.management.EntityBehaviourManager;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.management.EntityStateManager;
import uk.co.elionline.gears.entities.management.impl.AbstractEntityManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
@Component(service = EntityManager.class)
public class CollectionsEntityManager extends AbstractEntityManager {
	private final Set<UUID> entities;

	public CollectionsEntityManager() {
		this(new CollectionsEntityStateManager(),
				new CollectionsEntityBehaviourManager());
	}

	public CollectionsEntityManager(EntityStateManager stateManager,
			EntityBehaviourManager behaviourManager) {
		super(stateManager, behaviourManager);

		entities = new HashSet<>();
	}

	@Override
	protected void add(UUID identifier) {
		entities.add(identifier);
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
