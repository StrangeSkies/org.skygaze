package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class BoundsN<V extends Value<V>> extends Bounds<BoundsN<V>, V> {
	public BoundsN(Bounds<?, V> other) {
		super(other);
	}

	public BoundsN(Vector<?, V> from, Vector<?, V> to) {
		super(from, to);
	}

	public BoundsN(int size, Factory<V> valueFactory) {
		super(size, valueFactory);
	}

	@Override
	public final BoundsN<V> copy() {
		return new BoundsN<V>(this);
	}
}
