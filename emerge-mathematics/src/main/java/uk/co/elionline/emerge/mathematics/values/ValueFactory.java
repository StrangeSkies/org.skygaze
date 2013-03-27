package uk.co.elionline.emerge.mathematics.values;

import uk.co.elionline.emerge.utilities.Factory;

public abstract class ValueFactory<V extends Value<V>> implements Factory<V> {
	public V get(int value) {
		return create().setValue(value);
	}

	public V get(long value) {
		return create().setValue(value);
	}

	public V get(double value) {
		return create().setValue(value);
	}

	public V get(float value) {
		return create().setValue(value);
	}
}
