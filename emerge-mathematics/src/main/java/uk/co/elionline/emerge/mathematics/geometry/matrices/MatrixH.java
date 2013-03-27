package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.functions.collections.ListTransformOnceView;
import uk.co.elionline.emerge.mathematics.functions.collections.ListTransformation;
import uk.co.elionline.emerge.mathematics.functions.collections.SubListFunction;
import uk.co.elionline.emerge.mathematics.geometry.NonCommutativelyTranslatable;
import uk.co.elionline.emerge.mathematics.geometry.matrices.VectorH.Type;
import uk.co.elionline.emerge.mathematics.values.IntValue;
import uk.co.elionline.emerge.mathematics.values.IntValueFactory;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;
import uk.co.elionline.emerge.utilities.Self;
import uk.co.elionline.emerge.utilities.collections.SubList;

public abstract class MatrixH<S extends MatrixH<S, V>, V extends Value<V>>
		extends /*@ReadOnly*/MatrixImplementation<S, V> implements Self<S>,
		NonCommutativelyTranslatable<S> {
	@SuppressWarnings("unchecked")
	public MatrixH(int size, Order order, Factory<V> valueFactory) {
		super(size + 1, size, order, valueFactory);

		for (int i = 0; i < getProjectedDimensions(); i++) {
			((V) getElement(i, i)).setValue(1);
		}
	}

	@SuppressWarnings("unchecked")
	public MatrixH(int size, Factory<V> valueFactory) {
		super(size + 1, size, valueFactory);

		for (int i = 0; i < getProjectedDimensions(); i++) {
			((V) getElement(i, i)).setValue(1);
		}
	}

	public MatrixH(Matrix<?, V> other) {
		super(other);
	}

	public MatrixH(MatrixH<?, V> other) {
		super(other.getMutableMatrix());
	}

	public MatrixH(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public MatrixH(Factory<V> valueFactory, MatrixH<?, ?> other) {
		super(valueFactory, other.getMutableMatrix());
	}

	public <I> MatrixH(int size, Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size + 1, size, order, values, creationOperation);
	}

	public <I> MatrixH(int size, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size + 1, size, values, creationOperation);
	}

	public <I> MatrixH(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, values, creationOperation);
	}

	public <I> MatrixH(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	public MatrixH(int size, Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size + 1, size, order, valueFactory, values);
	}

	public MatrixH(int size, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size + 1, size, valueFactory, values);
	}

	public MatrixH(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(order, valueFactory, values);
	}

	public MatrixH(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(valueFactory, values);
	}

	public MatrixH(int size, Order order, Factory<V> valueFactory,
			Value<?>... values) {
		super(size + 1, size, order, valueFactory, values);
	}

	public MatrixH(int size, Factory<V> valueFactory, Value<?>... values) {
		super(size + 1, size, valueFactory, values);
	}

	public MatrixH(int size, Order order, List<? extends Value<V>> values) {
		super(size + 1, size, order, values);
	}

	public MatrixH(int size, List<? extends Value<V>> values) {
		super(size + 1, size, values);
	}

	public MatrixH(Order order, List<? extends List<? extends Value<V>>> values) {
		super(order, values);
	}

	public MatrixH(List<? extends List<? extends Value<V>>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixH(int size, Order order, Value<V>... values) {
		super(size + 1, size, order, values);
	}

	@SafeVarargs
	public MatrixH(int size, Value<V>... values) {
		super(size + 1, size, values);
	}

	public MatrixH(int size, boolean copyByReference, Order order,
			List<? extends V> values) {
		super(size + 1, size, copyByReference, order, values);
	}

	public MatrixH(int size, boolean copyByReference, List<? extends V> values) {
		super(size + 1, size, copyByReference, values);
	}

	public MatrixH(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		super(copyByReference, order, values);
	}

	public MatrixH(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixH(int size, boolean copyByReference, Order order, V... values) {
		super(size + 1, size, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixH(int size, boolean copyByReference, V... values) {
		super(size + 1, size, copyByReference, values);
	}

	public MatrixH(int size, Order order, Factory<V> valueFactory,
			Number... values) {
		super(size + 1, size, order, valueFactory, values);
	}

	public MatrixH(int size, Factory<V> valueFactory, Number... values) {
		super(size + 1, size, valueFactory, values);
	}

	public MatrixH(int size, Order order, Factory<V> valueFactory, int[] values) {
		super(size + 1, size, order, valueFactory, values);
	}

	public MatrixH(int size, Factory<V> valueFactory, int[] values) {
		super(size + 1, size, valueFactory, values);
	}

	public MatrixH(int size, Order order, Factory<V> valueFactory, long[] values) {
		super(size + 1, size, order, valueFactory, values);
	}

	public MatrixH(int size, Factory<V> valueFactory, long[] values) {
		super(size + 1, size, valueFactory, values);
	}

	public MatrixH(int size, Order order, Factory<V> valueFactory, float[] values) {
		super(size + 1, size, order, valueFactory, values);
	}

	public MatrixH(int size, Factory<V> valueFactory, float[] values) {
		super(size + 1, size, valueFactory, values);
	}

	public MatrixH(int size, Order order, Factory<V> valueFactory, double[] values) {
		super(size + 1, size, order, valueFactory, values);
	}

	public MatrixH(int size, Factory<V> valueFactory, double[] values) {
		super(size + 1, size, valueFactory, values);
	}

	public MatrixH(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixH(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public MatrixH(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixH(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public MatrixH(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixH(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public MatrixH(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixH(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	public MatrixH(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);
	}

	public MatrixH(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);
	}

	@SafeVarargs
	public MatrixH(Order order, Vector<?, V>... values) {
		super(order, values);
	}

	@SafeVarargs
	public MatrixH(Vector<?, V>... values) {
		super(values);
	}

	@SafeVarargs
	public MatrixH(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixH(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void finalise() {
		int size = getColumnSize();

		resizeColumnsImplementation(size + 1);

		((V) getElement(size, size)).setValue(1);

		assertIsSquare(this);
	}

	@Override
	public MatrixN<V> getTransposed() {
		return new MatrixN<V>(this).transpose();
	}

	@Override
	public boolean isResizable() {
		return false;
	}

	public int getDimensions() {
		return getMinorSize();
	}

	public int getProjectedDimensions() {
		return getDimensions() - 1;
	}

	public MatrixNN<V> getMutableMatrix() {
		List<List<V>> dataView;

		if (getOrder() == Order.ColumnMajor) {
			dataView = new ListTransformOnceView<List<V>, List<V>>(getData2(),
					new SubListFunction<V>(0, getProjectedDimensions()));
		} else {
			dataView = new SubList<>(getData2(), 0, getProjectedDimensions());
		}

		return new MatrixNN<V>(true, getOrder(), dataView);
	}

	protected List<List<V>> getTransformationData2() {
		return new ListTransformation<List<V>>(getData2().subList(0,
				getProjectedDimensions()), new SubListFunction<V>(0,
				getProjectedDimensions()));
	}

	public abstract MatrixS<?, V> getTransformationMatrix();

	public static void main(String[] args) {
		MatrixHN<IntValue> matrix = new MatrixHN<>(2, Order.RowMajor,
				IntValueFactory.instance(), 1, 2, 3, 4, 5, 6);
		System.out.println("original:\n  " + matrix);

		MatrixNN<IntValue> mutable = matrix.getMutableMatrix();
		System.out.println("mutable portion:\n  " + mutable);

		mutable.multiply(2);
		System.out.println("x2:\n  " + matrix);

		MatrixNN<IntValue> transposed = mutable.getTransposed();
		System.out.println("mutable portion transposed:\n  " + transposed);

		transposed.multiply(2);
		System.out.println("x4:\n  " + matrix);

		MatrixH2<IntValue> newMatrix = new MatrixH2<>(matrix);
		System.out.println("newMatrix:\n  " + newMatrix);
	}

	@Override
	public S translate(Vector<?, ?> translation) {
		getColumnVector(getProjectedDimensions()).translate(translation);

		return getThis();
	}

	@Override
	public S getTranslated(Vector<?, ?> translation) {
		return copy().translate(translation);
	}

	@Override
	public S preTranslate(Vector<?, ?> translation) {
		// TODO implement pre-rotation
		return null;
	}

	@Override
	public S getPreTranslated(Vector<?, ?> translation) {
		return copy().preTranslate(translation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends VectorH<?, V>> getColumnVectors() {
		return (List<? extends VectorH<?, V>>) super.getColumnVectors();
	}

	@Override
	public VectorH<?, V> getColumnVector(int column) {
		return new VectorHN<V>(column == getDimensions() - 1 ? Type.Absolute
				: Type.Relative, true, getColumnVectorData(getProjectedDimensions())
				.subList(0, getProjectedDimensions()));
	}
}
