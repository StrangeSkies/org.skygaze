package uk.co.strangeskies.gears.entity.state;

import java.util.function.Function;

import uk.co.strangeskies.gears.utilities.Property;

public class StateComponentProperty<D, T> {
	private final String name;

	private final Class<T> propertyClass;
	private final Function<D, Property<T, ? super T>> propertyFunction;

	public StateComponentProperty(String name, Class<T> propertyClass,
			Function<D, Property<T, ? super T>> propertyFunction) {
		this.name = name;
		this.propertyClass = propertyClass;
		this.propertyFunction = propertyFunction;
	}

	public final String getName() {
		return name;
	}

	public final Class<T> getPropertyClass() {
		return propertyClass;
	}

	public final Property<T, ? super T> getPropertyForData(D input) {
		return propertyFunction.apply(input);
	}
}
