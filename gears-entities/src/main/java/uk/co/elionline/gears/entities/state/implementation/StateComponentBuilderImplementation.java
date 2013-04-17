package uk.co.elionline.gears.entities.state.implementation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentBuilder;
import uk.co.elionline.gears.entities.state.StateComponentProperty;
import uk.co.elionline.gears.utilities.Factory;

public class StateComponentBuilderImplementation<D> implements
		StateComponentBuilder<D> {
	private String name;
	private String description;

	private Factory<? extends D> dataFactory;

	private Collection<? extends StateComponent<?>> readDependencies;
	private Collection<? extends StateComponent<?>> writeDependencies;

	private Collection<? extends StateComponentProperty<D, ?>> properties;

	public StateComponentBuilderImplementation() {
		name = "";
		description = "";

		readDependencies = new HashSet<>();
		writeDependencies = new HashSet<>();

		properties = new HashSet<>();
	}

	@Override
	public StateComponentBuilder<D> dataFactory(Factory<? extends D> dataFactory) {
		this.dataFactory = dataFactory;

		return this;
	}

	@Override
	public StateComponentBuilder<D> name(String name) {
		this.name = name;

		return this;
	}

	@Override
	public StateComponentBuilder<D> description(String description) {
		this.description = description;

		return this;
	}

	@Override
	public StateComponentBuilder<D> readDependencies(
			Collection<? extends StateComponent<?>> readDependencies) {
		this.readDependencies = readDependencies;

		return this;
	}

	@Override
	public StateComponentBuilder<D> readDependencies(
			StateComponent<?>... readDependencies) {
		return readDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public StateComponentBuilder<D> writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies) {
		this.writeDependencies = writeDependencies;

		return this;
	}

	@Override
	public StateComponentBuilder<D> writeDependencies(
			StateComponent<?>... writeDependencies) {
		return writeDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public StateComponentBuilder<D> properties(
			Collection<? extends StateComponentProperty<D, ?>> properties) {
		this.properties = properties;

		return this;
	}

	@Override
	public StateComponentBuilder<D> properties(
			@SuppressWarnings("unchecked") StateComponentProperty<D, ?>... properties) {
		return properties(Arrays.asList(properties));
	}

	@Override
	public StateComponent<D> create() {
		return new StateComponent<>(name, description, dataFactory,
				readDependencies, writeDependencies, properties);
	}
}