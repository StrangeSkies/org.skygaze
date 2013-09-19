package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.values.Value;

/**
 * A potentially self touching, but otherwise traditionally 'simple', polygon.
 * 
 * @author eli
 * 
 * @param <S>
 * @param <V>
 */
public interface SimplePolygon<S extends SimplePolygon<S, V>, V extends Value<V>>
		extends Polygon<S, V> {
	public enum WindingDirection {
		Clockwise, Anticlockwise;
	}

	/**
	 * 
	 * @return
	 */
	public WindingDirection getWindingDirection();
}
