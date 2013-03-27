package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public abstract class MatrixS<S extends MatrixS<S, V>, V extends Value<V>>
		extends MatrixImplementation<S, V> {
	public MatrixS(int size, Order order, Factory<V> valueFactory) {
		super(size, size, order, valueFactory);
	}

	public MatrixS(int size, Factory<V> valueFactory) {
		super(size, size, valueFactory);
	}

	public MatrixS(Matrix<?, V> other) {
		super(other);

		assertIsSquare(this);
	}

	public MatrixS(MatrixS<?, V> other) {
		super(other);
	}

	public MatrixS(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);

		assertIsSquare(this);
	}

	public MatrixS(Factory<V> valueFactory, MatrixS<?, ?> other) {
		super(valueFactory, other);
	}

	public <I> MatrixS(int size, Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, size, order, values, creationOperation);
	}

	public <I> MatrixS(int size, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, size, values, creationOperation);
	}

	public <I> MatrixS(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, values, creationOperation);

		assertIsSquare(this);
	}

	public <I> MatrixS(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);

		assertIsSquare(this);
	}

	public MatrixS(int size, Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, size, order, valueFactory, values);
	}

	public MatrixS(int size, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, size, valueFactory, values);
	}

	public MatrixS(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(order, valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(int size, Order order, Factory<V> valueFactory,
			Value<?>... values) {
		super(size, size, order, valueFactory, values);
	}

	public MatrixS(int size, Factory<V> valueFactory, Value<?>... values) {
		super(size, size, valueFactory, values);
	}

	public MatrixS(int size, Order order, List<? extends Value<V>> values) {
		super(size, size, order, values);
	}

	public MatrixS(int size, List<? extends Value<V>> values) {
		super(size, size, values);
	}

	public MatrixS(Order order, List<? extends List<? extends Value<V>>> values) {
		super(order, values);

		assertIsSquare(this);
	}

	public MatrixS(List<? extends List<? extends Value<V>>> values) {
		super(values);

		assertIsSquare(this);
	}

	@SafeVarargs
	public MatrixS(int size, Order order, Value<V>... values) {
		super(size, size, order, values);
	}

	@SafeVarargs
	public MatrixS(int size, Value<V>... values) {
		super(size, size, values);
	}

	public MatrixS(int size, boolean copyByReference, Order order,
			List<? extends V> values) {
		super(size, size, copyByReference, order, values);
	}

	public MatrixS(int size, boolean copyByReference, List<? extends V> values) {
		super(size, size, copyByReference, values);
	}

	public MatrixS(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		super(copyByReference, order, values);

		assertIsSquare(this);
	}

	public MatrixS(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		super(values);

		assertIsSquare(this);
	}

	@SafeVarargs
	public MatrixS(int size, boolean copyByReference, Order order, V... values) {
		super(size, size, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixS(int size, boolean copyByReference, V... values) {
		super(size, size, copyByReference, values);
	}

	public MatrixS(int size, Order order, Factory<V> valueFactory,
			Number... values) {
		super(size, size, order, valueFactory, values);
	}

	public MatrixS(int size, Factory<V> valueFactory, Number... values) {
		super(size, size, valueFactory, values);
	}

	public MatrixS(int size, Order order, Factory<V> valueFactory, int[] values) {
		super(size, size, order, valueFactory, values);
	}

	public MatrixS(int size, Factory<V> valueFactory, int[] values) {
		super(size, size, valueFactory, values);
	}

	public MatrixS(int size, Order order, Factory<V> valueFactory, long[] values) {
		super(size, size, order, valueFactory, values);
	}

	public MatrixS(int size, Factory<V> valueFactory, long[] values) {
		super(size, size, valueFactory, values);
	}

	public MatrixS(int size, Order order, Factory<V> valueFactory, float[] values) {
		super(size, size, order, valueFactory, values);
	}

	public MatrixS(int size, Factory<V> valueFactory, float[] values) {
		super(size, size, valueFactory, values);
	}

	public MatrixS(int size, Order order, Factory<V> valueFactory, double[] values) {
		super(size, size, order, valueFactory, values);
	}

	public MatrixS(int size, Factory<V> valueFactory, double[] values) {
		super(size, size, valueFactory, values);
	}

	public MatrixS(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);

		assertIsSquare(this);
	}

	public MatrixS(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);

		assertIsSquare(this);
	}

	@SafeVarargs
	public MatrixS(Order order, Vector<?, V>... values) {
		super(order, values);

		assertIsSquare(this);
	}

	@SafeVarargs
	public MatrixS(Vector<?, V>... values) {
		super(values);

		assertIsSquare(this);
	}

	@SafeVarargs
	public MatrixS(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);

		assertIsSquare(this);
	}

	@SafeVarargs
	public MatrixS(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);

		assertIsSquare(this);
	}

	@Override
	public S getTransposed() {
		return copy().transpose();
	}

	public final S transpose() {
		return transposeImplementation();
	}

	public int getDimensions() {
		return getMinorSize();
	}

	@Override
	public boolean isSquare() {
		return true;
	}

	protected S resize(int size) {
		return resizeImplementation(size, size);
	}
}
