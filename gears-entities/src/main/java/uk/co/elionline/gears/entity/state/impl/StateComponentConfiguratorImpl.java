package uk.co.elionline.gears.entity.state.impl;

import java.util.Arrays;
import java.util.Collection;

import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentConfigurator;
import uk.co.elionline.gears.utilities.Factory;

public class StateComponentConfiguratorImpl<D> implements
		StateComponentConfigurator<D> {
	private String name;
	private String description;

	private final Factory<? extends D> dataFactory;

	private Collection<? extends StateComponent<?>> readDependencies;
	private Collection<? extends StateComponent<?>> writeDependencies;

	public StateComponentConfiguratorImpl(Factory<? extends D> dataFactory) {
		this.dataFactory = dataFactory;
	}

	@Override
	public StateComponentConfigurator<D> name(String name) {
		this.name = name;

		return this;
	}

	@Override
	public StateComponentConfigurator<D> description(String description) {
		this.description = description;

		return this;
	}

	@Override
	public StateComponentConfigurator<D> readDependencies(
			Collection<? extends StateComponent<?>> readDependencies) {
		this.readDependencies = readDependencies;

		return this;
	}

	@Override
	public StateComponentConfigurator<D> readDependencies(
			StateComponent<?>... readDependencies) {
		return readDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public StateComponentConfigurator<D> writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies) {
		this.writeDependencies = writeDependencies;

		return this;
	}

	@Override
	public StateComponentConfigurator<D> writeDependencies(
			StateComponent<?>... writeDependencies) {
		return writeDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public StateComponent<D> create() {
		return new StateComponentImpl<>(name, description, dataFactory,
				readDependencies, writeDependencies);
	}
}