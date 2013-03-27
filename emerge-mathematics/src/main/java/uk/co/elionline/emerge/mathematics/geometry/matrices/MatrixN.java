package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class MatrixN<V extends Value<V>> extends MatrixS<MatrixN<V>, V> {
	public MatrixN(int size, Order order, Factory<V> valueFactory) {
		super(size, order, valueFactory);
	}

	public MatrixN(int size, Factory<V> valueFactory) {
		super(size, valueFactory);
	}

	public MatrixN(Matrix<?, V> other) {
		super(other);
	}

	public MatrixN(MatrixS<?, V> other) {
		super(other);
	}

	public MatrixN(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public MatrixN(Factory<V> valueFactory, MatrixS<?, ?> other) {
		super(valueFactory, other);
	}

	public <I> MatrixN(int size, Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, order, values, creationOperation);
	}

	public <I> MatrixN(int size, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, values, creationOperation);
	}

	public <I> MatrixN(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, values, creationOperation);
	}

	public <I> MatrixN(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	public MatrixN(int size, Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, order, valueFactory, values);
	}

	public MatrixN(int size, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, valueFactory, values);
	}

	public MatrixN(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(order, valueFactory, values);
	}

	public MatrixN(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(valueFactory, values);
	}

	public MatrixN(int size, Order order, Factory<V> valueFactory,
			Value<?>... values) {
		super(size, order, valueFactory, values);
	}

	public MatrixN(int size, Factory<V> valueFactory, Value<?>... values) {
		super(size, valueFactory, values);
	}

	public MatrixN(int size, Order order, List<? extends Value<V>> values) {
		super(size, order, values);
	}

	public MatrixN(int size, List<? extends Value<V>> values) {
		super(size, values);
	}

	public MatrixN(Order order, List<? extends List<? extends Value<V>>> values) {
		super(order, values);
	}

	public MatrixN(List<? extends List<? extends Value<V>>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixN(int size, Order order, Value<V>... values) {
		super(size, order, values);
	}

	@SafeVarargs
	public MatrixN(int size, Value<V>... values) {
		super(size, values);
	}

	public MatrixN(int size, boolean copyByReference, Order order,
			List<? extends V> values) {
		super(size, copyByReference, order, values);
	}

	public MatrixN(int size, boolean copyByReference, List<? extends V> values) {
		super(size, copyByReference, values);
	}

	public MatrixN(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		super(copyByReference, order, values);
	}

	public MatrixN(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixN(int size, boolean copyByReference, Order order, V... values) {
		super(size, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixN(int size, boolean copyByReference, V... values) {
		super(size, copyByReference, values);
	}

	public MatrixN(int size, Order order, Factory<V> valueFactory,
			Number... values) {
		super(size, order, valueFactory, values);
	}

	public MatrixN(int size, Factory<V> valueFactory, Number... values) {
		super(size, valueFactory, values);
	}

	public MatrixN(int size, Order order, Factory<V> valueFactory, int[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixN(int size, Factory<V> valueFactory, int[] values) {
		super(size, valueFactory, values);
	}

	public MatrixN(int size, Order order, Factory<V> valueFactory, long[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixN(int size, Factory<V> valueFactory, long[] values) {
		super(size, valueFactory, values);
	}

	public MatrixN(int size, Order order, Factory<V> valueFactory, float[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixN(int size, Factory<V> valueFactory, float[] values) {
		super(size, valueFactory, values);
	}

	public MatrixN(int size, Order order, Factory<V> valueFactory, double[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixN(int size, Factory<V> valueFactory, double[] values) {
		super(size, valueFactory, values);
	}

	public MatrixN(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixN(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public MatrixN(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixN(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public MatrixN(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixN(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public MatrixN(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixN(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	public MatrixN(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);
	}

	public MatrixN(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);
	}

	@SafeVarargs
	public MatrixN(Order order, Vector<?, V>... values) {
		super(order, values);
	}

	@SafeVarargs
	public MatrixN(Vector<?, V>... values) {
		super(values);
	}

	@SafeVarargs
	public MatrixN(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixN(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);
	}

	@Override
	public MatrixN<V> copy() {
		return new MatrixN<V>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<VectorN<V>> getRowVectors() {
		return (List<VectorN<V>>) super.getRowVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<VectorN<V>> getColumnVectors() {
		return (List<VectorN<V>>) super.getColumnVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final VectorN<V> getRowVector(int row) {
		return (VectorN<V>) super.getRowVector(row);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final VectorN<V> getColumnVector(int column) {
		return (VectorN<V>) super.getColumnVector(column);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<VectorN<V>> getMajorVectors() {
		return (List<VectorN<V>>) super.getMajorVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<VectorN<V>> getMinorVectors() {
		return (List<VectorN<V>>) super.getMinorVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final VectorN<V> getMajorVector(int index) {
		return (VectorN<V>) super.getMajorVector(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final VectorN<V> getMinorVector(int index) {
		return (VectorN<V>) super.getMinorVector(index);
	}
}
