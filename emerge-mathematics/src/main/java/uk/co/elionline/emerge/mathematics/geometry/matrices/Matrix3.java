package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class Matrix3<V extends Value<V>> extends MatrixS<Matrix3<V>, V> {
	public Matrix3(Order order, Factory<V> valueFactory) {
		super(3, order, valueFactory);
	}

	public Matrix3(Factory<V> valueFactory) {
		super(3, valueFactory);
	}

	public Matrix3(Matrix<?, V> other) {
		super(assertDimensions(other, 3));
	}

	public Matrix3(Matrix3<V> other) {
		super(other);
	}

	public Matrix3(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, assertDimensions(other, 3));
	}

	public Matrix3(Factory<V> valueFactory, Matrix3<?> other) {
		super(valueFactory, other);
	}

	public <I> Matrix3(Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(3, order, values, creationOperation);
	}

	public <I> Matrix3(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(3, values, creationOperation);
	}

	public <I> Matrix3(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, values, creationOperation);

		assertDimensions(this, 3);
	}

	public <I> Matrix3(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation);

		assertDimensions(this, 3);
	}

	public Matrix3(Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(3, order, valueFactory, values);
	}

	public Matrix3(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(3, valueFactory, values);
	}

	public Matrix3(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Order order, Factory<V> valueFactory, Value<?>... values) {
		super(3, order, valueFactory, values);
	}

	public Matrix3(Factory<V> valueFactory, Value<?>... values) {
		super(3, valueFactory, values);
	}

	public Matrix3(Order order, List<? extends Value<V>> values) {
		super(3, order, values);
	}

	public Matrix3(List<? extends Value<V>> values) {
		super(3, values);
	}

	public Matrix3(Order order, List<? extends List<? extends Value<V>>> values,
			Void flag) {
		super(order, values);

		assertDimensions(this, 3);
	}

	public Matrix3(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Matrix3(Order order, Value<V>... values) {
		super(3, order, values);
	}

	@SafeVarargs
	public Matrix3(Value<V>... values) {
		super(3, values);
	}

	public Matrix3(boolean copyByReference, Order order, List<? extends V> values) {
		super(3, copyByReference, order, values);
	}

	public Matrix3(boolean copyByReference, List<? extends V> values) {
		super(3, copyByReference, values);
	}

	public Matrix3(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, order, values);

		assertDimensions(this, 3);
	}

	public Matrix3(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Matrix3(boolean copyByReference, Order order, V... values) {
		super(3, copyByReference, order, values);
	}

	@SafeVarargs
	public Matrix3(boolean copyByReference, V... values) {
		super(3, copyByReference, values);
	}

	public Matrix3(Order order, Factory<V> valueFactory, Number... values) {
		super(3, order, valueFactory, values);
	}

	public Matrix3(Factory<V> valueFactory, Number... values) {
		super(3, valueFactory, values);
	}

	public Matrix3(Order order, Factory<V> valueFactory, int[] values) {
		super(3, order, valueFactory, values);
	}

	public Matrix3(Factory<V> valueFactory, int[] values) {
		super(3, valueFactory, values);
	}

	public Matrix3(Order order, Factory<V> valueFactory, long[] values) {
		super(3, order, valueFactory, values);
	}

	public Matrix3(Factory<V> valueFactory, long[] values) {
		super(3, valueFactory, values);
	}

	public Matrix3(Order order, Factory<V> valueFactory, float[] values) {
		super(3, order, valueFactory, values);
	}

	public Matrix3(Factory<V> valueFactory, float[] values) {
		super(3, valueFactory, values);
	}

	public Matrix3(Order order, Factory<V> valueFactory, double[] values) {
		super(3, order, valueFactory, values);
	}

	public Matrix3(Factory<V> valueFactory, double[] values) {
		super(3, valueFactory, values);
	}

	public Matrix3(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Matrix3(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Matrix3(Order order, Vector<?, V>... values) {
		super(order, values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Matrix3(Vector<?, V>... values) {
		super(values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Matrix3(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Matrix3(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);

		assertDimensions(this, 3);
	}

	@Override
	public Matrix3<V> copy() {
		return new Matrix3<V>(this);
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
		return (Vector3<V>) super.getRowVector(row);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Vector3<V> getColumnVector(int column) {
		return (Vector3<V>) super.getColumnVector(column);
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
		return new Vector3<V>(true, getData2().get(index));
	}

	@Override
	public final Vector3<V> getMinorVector(int index) {
		List<V> minorElements = new ArrayList<V>();
		for (List<V> elements : getData2()) {
			minorElements.add(elements.get(index));
		}
		return new Vector3<V>(true, minorElements);
	}
}
