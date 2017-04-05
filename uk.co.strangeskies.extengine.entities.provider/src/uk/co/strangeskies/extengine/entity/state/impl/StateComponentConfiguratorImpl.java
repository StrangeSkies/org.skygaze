package uk.co.strangeskies.extengine.entity.state.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponentConfigurator;
import uk.co.strangeskies.utilities.factory.Factory;

public class StateComponentConfiguratorImpl<D extends C, C> implements
		StateComponentConfigurator<D, C> {
	private String name;
	private String description;

	private Factory<? extends D> dataFactory;

	private Collection<? extends StateComponent<?, ?>> readDependencies;
	private Collection<? extends StateComponent<?, ?>> writeDependencies;

	public StateComponentConfiguratorImpl() {
		name = "";
		description = "";

		dataFactory = new Factory<D>() {
			@Override
			public D create() {
				return null;
			}
		};

		readDependencies = Collections.emptySet();
		writeDependencies = Collections.emptySet();
	}

	@Override
	public StateComponentConfigurator<D, C> name(String name) {
		this.name = name;

		return this;
	}

	@Override
	public StateComponentConfigurator<D, C> description(String description) {
		this.description = description;

		return this;
	}

	@Override
	public StateComponentConfigurator<D, C> readDependencies(
			Collection<? extends StateComponent<?, ?>> readDependencies) {
		this.readDependencies = readDependencies;

		return this;
	}

	@Override
	public StateComponentConfigurator<D, C> readDependencies(
			StateComponent<?, ?>... readDependencies) {
		return readDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public StateComponentConfigurator<D, C> writeDependencies(
			Collection<? extends StateComponent<?, ?>> writeDependencies) {
		this.writeDependencies = writeDependencies;

		return this;
	}

	@Override
	public StateComponentConfigurator<D, C> writeDependencies(
			StateComponent<?, ?>... writeDependencies) {
		return writeDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public StateComponent<D, C> create() {
		return new StateComponentImpl<>(name, description, dataFactory,
				readDependencies, writeDependencies);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends U, U> StateComponentConfigurator<T, U> data(
			Factory<? extends T> dataFactory) {
		this.dataFactory = (Factory<? extends D>) dataFactory;

		return (StateComponentConfigurator<T, U>) this;
	}
}
