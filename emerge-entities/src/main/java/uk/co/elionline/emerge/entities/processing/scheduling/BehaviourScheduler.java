package uk.co.elionline.emerge.entities.processing.scheduling;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.emerge.entities.EntityManager;
import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;

public abstract class BehaviourScheduler {
	private final Set<BehaviourComponent> behaviours;

	private EntityManager entityManager;

	public BehaviourScheduler() {
		this.behaviours = new HashSet<>();
	}

	public Set<BehaviourComponent> getBehaviours() {
		return Collections.unmodifiableSet(behaviours);
	}

	public final void setBehaviours(Set<BehaviourComponent> behaviours) {
		this.behaviours.clear();
		this.behaviours.addAll(behaviours);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public abstract void start();
}
