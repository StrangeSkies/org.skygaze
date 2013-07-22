package uk.co.elionline.gears.mathematics.geometry.shape;

import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.logic.BooleanCombinationBehaviour;
import uk.co.elionline.gears.mathematics.values.Value;

/**
 * Complex polygons, self intersecting with holes and multiple parts.
 * 
 * @author eli
 * 
 * @param <V>
 */
public interface ComplexPolygon<V extends Value<V>> extends
		Polygon<ComplexPolygon<V>, V>, /*@Mutable*/
		BooleanCombinationBehaviour<ComplexPolygon<V>, Polygon<?, V>> {
	enum Winding {
		Odd, NonZero, CW, CCW
	}

	public void set(/*@ReadOnly*/Vector2<?>... newVertices);

	public void set(/*@ReadOnly*/List<Vector2<?>> newVertices);

	public void set(/*@ReadOnly*/Polygon<?, ?> orig);

	public void set(/*@ReadOnly*/ComplexPolygon<?> orig);

	/**
	 * return indices of complete separate contours
	 * 
	 * @return
	 */
	public Set<ConcavePolygon<V>> getContourSet() /*@ReadOnly*/;

	/**
	 * return indices of keyholed hole contours
	 * 
	 * @return
	 */
	public Set<ConcavePolygon<V>> getKeyholedContourSet() /*@ReadOnly*/;
}
