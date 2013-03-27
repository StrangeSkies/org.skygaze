package uk.co.elionline.gears.mathematics.functions;

import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

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
