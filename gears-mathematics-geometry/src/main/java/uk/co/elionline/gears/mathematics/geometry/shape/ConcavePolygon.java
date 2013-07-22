package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.values.Value;

public interface ConcavePolygon<V extends Value<V>> extends
		SimplePolygon<ConcavePolygon<V>, V> {
	public void sanitize(int toleranceLevel);
}
