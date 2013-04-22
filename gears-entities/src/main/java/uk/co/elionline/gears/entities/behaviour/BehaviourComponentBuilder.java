package uk.co.elionline.gears.entities.behaviour;

import java.util.Collection;

import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.utilities.Factory;

public interface BehaviourComponentBuilder extends
		Factory<BehaviourComponent> {
	public BehaviourComponentBuilder process(
			BehaviourComponentProcess process);

	public BehaviourComponentBuilder name(String name);

	public BehaviourComponentBuilder description(String description);

	public BehaviourComponentBuilder behaviourDependencies(
			Collection<? extends BehaviourComponent> behaviourDependencies);

	public BehaviourComponentBuilder behaviourDependencies(
			BehaviourComponent... behaviourDependencies);

	public BehaviourComponentBuilder behaviourDependents(
			Collection<? extends BehaviourComponent> behaviourDependents);

	public BehaviourComponentBuilder behaviourDependents(
			BehaviourComponent... behaviourDependents);

	public BehaviourComponentBuilder readDependencies(
			Collection<? extends StateComponent<?>> readDependencies);

	public BehaviourComponentBuilder readDependencies(
			StateComponent<?>... readDependencies);

	public BehaviourComponentBuilder writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies);

	public BehaviourComponentBuilder writeDependencies(
			StateComponent<?>... writeDependencies);

	public BehaviourComponentBuilder indirectReadDependencies(
			Collection<? extends StateComponent<?>> readDependencies);

	public BehaviourComponentBuilder indirectReadDependencies(
			StateComponent<?>... readDependencies);

	public BehaviourComponentBuilder indirectWriteDependencies(
			Collection<? extends StateComponent<?>> writeDependencies);

	public BehaviourComponentBuilder indirectWriteDependencies(
			StateComponent<?>... writeDependencies);
}