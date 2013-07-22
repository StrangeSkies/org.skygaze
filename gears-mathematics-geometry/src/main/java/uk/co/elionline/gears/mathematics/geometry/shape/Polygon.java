package uk.co.elionline.gears.mathematics.geometry.shape;

import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.logic.BooleanCombinationBehaviour;
import uk.co.elionline.gears.mathematics.values.Value;

public interface Polygon<S extends Polygon<S, V>, V extends Value<V>> extends
		Shape<S>, /*@ReadOnly*/
		BooleanCombinationBehaviour<ComplexPolygon<V>, Polygon<?, V>> {
	public enum WindingDirection {
		Clockwise, Anticlockwise;
	}

	public Set</*@Immutable*/List</*@Immutable*/Integer>> getTessellationIndices(
			int limit, TessellationScheme tessellation) /*@ReadOnly*/;

	public Set</*@Immutable*/Polygon<?, V>> getTessellationShapes(int limit,
			TessellationScheme tessellation) /*@ReadOnly*/;

	/**
	 * Gets the area where anti-clockwise wound polygons are negative.
	 * 
	 * @return
	 */
	@Override
	public Value<?> getArea();

	/**
	 * Vertices describing polygon
	 * 
	 * Guaranteed to be continuous/unbroken path describing the polygon, but may
	 * include repeated elements where there is self intersection of edges. In the
	 * case of a ComplexPolygon class, for example, this will effectively return a
	 * keyholed and stitched representation of the component contours.
	 * 
	 * The winding direction of the returned list of vertices should correspond to
	 * the result of {@link Polygon#getWindingDirection()}.
	 */
	public/*@ReadOnly*/List<Vector2<V>> getVertices()/*@ReadOnly*/;

	/**
	 * 
	 * @return
	 */
	public WindingDirection setWindingDirection();

	/**
	 * 
	 * @return
	 */
	public WindingDirection getWindingDirection();
}
