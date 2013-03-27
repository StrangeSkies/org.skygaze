package uk.co.elionline.gears.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class MatrixR<V extends Value<V>> extends MatrixS<MatrixR<V>, V> {
	public MatrixR(int size, Order order, Factory<V> valueFactory) {
		super(size, order, valueFactory);
	}

	public MatrixR(int size, Factory<V> valueFactory) {
		super(size, valueFactory);
	}

	public MatrixR(Matrix<?, V> other) {
		super(other);
	}

	public MatrixR(MatrixS<?, V> other) {
		super(other);
	}

	public MatrixR(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public MatrixR(Factory<V> valueFactory, MatrixS<?, ?> other) {
		super(valueFactory, other);
	}

	public <I> MatrixR(int size, Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, order, values, creationOperation);
	}

	public <I> MatrixR(int size, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, values, creationOperation);
	}

	public <I> MatrixR(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, values, creationOperation);
	}

	public <I> MatrixR(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	public MatrixR(int size, Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, order, valueFactory, values);
	}

	public MatrixR(int size, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, valueFactory, values);
	}

	public MatrixR(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(order, valueFactory, values);
	}

	public MatrixR(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(valueFactory, values);
	}

	public MatrixR(int size, Order order, Factory<V> valueFactory,
			Value<?>... values) {
		super(size, order, valueFactory, values);
	}

	public MatrixR(int size, Factory<V> valueFactory, Value<?>... values) {
		super(size, valueFactory, values);
	}

	public MatrixR(int size, Order order, List<? extends Value<V>> values) {
		super(size, order, values);
	}

	public MatrixR(int size, List<? extends Value<V>> values) {
		super(size, values);
	}

	public MatrixR(Order order, List<? extends List<? extends Value<V>>> values) {
		super(order, values);
	}

	public MatrixR(List<? extends List<? extends Value<V>>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixR(int size, Order order, Value<V>... values) {
		super(size, order, values);
	}

	@SafeVarargs
	public MatrixR(int size, Value<V>... values) {
		super(size, values);
	}

	public MatrixR(int size, boolean copyByReference, Order order,
			List<? extends V> values) {
		super(size, copyByReference, order, values);
	}

	public MatrixR(int size, boolean copyByReference, List<? extends V> values) {
		super(size, copyByReference, values);
	}

	public MatrixR(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		super(copyByReference, order, values);
	}

	public MatrixR(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixR(int size, boolean copyByReference, Order order, V... values) {
		super(size, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixR(int size, boolean copyByReference, V... values) {
		super(size, copyByReference, values);
	}

	public MatrixR(int size, Order order, Factory<V> valueFactory,
			Number... values) {
		super(size, order, valueFactory, values);
	}

	public MatrixR(int size, Factory<V> valueFactory, Number... values) {
		super(size, valueFactory, values);
	}

	public MatrixR(int size, Order order, Factory<V> valueFactory, int[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixR(int size, Factory<V> valueFactory, int[] values) {
		super(size, valueFactory, values);
	}

	public MatrixR(int size, Order order, Factory<V> valueFactory, long[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixR(int size, Factory<V> valueFactory, long[] values) {
		super(size, valueFactory, values);
	}

	public MatrixR(int size, Order order, Factory<V> valueFactory, float[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixR(int size, Factory<V> valueFactory, float[] values) {
		super(size, valueFactory, values);
	}

	public MatrixR(int size, Order order, Factory<V> valueFactory, double[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixR(int size, Factory<V> valueFactory, double[] values) {
		super(size, valueFactory, values);
	}

	public MatrixR(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixR(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public MatrixR(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixR(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public MatrixR(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixR(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public MatrixR(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixR(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	public MatrixR(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);
	}

	public MatrixR(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);
	}

	@SafeVarargs
	public MatrixR(Order order, Vector<?, V>... values) {
		super(order, values);
	}

	@SafeVarargs
	public MatrixR(Vector<?, V>... values) {
		super(values);
	}

	@SafeVarargs
	public MatrixR(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixR(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public MatrixR<V> copy() {
		return new MatrixR<V>(this).getThis();
	}

	@Override
	public MatrixR<V> resize(int size) {
		return super.resize(size);
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
