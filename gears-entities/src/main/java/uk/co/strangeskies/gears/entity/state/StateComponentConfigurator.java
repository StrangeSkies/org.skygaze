package uk.co.strangeskies.gears.entity.state;

import java.util.Collection;

import uk.co.strangeskies.gears.utilities.Factory;

public interface StateComponentConfigurator<D> extends
		Factory<StateComponent<D>> {
	public StateComponentConfigurator<D> name(String name);

	public StateComponentConfigurator<D> description(String description);

	public StateComponentConfigurator<D> readDependencies(
			Collection<? extends StateComponent<?>> readDependencies);

	public StateComponentConfigurator<D> readDependencies(
			StateComponent<?>... readDependencies);

	public StateComponentConfigurator<D> writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies);

	public StateComponentConfigurator<D> writeDependencies(
			StateComponent<?>... writeDependencies);
}
