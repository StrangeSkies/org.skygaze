package uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl;

import java.util.List;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.VectorH3;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class VectorH3Impl<V extends Value<V>> extends
		VectorHImpl<VectorH3<V>, V> implements VectorH3<V> {
	public VectorH3Impl(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(type, 3, order, orientation, valueFactory);
	}

	public VectorH3Impl(Type type, Factory<V> valueFactory) {
		super(type, 3, valueFactory);
	}

	public VectorH3Impl(Type type, Order order, Orientation orientation,
			List<? extends V> values) {
		super(type, order, orientation, values);
	}

	public VectorH3Impl(Type type, List<? extends V> values) {
		super(type, values);
	}

	@Override
	protected void finalise() {
		super.finalise();

		assertDimensions(this, 4);
	}

	@Override
	public Vector2<V> getMutableVector() {
		Vector2<V> mutableVector = new Vector2Impl<V>(getData().subList(0, 3));

		return mutableVector;
	}

	@Override
	public VectorH3<V> copy() {
		return new VectorH3Impl<>(getType(), getData());
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
	public V getW() {
		return getElement(2);
	}
}
