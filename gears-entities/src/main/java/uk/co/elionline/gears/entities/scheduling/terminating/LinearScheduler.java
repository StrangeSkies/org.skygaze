package uk.co.elionline.gears.entities.scheduling.terminating;

import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.scheduling.ScheduleProcessingContext;
import uk.co.elionline.gears.entities.scheduling.Scheduler;
import uk.co.elionline.gears.utilities.collections.HashSetMultiMap;

public class LinearScheduler implements Scheduler {
	public LinearScheduler() {
		super();
	}

	@Override
	public synchronized boolean process(
			ScheduleProcessingContext processingContext) {
		Set<BehaviourComponent> remainingBehaviours = new HashSet<>(
				processingContext.getBehaviours());
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
						processingContext.processBehaviour(behaviour);
						done = true;
					} catch (InterruptedException e) {
					}
				} while (!done);
			}
			remainingDependencies.removeAllFromAll(readyBehaviours);
			remainingBehaviours.removeAll(readyBehaviours);
		}

		return true;
	}

	@Override
	public boolean stopProcessing() {
		return false;
	}
}
