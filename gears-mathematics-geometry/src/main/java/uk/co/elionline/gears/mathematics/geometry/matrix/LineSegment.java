package uk.co.elionline.gears.mathematics.geometry.matrix;

import uk.co.elionline.gears.mathematics.geometry.Bounds;
import uk.co.elionline.gears.mathematics.values.Value;

public interface LineSegment<V extends Value<V>> {
	public Vector<?, V> getA();

	public Vector<?, V> getB();

	public Vector<?, V> getAB();

	public Vector<?, V> getBA();

	public Bounds<?, V> getBounds();

	public V getLength();

	public V getLengthSquared();
}
