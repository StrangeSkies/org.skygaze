package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.matrix.LineSegment;
import uk.co.elionline.gears.mathematics.values.Value;

public interface Line<V extends Value<V>> extends Shape<Line<V>>,
		LineSegment<V> {
	@Override
	public Bounds2<V> getBounds();
}
