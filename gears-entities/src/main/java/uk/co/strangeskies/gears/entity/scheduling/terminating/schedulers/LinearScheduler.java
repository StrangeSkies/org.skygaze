package uk.co.strangeskies.gears.entity.scheduling.terminating.schedulers;

import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.gears.entity.scheduling.Scheduler;
import uk.co.strangeskies.gears.utilities.collections.HashSetMultiHashMap;

public class LinearScheduler implements Scheduler {
	public LinearScheduler() {
		super();
	}

	@Override
	public synchronized boolean process(
			ScheduleProcessingContext processingContext) {
		Set<BehaviourComponent> remainingBehaviours = new HashSet<>(
				processingContext.getBehaviours());
		HashSetMultiHashMap<BehaviourComponent, BehaviourComponent> remainingDependencies = new HashSetMultiHashMap<>();

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
