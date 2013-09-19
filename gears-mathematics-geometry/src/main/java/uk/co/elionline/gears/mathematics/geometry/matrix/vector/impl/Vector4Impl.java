package uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl;

import java.util.List;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector4;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class Vector4Impl<V extends Value<V>> extends VectorImpl<Vector4<V>, V>
		implements Vector4<V> {
	public Vector4Impl(Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(4, order, orientation, valueFactory);
	}

	public Vector4Impl(Factory<V> valueFactory) {
		super(4, valueFactory);
	}

	public Vector4Impl(List<? extends V> other) {
		super(other);

		assertDimensions(this, 4);
	}

	public Vector4Impl(Order order, Orientation orientation,
			List<? extends V> values) {
		super(order, orientation, values);

		assertDimensions(this, 4);
	}

	@Override
	public Vector4<V> copy() {
		return new Vector4Impl<V>(getData());
	}

	@Override
	public V getX() {
		return getElement(0);
	}

	@Override
	public V getY() {
		return getElement(1);
	}

	@Override
	public V getZ() {
		return getElement(2);
	}

	@Override
	public V getW() {
		return getElement(3);
	}
}
