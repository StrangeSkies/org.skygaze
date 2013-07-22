package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

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
