package uk.co.elionline.gears.entity.state.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.utilities.Factory;

public class StateComponentBuilderImpl<D> implements StateComponentBuilder<D> {
	private String name;
	private String description;

	private Factory<? extends D> dataFactory;

	private Collection<? extends StateComponent<?>> readDependencies;
	private Collection<? extends StateComponent<?>> writeDependencies;

	public StateComponentBuilderImpl() {
		name = "";
		description = "";

		readDependencies = new HashSet<>();
		writeDependencies = new HashSet<>();
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
	public StateComponent<D> create() {
		return new StateComponentImpl<>(name, description, dataFactory,
				readDependencies, writeDependencies);
	}
}