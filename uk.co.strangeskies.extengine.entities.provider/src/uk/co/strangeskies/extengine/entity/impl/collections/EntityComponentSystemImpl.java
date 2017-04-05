package uk.co.strangeskies.extengine.entity.impl.collections;

import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.impl.AbstractEntityComponentSystem;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public class EntityComponentSystemImpl extends AbstractEntityComponentSystem {
	private final Set<Entity> entities;

	public EntityComponentSystemImpl() {
		super(new StateComponentManagerImpl(), new BehaviourComponentManagerImpl());

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
