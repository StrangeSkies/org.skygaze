package uk.co.elionline.emerge.mathematics.functions;

import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class NumberToValueFunction<V extends Value<V>> implements
		Function<V, Number> {
	private final Factory<V> valueFactory;

	public <X extends V> NumberToValueFunction(Factory<V> valueFactory) {
		this.valueFactory = valueFactory;
	}

	@Override
	public V applyTo(Number input) {
		return valueFactory.create().setValue(input);
	}
}
