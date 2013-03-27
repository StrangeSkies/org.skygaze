package uk.co.elionline.emerge.entities.processing.scheduling;

import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;
import uk.co.elionline.emerge.utilities.collections.HashSetMultiMap;

public class LinearScheduler extends BehaviourScheduler {
	public LinearScheduler() {
		super();
	}

	@Override
	public void start() {
		Set<BehaviourComponent> remainingBehaviours = new HashSet<>(getBehaviours());
		HashSetMultiMap<BehaviourComponent, BehaviourComponent> remainingDependencies = new HashSetMultiMap<>();

		for (BehaviourComponent behaviour : remainingBehaviours) {
			Set<BehaviourComponent> dependencies = new HashSet<>(
					behaviour.getBehaviourDependencies());
			dependencies.retainAll(remainingBehaviours);
			remainingDependencies.addAll(behaviour, dependencies);
		}

		while (!remainingBehaviours.isEmpty()) {
			Set<BehaviourComponent> readyBehaviours = new HashSet<>(
					remainingBehaviours);
			readyBehaviours.removeAll(remainingDependencies.keySet());
			for (BehaviourComponent behaviour : readyBehaviours) {
				boolean done = false;
				do {
					try {
						getEntityManager().processBehaviour(behaviour);
						done = true;
					} catch (InterruptedException e) {
					}
				} while (!done);
			}
			remainingDependencies.removeAllFromAll(readyBehaviours);
			remainingBehaviours.removeAll(readyBehaviours);
		}
	}
}
