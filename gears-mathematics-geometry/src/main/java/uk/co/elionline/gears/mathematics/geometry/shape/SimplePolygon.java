package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.values.Value;

//simple and potentially self touching polygons (relatively simple)
public interface SimplePolygon<S extends SimplePolygon<S, V>, V extends Value<V>>
		extends Polygon<S, V> {
	public boolean isClockwise();
}