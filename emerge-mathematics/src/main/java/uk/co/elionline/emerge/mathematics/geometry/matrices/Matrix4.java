package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class Matrix4<V extends Value<V>> extends MatrixS<Matrix4<V>, V> {
	public Matrix4(Order order, Factory<V> valueFactory) {
		super(4, order, valueFactory);
	}

	public Matrix4(Factory<V> valueFactory) {
		super(4, valueFactory);
	}

	public Matrix4(Matrix<?, V> other) {
		super(assertDimensions(other, 4));
	}

	public Matrix4(Matrix4<V> other) {
		super(other);
	}

	public Matrix4(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, assertDimensions(other, 4));
	}

	public Matrix4(Factory<V> valueFactory, Matrix4<?> other) {
		super(valueFactory, other);
	}

	public <I> Matrix4(Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(4, order, values, creationOperation);
	}

	public <I> Matrix4(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(4, values, creationOperation);
	}

	public <I> Matrix4(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, values, creationOperation);

		assertDimensions(this, 4);
	}

	public <I> Matrix4(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation);

		assertDimensions(this, 4);
	}

	public Matrix4(Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(4, order, valueFactory, values);
	}

	public Matrix4(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(4, valueFactory, values);
	}

	public Matrix4(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Order order, Factory<V> valueFactory, Value<?>... values) {
		super(4, order, valueFactory, values);
	}

	public Matrix4(Factory<V> valueFactory, Value<?>... values) {
		super(4, valueFactory, values);
	}

	public Matrix4(Order order, List<? extends Value<V>> values) {
		super(4, order, values);
	}

	public Matrix4(List<? extends Value<V>> values) {
		super(4, values);
	}

	public Matrix4(Order order, List<? extends List<? extends Value<V>>> values,
			Void flag) {
		super(order, values);

		assertDimensions(this, 4);
	}

	public Matrix4(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Matrix4(Order order, Value<V>... values) {
		super(4, order, values);
	}

	@SafeVarargs
	public Matrix4(Value<V>... values) {
		super(4, values);
	}

	public Matrix4(boolean copyByReference, Order order, List<? extends V> values) {
		super(4, copyByReference, order, values);
	}

	public Matrix4(boolean copyByReference, List<? extends V> values) {
		super(4, copyByReference, values);
	}

	public Matrix4(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, order, values);

		assertDimensions(this, 4);
	}

	public Matrix4(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Matrix4(boolean copyByReference, Order order, V... values) {
		super(4, copyByReference, order, values);
	}

	@SafeVarargs
	public Matrix4(boolean copyByReference, V... values) {
		super(4, copyByReference, values);
	}

	public Matrix4(Order order, Factory<V> valueFactory, Number... values) {
		super(4, order, valueFactory, values);
	}

	public Matrix4(Factory<V> valueFactory, Number... values) {
		super(4, valueFactory, values);
	}

	public Matrix4(Order order, Factory<V> valueFactory, int[] values) {
		super(4, order, valueFactory, values);
	}

	public Matrix4(Factory<V> valueFactory, int[] values) {
		super(4, valueFactory, values);
	}

	public Matrix4(Order order, Factory<V> valueFactory, long[] values) {
		super(4, order, valueFactory, values);
	}

	public Matrix4(Factory<V> valueFactory, long[] values) {
		super(4, valueFactory, values);
	}

	public Matrix4(Order order, Factory<V> valueFactory, float[] values) {
		super(4, order, valueFactory, values);
	}

	public Matrix4(Factory<V> valueFactory, float[] values) {
		super(4, valueFactory, values);
	}

	public Matrix4(Order order, Factory<V> valueFactory, double[] values) {
		super(4, order, valueFactory, values);
	}

	public Matrix4(Factory<V> valueFactory, double[] values) {
		super(4, valueFactory, values);
	}

	public Matrix4(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Matrix4(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Matrix4(Order order, Vector<?, V>... values) {
		super(order, values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Matrix4(Vector<?, V>... values) {
		super(values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Matrix4(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Matrix4(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);

		assertDimensions(this, 4);
	}

	@Override
	public Matrix4<V> copy() {
		return new Matrix4<V>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector4<V>> getRowVectors() {
		return (List<Vector4<V>>) super.getRowVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector4<V>> getColumnVectors() {
		return (List<Vector4<V>>) super.getColumnVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Vector4<V> getRowVector(int row) {
		return (Vector4<V>) super.getRowVector(row);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Vector4<V> getColumnVector(int column) {
		return (Vector4<V>) super.getColumnVector(column);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector4<V>> getMajorVectors() {
		return (List<Vector4<V>>) super.getMajorVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector4<V>> getMinorVectors() {
		return (List<Vector4<V>>) super.getMinorVectors();
	}

	@Override
	public final Vector4<V> getMajorVector(int index) {
		return new Vector4<V>(true, getData2().get(index));
	}

	@Override
	public final Vector4<V> getMinorVector(int index) {
		List<V> minorElements = new ArrayList<V>();
		for (List<V> elements : getData2()) {
			minorElements.add(elements.get(index));
		}
		return new Vector4<V>(true, minorElements);
	}
}
