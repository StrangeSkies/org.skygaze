package uk.co.elionline.gears.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class MatrixRR<V extends Value<V>> extends
		MatrixImplementation<MatrixRR<V>, V> {
	public MatrixRR(int rows, int columns, Order order, Factory<V> valueFactory) {
		super(rows, columns, order, valueFactory);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory) {
		super(rows, columns, valueFactory);
	}

	public MatrixRR(Matrix<?, V> other) {
		super(other);
	}

	public MatrixRR(MatrixS<?, V> other) {
		super(other);
	}

	public MatrixRR(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public MatrixRR(Factory<V> valueFactory, MatrixS<?, ?> other) {
		super(valueFactory, other);
	}

	public <I> MatrixRR(int rows, int columns, Order order,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(rows, columns, order, values, creationOperation);
	}

	public <I> MatrixRR(int rows, int columns, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(rows, columns, values, creationOperation);
	}

	public <I> MatrixRR(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, values, creationOperation);
	}

	public <I> MatrixRR(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	public MatrixRR(int rows, int columns, Order order,
			final Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixRR(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(order, valueFactory, values);
	}

	public MatrixRR(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Order order, Factory<V> valueFactory,
			Value<?>... values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory,
			Value<?>... values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Order order,
			List<? extends Value<V>> values) {
		super(rows, columns, order, values);
	}

	public MatrixRR(int rows, int columns, List<? extends Value<V>> values) {
		super(rows, columns, values);
	}

	public MatrixRR(Order order, List<? extends List<? extends Value<V>>> values) {
		super(order, values);
	}

	public MatrixRR(List<? extends List<? extends Value<V>>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixRR(int rows, int columns, Order order, Value<V>... values) {
		super(rows, columns, order, values);
	}

	@SafeVarargs
	public MatrixRR(int rows, int columns, Value<V>... values) {
		super(rows, columns, values);
	}

	public MatrixRR(int rows, int columns, boolean copyByReference, Order order,
			List<? extends V> values) {
		super(rows, columns, copyByReference, order, values);
	}

	public MatrixRR(int rows, int columns, boolean copyByReference,
			List<? extends V> values) {
		super(rows, columns, copyByReference, values);
	}

	public MatrixRR(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		super(copyByReference, order, values);
	}

	public MatrixRR(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixRR(int rows, int columns, boolean copyByReference, Order order,
			V... values) {
		super(rows, columns, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixRR(int rows, int columns, boolean copyByReference, V... values) {
		super(rows, columns, copyByReference, values);
	}

	public MatrixRR(int rows, int columns, Order order, Factory<V> valueFactory,
			Number... values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory,
			Number... values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Order order, Factory<V> valueFactory,
			int[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory, int[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Order order, Factory<V> valueFactory,
			long[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory, long[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Order order, Factory<V> valueFactory,
			float[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory, float[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Order order, Factory<V> valueFactory,
			double[] values) {
		super(rows, columns, order, valueFactory, values);
	}

	public MatrixRR(int rows, int columns, Factory<V> valueFactory,
			double[] values) {
		super(rows, columns, valueFactory, values);
	}

	public MatrixRR(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixRR(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public MatrixRR(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixRR(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public MatrixRR(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixRR(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public MatrixRR(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixRR(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	public MatrixRR(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);
	}

	public MatrixRR(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);
	}

	@SafeVarargs
	public MatrixRR(Order order, Vector<?, V>... values) {
		super(order, values);
	}

	@SafeVarargs
	public MatrixRR(Vector<?, V>... values) {
		super(values);
	}

	@SafeVarargs
	public MatrixRR(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixRR(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);
	}

	@Override
	public MatrixRR<V> getTransposed() {
		return copy().transposeImplementation();
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public MatrixRR<V> copy() {
		return new MatrixRR<V>(this);
	}

	public MatrixRR<V> resize(Vector<?, IntValue> dimensions) {
		return super.resizeImplementation(dimensions);
	}

	public MatrixRR<V> resize(int rows, int columns) {
		return super.resizeImplementation(rows, columns);
	}

	public MatrixRR<V> resizeColumns(int dimensions) {
		return super.resizeColumnsImplementation(dimensions);
	}

	public MatrixRR<V> resizeMajor(int dimensions) {
		return super.resizeMajorImplementation(dimensions);
	}

	public MatrixRR<V> resizeMinor(int dimensions) {
		return super.resizeMinorImplementation(dimensions);
	}

	public MatrixRR<V> resizeRows(int dimensions) {
		return super.resizeRowsImplementation(dimensions);
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
