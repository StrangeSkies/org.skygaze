package uk.co.strangeskies.extengine.entity.behaviour;

import java.util.Collection;

import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.factory.Factory;

public interface BehaviourComponentConfigurator extends
		Factory<BehaviourComponent> {
	public BehaviourComponentConfigurator name(String name);

	public BehaviourComponentConfigurator description(String description);

	public BehaviourComponentConfigurator behaviourDependencies(
			Collection<? extends BehaviourComponent> behaviourDependencies);

	public BehaviourComponentConfigurator behaviourDependencies(
			BehaviourComponent... behaviourDependencies);

	public BehaviourComponentConfigurator behaviourDependents(
			Collection<? extends BehaviourComponent> behaviourDependents);

	public BehaviourComponentConfigurator behaviourDependents(
			BehaviourComponent... behaviourDependents);

	public BehaviourComponentConfigurator readDependencies(
			Collection<? extends StateComponent<?>> readDependencies);

	public BehaviourComponentConfigurator readDependencies(
			StateComponent<?>... readDependencies);

	public BehaviourComponentConfigurator writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies);

	public BehaviourComponentConfigurator writeDependencies(
			StateComponent<?>... writeDependencies);

	public BehaviourComponentConfigurator optionalReadDependencies(
			Collection<? extends StateComponent<?>> readDependencies);

	public BehaviourComponentConfigurator optionalReadDependencies(
			StateComponent<?>... readDependencies);

	public BehaviourComponentConfigurator optionalWriteDependencies(
			Collection<? extends StateComponent<?>> writeDependencies);

	public BehaviourComponentConfigurator optionalWriteDependencies(
			StateComponent<?>... writeDependencies);

	public BehaviourComponentConfigurator process(BehaviourProcess process);
}