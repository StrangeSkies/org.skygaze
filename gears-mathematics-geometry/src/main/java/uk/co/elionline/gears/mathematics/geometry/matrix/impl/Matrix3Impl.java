package uk.co.elionline.gears.mathematics.geometry.matrix.impl;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.gears.mathematics.geometry.matrix.Matrix3;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector3;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl.Vector3Impl;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class Matrix3Impl<V extends Value<V>> extends MatrixSImpl<Matrix3<V>, V>
		implements Matrix3<V> {
	public Matrix3Impl(Order order, Factory<V> valueFactory) {
		super(3, order, valueFactory);
	}

	public Matrix3Impl(Factory<V> valueFactory) {
		super(3, valueFactory);
	}

	public Matrix3Impl(Order order, List<? extends List<? extends V>> values) {
		super(order, values);

		assertDimensions(this, 3);
	}

	public Matrix3Impl(List<? extends List<? extends V>> values) {
		super(values);

		assertDimensions(this, 3);
	}

	@Override
	public Matrix3<V> copy() {
		return new Matrix3Impl<V>(getData2());
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector3<V>> getRowVectors() {
		return (List<Vector3<V>>) super.getRowVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector3<V>> getColumnVectors() {
		return (List<Vector3<V>>) super.getColumnVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Vector3<V> getRowVector(int row) {
		return (Vector3Impl<V>) super.getRowVector(row);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Vector3<V> getColumnVector(int column) {
		return (Vector3Impl<V>) super.getColumnVector(column);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector3<V>> getMajorVectors() {
		return (List<Vector3<V>>) super.getMajorVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector3<V>> getMinorVectors() {
		return (List<Vector3<V>>) super.getMinorVectors();
	}

	@Override
	public final Vector3<V> getMajorVector(int index) {
		return new Vector3Impl<V>(getData2().get(index));
	}

	@Override
	public final Vector3<V> getMinorVector(int index) {
		List<V> minorElements = new ArrayList<V>();
		for (List<V> elements : getData2()) {
			minorElements.add(elements.get(index));
		}
		return new Vector3Impl<V>(minorElements);
	}
}
