package uk.co.elionline.gears.entities.state;

import java.util.Collection;

import uk.co.elionline.gears.utilities.Factory;

public interface StateComponentBuilder<D> extends Factory<StateComponent<D>> {
	public StateComponentBuilder<D> dataFactory(Factory<? extends D> dataFactory);

	public StateComponentBuilder<D> name(String name);

	public StateComponentBuilder<D> description(String description);

	public StateComponentBuilder<D> readDependencies(
			Collection<? extends StateComponent<?>> readDependencies);

	public StateComponentBuilder<D> readDependencies(
			StateComponent<?>... readDependencies);

	public StateComponentBuilder<D> writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies);

	public StateComponentBuilder<D> writeDependencies(
			StateComponent<?>... writeDependencies);

	public StateComponentBuilder<D> properties(
			Collection<? extends StateComponentProperty<D, ?>> properties);

	public StateComponentBuilder<D> properties(
			@SuppressWarnings("unchecked") StateComponentProperty<D, ?>... properties);
}
