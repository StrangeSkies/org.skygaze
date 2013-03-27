package uk.co.elionline.gears.entities.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.co.elionline.gears.entities.state.data.StateComponentProperty;
import uk.co.elionline.gears.utilities.Described;
import uk.co.elionline.gears.utilities.Factory;
import uk.co.elionline.gears.utilities.Named;

public class StateComponent<D> implements Named, Described, Factory<D> {
	private final String name;
	private final String description;

	private final Factory<? extends D> dataFactory;

	private final Set<StateComponent<?>> readDependencies;
	private final Set<StateComponent<?>> writeDependencies;

	private final Map<String, StateComponentProperty<D, ?>> properties;

	public StateComponent(String name, String description,
			Factory<? extends D> dataFactory) {
		this.name = name;
		this.description = description;

		this.dataFactory = dataFactory;

		this.readDependencies = new HashSet<>();
		this.writeDependencies = new HashSet<>();

		this.properties = new HashMap<>();
	}

	public StateComponent(String name, String description,
			Factory<? extends D> factory,
			Collection<? extends StateComponent<?>> readDependencies) {
		this(name, description, factory);

		this.readDependencies.addAll(readDependencies);

		// Add all indirect read dependencies
		for (StateComponent<?> readDependency : readDependencies) {
			this.readDependencies.addAll(readDependency.getReadDependencies());
		}
	}

	public StateComponent(String name, String description,
			Factory<? extends D> factory, StateComponent<?>... readDependencies) {
		this(name, description, factory, Arrays.asList(readDependencies));
	}

	public StateComponent(String name, String description,
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

	public StateComponent(String name, String description,
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
	public final Set<StateComponent<?>> getReadDependencies() {
		return Collections.unmodifiableSet(readDependencies);
	}

	/**
	 * The set of state components which also need to be locked for writing when
	 * we get a write lock on this component.
	 * 
	 * @return
	 */
	public final Set<StateComponent<?>> getWriteDependencies() {
		return Collections.unmodifiableSet(writeDependencies);
	}

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

	public static <D> Builder<D> builder() {
		return new Builder<D>();
	}

	public static class Builder<D> implements Factory<StateComponent<D>> {
		private String name;
		private String description;

		private Factory<? extends D> dataFactory;

		private Collection<? extends StateComponent<?>> readDependencies;
		private Collection<? extends StateComponent<?>> writeDependencies;

		private Collection<? extends StateComponentProperty<D, ?>> properties;

		protected Builder() {
			name = "";
			description = "";

			readDependencies = new HashSet<>();
			writeDependencies = new HashSet<>();

			properties = new HashSet<>();
		}

		public Builder<D> dataFactory(Factory<? extends D> dataFactory) {
			this.dataFactory = dataFactory;

			return this;
		}

		public Builder<D> name(String name) {
			this.name = name;

			return this;
		}

		public Builder<D> description(String description) {
			this.description = description;

			return this;
		}

		public Builder<D> readDependencies(
				Collection<? extends StateComponent<?>> readDependencies) {
			this.readDependencies = readDependencies;

			return this;
		}

		public Builder<D> readDependencies(StateComponent<?>... readDependencies) {
			return readDependencies(Arrays.asList(readDependencies));
		}

		public Builder<D> writeDependencies(
				Collection<? extends StateComponent<?>> writeDependencies) {
			this.writeDependencies = writeDependencies;

			return this;
		}

		public Builder<D> writeDependencies(StateComponent<?>... writeDependencies) {
			return writeDependencies(Arrays.asList(writeDependencies));
		}

		public Builder<D> properties(
				Collection<? extends StateComponentProperty<D, ?>> properties) {
			this.properties = properties;

			return this;
		}

		public Builder<D> properties(
				@SuppressWarnings("unchecked") StateComponentProperty<D, ?>... properties) {
			return properties(Arrays.asList(properties));
		}

		@Override
		public StateComponent<D> create() {
			return new StateComponent<>(name, description, dataFactory,
					readDependencies, writeDependencies, properties);
		}
	}
}
