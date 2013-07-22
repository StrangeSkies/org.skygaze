package uk.co.elionline.gears.mathematics.geometry.shape.impl;

import uk.co.elionline.gears.mathematics.geometry.shape.SimplePolygon;
import uk.co.elionline.gears.mathematics.values.Value;

//simple and potentially self touching polygons (relatively simple)
public abstract class SimplePolygonImpl<S extends SimplePolygon<S, V>, V extends Value<V>>
		extends PolygonImpl<S, V> implements SimplePolygon<S, V> {
	@Override
	public boolean isClockwise() {
		return false; // TODO default
	}
}
