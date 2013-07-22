package uk.co.elionline.gears.mathematics.geometry.shape.impl;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.geometry.shape.Triangle;
import uk.co.elionline.gears.mathematics.values.Value;

public class TriangleImpl<V extends Value<V>> extends
		SimplePolygonImpl<Triangle<V>, V> implements Triangle<V> {
	public TriangleImpl(Vector2<V> a, Vector2<V> b, Vector2<V> c) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isClockwise() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(Vector2<?> newCornerA, Vector2<?> newCornerB,
			Vector2<?> newCornerC) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(float ax, float ay, float bx, float by, float cx, float cy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Triangle<?> orig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setA(Vector2<?> newCornerA) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setB(Vector2<?> newCornerB) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setC(Vector2<?> newCornerC) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector2<V> GetA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2<V> GetB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2<V> GetC() {
		// TODO Auto-generated method stub
		return null;
	}
}
