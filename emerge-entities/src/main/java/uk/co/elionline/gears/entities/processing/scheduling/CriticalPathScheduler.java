package uk.co.elionline.gears.entities.processing.scheduling;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;

public class CriticalPathScheduler extends BehaviourScheduler {
	public CriticalPathScheduler() {
		super();
	}

	@Override
	public void start() {
		for (BehaviourComponent behaviour : getBehaviours()) {
			boolean done = false;
			do {
				try {
					getEntityManager().processBehaviour(behaviour);
				} catch (InterruptedException e) {
				}
			} while (!done);
		}
	}
}
