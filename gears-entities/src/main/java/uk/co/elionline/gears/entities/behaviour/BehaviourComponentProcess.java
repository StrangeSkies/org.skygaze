package uk.co.elionline.gears.entities.behaviour;

import java.util.Set;
import java.util.UUID;


public interface BehaviourComponentProcess {
	public void process(Set<? extends UUID> entities, BehaviourProcessingContextEntityManager context);
}
