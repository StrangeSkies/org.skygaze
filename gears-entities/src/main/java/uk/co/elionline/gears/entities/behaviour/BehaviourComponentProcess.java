package uk.co.elionline.gears.entities.behaviour;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.LockedEntityManager;

public interface BehaviourComponentProcess {
	public void process(Set<? extends UUID> entities, LockedEntityManager context);
}
