package uk.co.elionline.gears.entity.state;

import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.utilities.Property;

public class StateComponentProperty<D, T> {
	private final String name;

	private final Class<T> propertyClass;
	private final Function<Property<T, ? super T>, D> propertyFunction;

	public StateComponentProperty(String name, Class<T> propertyClass,
			Function<Property<T, ? super T>, D> propertyFunction) {
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
		return propertyFunction.applyTo(input);
	}
}
