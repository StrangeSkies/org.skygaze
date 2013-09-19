package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector;
import uk.co.elionline.gears.mathematics.values.Value;

public interface LineN<T extends Vector<?, V>, V extends Value<V>> {
	public T getA();

	public T getB();

	public T getAB();

	public T getBA();

	public Bounds<?, V> getBounds();

	public Value<?> getLength();

	public V getLengthSquared();
}
