package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.LineN;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.values.Value;

public interface Line2<V extends Value<V>> extends Shape<Line2<V>>,
		LineN<Vector2<V>, V> {
	@Override
	public Bounds2<V> getBounds();
}
