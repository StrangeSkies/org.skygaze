package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.values.Value;

/**
 * Complex polygons, self intersecting with holes and multiple parts.
 * 
 * @author eli
 * 
 * @param <V>
 */
public interface ComplexPolygon<S extends ComplexPolygon<S, V>, V extends Value<V>>
		extends Polygon<S, V> {
	enum Winding {
		Odd, NonZero, CW, CCW
	}
}
