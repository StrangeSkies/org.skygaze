package uk.co.elionline.gears.mathematics.geometry.shape.impl;

import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.geometry.shape.ComplexPolygon;
import uk.co.elionline.gears.mathematics.geometry.shape.ConcavePolygon;
import uk.co.elionline.gears.mathematics.geometry.shape.Polygon;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.mathematics.values.ValueFactory;

/**
 * Complex polygons, self intersecting with holes and multiple parts.
 * 
 * @author eli
 * 
 * @param <V>
 */
public class ComplexPolygonImpl<V extends Value<V>> extends
		PolygonImpl<ComplexPolygon<V>, V> implements ComplexPolygon<V> {
	public ComplexPolygonImpl(Polygon<?, ?> polygon,
			ValueFactory<? extends V> valueFactory) {
	}

	public ComplexPolygonImpl(Polygon<?, ? extends V> polygon) {
	}

	public ComplexPolygonImpl(List<? extends Vector2<?>> polygon,
			ValueFactory<? extends V> valueFactory) {
	}

	public ComplexPolygonImpl(List<Vector2<V>> polygon) {
	}

	enum Winding {
		Odd, NonZero, CW, CCW
	}

	@Override
	public void set(Vector2<?>... newVertices) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(List<Vector2<?>> newVertices) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Polygon<?, ?> orig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(ComplexPolygon<?> orig) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<ConcavePolygon<V>> getContourSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ConcavePolygon<V>> getKeyholedContourSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConcavePolygon<V> getStitchedContour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<List<IntValue>> getContourIndicesSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<List<IntValue>> getKeyholedContourIndicesSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntValue> getStitchedContourIndices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexPolygon<V> and(Polygon<?, V> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexPolygon<V> nand(Polygon<?, V> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexPolygon<V> nor(Polygon<?, V> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexPolygon<V> or(Polygon<?, V> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexPolygon<V> xnor(Polygon<?, V> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexPolygon<V> xor(Polygon<?, V> value) {
		// TODO Auto-generated method stub
		return null;
	}
}