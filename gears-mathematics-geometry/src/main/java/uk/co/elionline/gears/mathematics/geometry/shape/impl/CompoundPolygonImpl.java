package uk.co.elionline.gears.mathematics.geometry.shape.impl;

import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.matrix.builder.MatrixBuilder;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector.Orientation;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl.Vector2Impl;
import uk.co.elionline.gears.mathematics.geometry.shape.CompoundPolygon;
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
public class CompoundPolygonImpl<V extends Value<V>> extends
		PolygonImpl<CompoundPolygon<V>, V> implements CompoundPolygon<V> {
	public CompoundPolygonImpl(Polygon<?, ?> polygon,
			ValueFactory<? extends V> valueFactory) {
	}

	public CompoundPolygonImpl(Polygon<?, ? extends V> polygon) {
	}

	public CompoundPolygonImpl(List<? extends Vector2<?>> polygon,
			ValueFactory<? extends V> valueFactory) {
	}

	public CompoundPolygonImpl(List<? extends Vector2<V>> polygon) {
	}

	@Override
	public Value<?> getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vector2<V>> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundPolygon<V> copy() {
		Vector2<IntValue> v = null;
		IntValue a = new IntValue(1);
		IntValue b = new IntValue(2);

		MatrixBuilder matrices = null;
		v = new Vector2Impl<>(IntValue.factory(), 0, 1);

		v = matrices.ints().vector2().create().setData(0, 1)

		.setOrientation(Orientation.Column);
		v = matrices.ints().vector2().create(a, b);
		v = matrices.values(IntValue.factory()).vector2().create(0, 1);

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundPolygon<V> set(CompoundPolygon<V> to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Polygon<?, V>> getComponentSet() {
		// TODO Auto-generated method stub
		return null;
	}
}