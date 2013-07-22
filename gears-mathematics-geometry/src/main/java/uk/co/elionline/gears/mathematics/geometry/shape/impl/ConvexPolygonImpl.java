package uk.co.elionline.gears.mathematics.geometry.shape.impl;

import java.util.Collection;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.geometry.shape.ConvexPolygon;
import uk.co.elionline.gears.mathematics.geometry.shape.Polygon;
import uk.co.elionline.gears.mathematics.geometry.shape.Triangle;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.Value;

//convex only polygon
public class ConvexPolygonImpl<V extends Value<V>> extends
		SimplePolygonImpl<ConvexPolygon<V>, V> implements ConvexPolygon<V> {
	@Override
	public void set(Vector2<V>[] newVertices, int newVerticesLength) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Collection<? extends Vector2<?>> newVertices) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Polygon<?, ?> orig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(ConvexPolygon<?> orig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Vector2<?>[] newVertices, int newVerticesLength) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Collection<? extends Vector2<?>> newVertices) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Polygon<?, ?> orig) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean add(Vector2<?> point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Triangle<V>> triangleSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Set<IntValue>> TriangleIndicesset() {
		// TODO Auto-generated method stub
		return null;
	}
}