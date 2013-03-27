package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.geometry.matrices.VectorH.Type;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class MatrixHN<V extends Value<V>> extends MatrixH<MatrixHN<V>, V> {
	public MatrixHN(int size, Order order, Factory<V> valueFactory) {
		super(size, order, valueFactory);
	}

	public MatrixHN(int size, Factory<V> valueFactory) {
		super(size, valueFactory);
	}

	public MatrixHN(Matrix<?, V> other) {
		super(other);
	}

	public MatrixHN(MatrixH<?, V> other) {
		super(other);
	}

	public MatrixHN(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public MatrixHN(Factory<V> valueFactory, MatrixH<?, ?> other) {
		super(valueFactory, other);
	}

	public <I> MatrixHN(int size, Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, order, values, creationOperation);
	}

	public <I> MatrixHN(int size, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(size, values, creationOperation);
	}

	public <I> MatrixHN(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, values, creationOperation);
	}

	public <I> MatrixHN(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	public MatrixHN(int size, Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, order, valueFactory, values);
	}

	public MatrixHN(int size, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(size, valueFactory, values);
	}

	public MatrixHN(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(order, valueFactory, values);
	}

	public MatrixHN(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		super(valueFactory, values);
	}

	public MatrixHN(int size, Order order, Factory<V> valueFactory,
			Value<?>... values) {
		super(size, order, valueFactory, values);
	}

	public MatrixHN(int size, Factory<V> valueFactory, Value<?>... values) {
		super(size, valueFactory, values);
	}

	public MatrixHN(int size, Order order, List<? extends Value<V>> values) {
		super(size, order, values);
	}

	public MatrixHN(int size, List<? extends Value<V>> values) {
		super(size, values);
	}

	public MatrixHN(Order order, List<? extends List<? extends Value<V>>> values) {
		super(order, values);
	}

	public MatrixHN(List<? extends List<? extends Value<V>>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixHN(int size, Order order, Value<V>... values) {
		super(size, order, values);
	}

	@SafeVarargs
	public MatrixHN(int size, Value<V>... values) {
		super(size, values);
	}

	public MatrixHN(int size, boolean copyByReference, Order order,
			List<? extends V> values) {
		super(size, copyByReference, order, values);
	}

	public MatrixHN(int size, boolean copyByReference, List<? extends V> values) {
		super(size, copyByReference, values);
	}

	public MatrixHN(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		super(copyByReference, order, values);
	}

	public MatrixHN(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		super(values);
	}

	@SafeVarargs
	public MatrixHN(int size, boolean copyByReference, Order order, V... values) {
		super(size, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixHN(int size, boolean copyByReference, V... values) {
		super(size, copyByReference, values);
	}

	public MatrixHN(int size, Order order, Factory<V> valueFactory,
			Number... values) {
		super(size, order, valueFactory, values);
	}

	public MatrixHN(int size, Factory<V> valueFactory, Number... values) {
		super(size, valueFactory, values);
	}

	public MatrixHN(int size, Order order, Factory<V> valueFactory, int[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixHN(int size, Factory<V> valueFactory, int[] values) {
		super(size, valueFactory, values);
	}

	public MatrixHN(int size, Order order, Factory<V> valueFactory, long[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixHN(int size, Factory<V> valueFactory, long[] values) {
		super(size, valueFactory, values);
	}

	public MatrixHN(int size, Order order, Factory<V> valueFactory, float[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixHN(int size, Factory<V> valueFactory, float[] values) {
		super(size, valueFactory, values);
	}

	public MatrixHN(int size, Order order, Factory<V> valueFactory,
			double[] values) {
		super(size, order, valueFactory, values);
	}

	public MatrixHN(int size, Factory<V> valueFactory, double[] values) {
		super(size, valueFactory, values);
	}

	public MatrixHN(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixHN(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public MatrixHN(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixHN(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public MatrixHN(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixHN(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public MatrixHN(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);
	}

	public MatrixHN(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	public MatrixHN(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);
	}

	public MatrixHN(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);
	}

	@SafeVarargs
	public MatrixHN(Order order, Vector<?, V>... values) {
		super(order, values);
	}

	@SafeVarargs
	public MatrixHN(Vector<?, V>... values) {
		super(values);
	}

	@SafeVarargs
	public MatrixHN(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixHN(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);
	}

	@Override
	public MatrixN<V> getTransformationMatrix() {
		return new MatrixN<V>(true, getOrder(), getTransformationData2());
	}

	@Override
	public MatrixHN<V> copy() {
		return new MatrixHN<>(this);
	}

	@Override
	public final Vector<?, V> getMajorVector(int index) {
		List<V> majorElements = getData2().get(index);

		if (getOrder() == Order.ColumnMajor) {
			majorElements = majorElements.subList(0, getProjectedDimensions());

			Type newType;
			if (index == getProjectedDimensions()) {
				newType = Type.Absolute;
			} else {
				newType = Type.Relative;
			}

			return new VectorHN<V>(newType, true, majorElements);
		} else {
			return new VectorN<V>(true, getData2().get(index));
		}
	}

	@Override
	public final Vector<?, V> getMinorVector(int index) {
		List<V> minorElements = new ArrayList<V>();
		for (List<V> elements : getData2()) {
			minorElements.add(elements.get(index));
		}

		if (getOrder() == Order.RowMajor) {
			minorElements = minorElements.subList(0, getProjectedDimensions());

			Type newType;
			if (index == getProjectedDimensions()) {
				newType = Type.Absolute;
			} else {
				newType = Type.Relative;
			}

			return new VectorHN<V>(newType, true, minorElements);
		} else {
			return new VectorN<V>(true, getData2().get(index));
		}
	}
}
