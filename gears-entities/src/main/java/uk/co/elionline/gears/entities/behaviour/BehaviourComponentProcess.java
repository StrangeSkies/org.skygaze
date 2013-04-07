package uk.co.elionline.gears.entities.behaviour;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.EntityManagerProcessingContext;

public interface BehaviourComponentProcess {
	public void process(Set<? extends UUID> entities, EntityManagerProcessingContext context);
}
