package uk.co.elionline.gears.mathematics.geometry.matrix.vector;

import uk.co.elionline.gears.mathematics.values.Value;

public interface VectorR<V extends Value<V>> extends Vector<VectorR<V>, V> {
	public VectorR<V> resize(int dimensions);

	public VectorR<V> getResized(int dimensions);
}
