package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.Range;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class Bounds2<V extends Value<V>> extends Bounds<Bounds2<V>, V> {
	public Bounds2(Bounds<?, V> other) throws DimensionalityException {
		super(other, 2);
	}

	public Bounds2(Bounds2<V> other) {
		super(other);
	}

	public Bounds2(Vector2<V> from, Vector2<V> to) {
		super(from, to);
	}

	public Bounds2(Vector<?, V> from, Vector<?, V> to) {
		super(from, to, 2);
	}

	public Bounds2(Factory<V> valueFactory) {
		super(2, valueFactory);
	}

	public final Range<V> getRangeX() {
		return super.getRange(0);
	}

	public final Range<V> getRangeY() {
		return super.getRange(1);
	}

	@Override
	public final Bounds2<V> copy() {
		return new Bounds2<V>(this);
	}
}
