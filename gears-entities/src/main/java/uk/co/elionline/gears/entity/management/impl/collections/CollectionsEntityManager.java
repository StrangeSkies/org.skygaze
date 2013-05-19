package uk.co.elionline.gears.entity.management.impl.collections;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.management.EntityBehaviourManager;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.management.EntityStateManager;
import uk.co.elionline.gears.entity.management.impl.AbstractEntityManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
@Component(service = EntityManager.class)
public class CollectionsEntityManager extends AbstractEntityManager {
	private final Set<Entity> entities;

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
	protected void add(Entity identifier) {
		entities.add(identifier);
	}

	@Override
	public boolean exists(Entity entity) {
		return entities.contains(entity);
	}

	@Override
	public Set<Entity> getAll() {
		return new HashSet<>(entities);
	}

	@Override
	protected boolean detach(Entity entity) {
		return entities.remove(entity);
	}
}
