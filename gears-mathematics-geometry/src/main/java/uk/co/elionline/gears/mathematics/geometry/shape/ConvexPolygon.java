package uk.co.elionline.gears.mathematics.geometry.shape;

import java.util.Collection;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.Value;

//convex only polygon
public interface ConvexPolygon<V extends Value<V>> extends
		SimplePolygon<ConvexPolygon<V>, V> {
	// set as convex hull of input
	public void set(/*@ReadOnly*/Vector2<V> newVertices[], int newVerticesLength);

	public void set(/*@ReadOnly*/Collection<? extends Vector2<?>> newVertices);

	public void set(/*@ReadOnly*/Polygon<?, ?> orig);

	public void set(/*@ReadOnly*/ConvexPolygon<?> orig);

	// create convex hull including new point, false if no change
	public void add(/*@ReadOnly*/Vector2<?> newVertices[], int newVerticesLength);

	public void add(/*@ReadOnly*/Collection<? extends Vector2<?>> newVertices);

	public void add(/*@ReadOnly*/Polygon<?, ?> orig);

	public boolean add(/*@ReadOnly*/Vector2<?> point);

	public Set<Triangle<V>> triangleSet() /*@ReadOnly*/; // based on indices
																												// varient

	public Set<Set</*Immutable*/IntValue>> TriangleIndicesset() /*@ReadOnly*/;
}