package uk.co.elionline.gears.mathematics.geometry.shape.impl;

import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.geometry.shape.Line;
import uk.co.elionline.gears.mathematics.geometry.shape.Shape;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class LineImpl<V extends Value<V>> implements Line<V> {
	private final Vector2<V> a;
	private final Vector2<V> b;

	public LineImpl(Factory<V> valueFactory) {
		a = new Vector2<>(valueFactory);
		b = new Vector2<>(valueFactory);
	}

	public LineImpl(Vector2<V> a, Vector2<V> b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public V getArea() {
		return a.getX().copy().setValue(0);
	}

	@Override
	public V getPerimeter() {
		return getLength().multiply(2);
	}

	@Override
	public V getLength() {
		return a.getSubtracted(b).getSize();
	}

	@Override
	public V getLengthSquared() {
		return a.getSubtracted(b).getSizeSquared();
	}

	@Override
	public Bounds2<V> getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Line<V> getThis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Line<V> copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Vector2<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touches(Vector2<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(Shape<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touches(Shape<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<?, V> getA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<?, V> getB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<?, V> getAB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<?, V> getBA() {
		// TODO Auto-generated method stub
		return null;
	}

}
