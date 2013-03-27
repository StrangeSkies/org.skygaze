package uk.co.elionline.emerge.entities.behaviour;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.emerge.entities.LockedEntityManager;

public interface BehaviourComponentProcess {
	public void process(Set<? extends UUID> entities, LockedEntityManager context);
}
