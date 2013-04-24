package uk.co.elionline.gears.entities.state.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentProperty;
import uk.co.elionline.gears.utilities.Factory;

public class StateComponentImpl<D> implements StateComponent<D> {
	private final String name;
	private final String description;

	private final Factory<? extends D> dataFactory;

	private final Set<StateComponent<?>> readDependencies;
	private final Set<StateComponent<?>> writeDependencies;

	private final Map<String, StateComponentProperty<D, ?>> properties;

	public StateComponentImpl(String name, String description,
			Factory<? extends D> dataFactory) {
		this.name = name;
		this.description = description;

		this.dataFactory = dataFactory;

		this.readDependencies = new HashSet<>();
		this.writeDependencies = new HashSet<>();

		this.properties = new HashMap<>();
	}

	public StateComponentImpl(String name, String description,
			Factory<? extends D> factory,
			Collection<? extends StateComponent<?>> readDependencies) {
		this(name, description, factory);

		this.readDependencies.addAll(readDependencies);

		// Add all indirect read dependencies
		for (StateComponent<?> readDependency : readDependencies) {
			this.readDependencies.addAll(readDependency.getReadDependencies());
		}
	}

	public StateComponentImpl(String name, String description,
			Factory<? extends D> factory, StateComponent<?>... readDependencies) {
		this(name, description, factory, Arrays.asList(readDependencies));
	}

	public StateComponentImpl(String name, String description,
			Factory<? extends D> factory,
			Collection<? extends StateComponent<?>> readDependencies,
			Collection<? extends StateComponent<?>> writeDependencies) {
		this(name, description, factory, readDependencies);

		this.writeDependencies.addAll(writeDependencies);

		// Add all indirect write dependencies
		for (StateComponent<?> writeDependency : writeDependencies) {
			this.writeDependencies.addAll(writeDependency.getWriteDependencies());
		}
	}

	public StateComponentImpl(String name, String description,
			Factory<? extends D> factory,
			Collection<? extends StateComponent<?>> readDependencies,
			Collection<? extends StateComponent<?>> writeDependencies,
			Collection<? extends StateComponentProperty<D, ?>> properties) {
		this(name, description, factory, readDependencies, writeDependencies);

		for (StateComponentProperty<D, ?> property : properties) {
			this.properties.put(property.getName(), property);
		}
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final String toString() {
		return getName();
	}

	@Override
	public final String getDescription() {
		return description;
	}

	/**
	 * The set of state components which also need to be locked for reading when
	 * we get a read lock on this component.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getReadDependencies() {
		return Collections.unmodifiableSet(readDependencies);
	}

	/**
	 * The set of state components which also need to be locked for writing when
	 * we get a write lock on this component.
	 * 
	 * @return
	 */
	@Override
	public final Set<StateComponent<?>> getWriteDependencies() {
		return Collections.unmodifiableSet(writeDependencies);
	}

	@Override
	public final Map<String, StateComponentProperty<D, ?>> getPropertiesMap() {
		return properties;
	}

	@Override
	public final D create() {
		return dataFactory.create();
	}

	@Override
	public final boolean equals(Object obj) {
		return super.equals(obj);
	}
}
