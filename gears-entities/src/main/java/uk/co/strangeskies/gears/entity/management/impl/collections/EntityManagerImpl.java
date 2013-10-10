package uk.co.strangeskies.gears.entity.management.impl.collections;

import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.management.impl.AbstractEntityManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public class EntityManagerImpl extends AbstractEntityManager {
	private final Set<Entity> entities;

	public EntityManagerImpl() {
		super(new EntityStateManagerImpl(), new EntityBehaviourManagerImpl());

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
