package uk.co.elionline.gears.entity.behaviour;

import java.util.Collection;

import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.Factory;

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

	public BehaviourComponentConfigurator indirectReadDependencies(
			Collection<? extends StateComponent<?>> readDependencies);

	public BehaviourComponentConfigurator indirectReadDependencies(
			StateComponent<?>... readDependencies);

	public BehaviourComponentConfigurator indirectWriteDependencies(
			Collection<? extends StateComponent<?>> writeDependencies);

	public BehaviourComponentConfigurator indirectWriteDependencies(
			StateComponent<?>... writeDependencies);
}