package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.Range;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector3;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class Bounds3<V extends Value<V>> extends Bounds<Bounds3<V>, V> {
	public Bounds3(Bounds<?, V> other) {
		super(other, 3);
	}

	public Bounds3(Bounds3<V> other) {
		super(other);
	}

	public Bounds3(Vector<?, V> from, Vector<?, V> to) {
		super(from, to, 3);
	}

	public Bounds3(Vector3<V> from, Vector3<V> to) {
		super(from, to);
	}

	public Bounds3(Factory<V> valueFactory) {
		super(3, valueFactory);
	}

	public final Range<V> getRangeX() {
		return super.getRange(0);
	}

	public final Range<V> getRangeY() {
		return super.getRange(1);
	}

	public final Range<V> getRangeZ() {
		return super.getRange(2);
	}

	@Override
	public final Bounds3<V> copy() {
		return new Bounds3<V>(this);
	}
}
