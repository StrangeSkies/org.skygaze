package uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl;

import java.util.List;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.VectorHN;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class VectorHNImpl<V extends Value<V>> extends
		VectorHImpl<VectorHN<V>, V> implements VectorHN<V> {
	public VectorHNImpl(Type type, int size, Order order,
			Orientation orientation, Factory<V> valueFactory) {
		super(type, size, order, orientation, valueFactory);
	}

	public VectorHNImpl(Type type, int size, Factory<V> valueFactory) {
		super(type, size, valueFactory);
	}

	public VectorHNImpl(Type type, Order order, Orientation orientation,
			List<? extends V> values) {
		super(type, order, orientation, values);
	}

	public VectorHNImpl(Type type, List<? extends V> values) {
		super(type, values);
	}

	@Override
	public VectorNImpl<V> getMutableVector() {
		VectorNImpl<V> mutableVector = new VectorNImpl<>(getData().subList(0,
				getProjectedDimensions()));

		return mutableVector;
	}

	@Override
	public VectorHN<V> copy() {
		return new VectorHNImpl<>(getType(), getData());
	}
}
