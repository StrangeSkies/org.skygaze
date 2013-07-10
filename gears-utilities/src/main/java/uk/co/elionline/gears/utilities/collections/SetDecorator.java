package uk.co.elionline.gears.utilities.collections;

import java.util.Set;

import uk.co.elionline.gears.utilities.Property;

public class SetDecorator<T> extends CollectionDecorator<Set<T>, T> implements
		Set<T> {
	public SetDecorator(Set<T> component) {
		super(component);
	}

	public SetDecorator(Property<Set<T>, ? super Set<T>> component) {
		super(component);
	}
}
