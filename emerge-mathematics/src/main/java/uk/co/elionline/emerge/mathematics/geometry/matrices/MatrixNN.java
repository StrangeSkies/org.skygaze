package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class MatrixNN<V extends Value<V>> extends
		MatrixImplementation<MatrixNN<V>, V> {
	public MatrixNN(int rows, int columns, Order order, Factory<V> valueFactory) {
		super(rows, columns, order, valueFactory);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory) {
		super(rows, columns, valueFactory);
	}

	public MatrixNN(Matrix<?, V> other) {
		super(other);
	}

	public MatrixNN(MatrixS<?, V> other) {
		super(other);
	}

	public MatrixNN(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public MatrixNN(Factory<V> valueFactory, MatrixS<?, ?> other) {
		super(valueFactory, other);
	}

	public <I> MatrixNN(int rows, int columns, Order order,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(rows, columns, order, values, creationOperation);
	}

	public <I> MatrixNN(int rows, int columns, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(rows, columns, values, creationOperation);
	}

	public <I> MatrixNN(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, values, creationOperation);
	}

	public <I> MatrixNN(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	public MatrixNN(int rows, int columns, Order order,
			final Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixNN(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(order, valueFactory, values);
	}

	public MatrixNN(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Order order, Factory<V> valueFactory,
			Value<?>... values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory,
			Value<?>... values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Order order,
			List<? extends Value<V>> values) {
		super(rows, columns, order, values);
	}

	public MatrixNN(int rows, int columns, List<? extends Value<V>> values) {
		super(rows, columns, values);
	}

	public MatrixNN(Order order, List<? extends List<? extends Value<V>>> values) {
		super(order, values);
	}

	public MatrixNN(List<? extends List<? extends Value<V>>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixNN(int rows, int columns, Order order, Value<V>... values) {
		super(rows, columns, order, values);
	}

	@SafeVarargs
	public MatrixNN(int rows, int columns, Value<V>... values) {
		super(rows, columns, values);
	}

	public MatrixNN(int rows, int columns, boolean copyByReference, Order order,
			List<? extends V> values) {
		super(rows, columns, copyByReference, order, values);
	}

	public MatrixNN(int rows, int columns, boolean copyByReference,
			List<? extends V> values) {
		super(rows, columns, copyByReference, values);
	}

	public MatrixNN(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		super(copyByReference, order, values);
	}

	public MatrixNN(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixNN(int rows, int columns, boolean copyByReference, Order order,
			V... values) {
		super(rows, columns, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixNN(int rows, int columns, boolean copyByReference, V... values) {
		super(rows, columns, copyByReference, values);
	}

	public MatrixNN(int rows, int columns, Order order, Factory<V> valueFactory,
			Number... values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory,
			Number... values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Order order, Factory<V> valueFactory,
			int[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory, int[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Order order, Factory<V> valueFactory,
			long[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory, long[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Order order, Factory<V> valueFactory,
			float[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory, float[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Order order, Factory<V> valueFactory,
			double[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixNN(int rows, int columns, Factory<V> valueFactory,
			double[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixNN(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixNN(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public MatrixNN(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixNN(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public MatrixNN(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixNN(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public MatrixNN(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixNN(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	public MatrixNN(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);
	}

	public MatrixNN(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);
	}

	@SafeVarargs
	public MatrixNN(Order order, Vector<?, V>... values) {
		super(order, values);
	}

	@SafeVarargs
	public MatrixNN(Vector<?, V>... values) {
		super(values);
	}

	@SafeVarargs
	public MatrixNN(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixNN(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);
	}

	@Override
	public MatrixNN<V> getTransposed() {
		return copy().transposeImplementation();
	}

	@Override
	public boolean isResizable() {
		return false;
	}

	@Override
	public MatrixNN<V> copy() {
		return new MatrixNN<V>(this);
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
