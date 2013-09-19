package uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl;

import java.util.List;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.VectorH2;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class VectorH2Impl<V extends Value<V>> extends
		VectorHImpl<VectorH2<V>, V> implements VectorH2<V> {
	public VectorH2Impl(Type type, int size, Order order,
			Orientation orientation, Factory<V> valueFactory) {
		super(type, 2, order, orientation, valueFactory);
	}

	public VectorH2Impl(Type type, int size, Factory<V> valueFactory) {
		super(type, 2, valueFactory);
	}

	public VectorH2Impl(Type type, Order order, Orientation orientation,
			List<? extends V> values) {
		super(type, order, orientation, values);
	}

	public VectorH2Impl(Type type, List<? extends V> values) {
		super(type, values);
	}

	@Override
	protected void finalise() {
		super.finalise();

		assertDimensions(this, 3);
	}

	@Override
	public Vector2<V> getMutableVector() {
		Vector2<V> mutableVector = new Vector2Impl<V>(getData().subList(0, 2));

		return mutableVector;
	}

	@Override
	public VectorH2<V> copy() {
		return new VectorH2Impl<>(getType(), getData());
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

	@Override
	public VectorH2<V> getRotated(Value<?> angle) {
		return copy().rotate(angle);
	}

	@Override
	public VectorH2<V> rotate(Value<?> angle) {
		getMutableVector().rotate(angle);

		return getThis();
	}

	@Override
	public VectorH2<V> getRotated(Value<?> angle, Vector2<?> centre) {
		return copy().rotate(angle, centre);
	}

	@Override
	public VectorH2<V> rotate(Value<?> angle, Vector2<?> centre) {
		getMutableVector().rotate(angle, centre);

		return getThis();
	}
}
