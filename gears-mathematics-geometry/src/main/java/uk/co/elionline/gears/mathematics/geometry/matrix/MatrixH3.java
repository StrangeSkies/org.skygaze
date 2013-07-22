package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.geometry.matrix.VectorH.Type;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class MatrixH3<V extends Value<V>> extends MatrixH<MatrixH3<V>, V> {
	public MatrixH3(Order order, Factory<V> valueFactory) {
		super(3, order, valueFactory);
	}

	public MatrixH3(Factory<V> valueFactory) {
		super(3, valueFactory);
	}

	public MatrixH3(Matrix<?, V> other) {
		super(assertDimensions(other, 4, 3));
	}

	public MatrixH3(MatrixH<?, V> other) {
		super(assertDimensions(other, 4));
	}

	public MatrixH3(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, assertDimensions(other, 4, 3));
	}

	public MatrixH3(Factory<V> valueFactory, MatrixH<?, ?> other) {
		super(valueFactory, assertDimensions(other, 4));
	}

	public <I> MatrixH3(Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(3, order, values, creationOperation);
	}

	public <I> MatrixH3(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(3, values, creationOperation);
	}

	public <I> MatrixH3(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, values, creationOperation);

		assertDimensions(getThis(), 4);
	}

	public <I> MatrixH3(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(3, order, valueFactory, values);
	}

	public MatrixH3(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(3, valueFactory, values);
	}

	public MatrixH3(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, Value<?>... values) {
		super(3, order, valueFactory, values);
	}

	public MatrixH3(Factory<V> valueFactory, Value<?>... values) {
		super(3, valueFactory, values);
	}

	public MatrixH3(Order order, List<? extends Value<V>> values) {
		super(3, order, values);
	}

	public MatrixH3(List<? extends Value<V>> values) {
		super(3, values);
	}

	public MatrixH3(Order order, List<? extends List<? extends Value<V>>> values,
			Void flag) {
		super(order, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values);

		assertDimensions(getThis(), 4);
	}

	@SafeVarargs
	public MatrixH3(Order order, Value<V>... values) {
		super(3, order, values);
	}

	@SafeVarargs
	public MatrixH3(Value<V>... values) {
		super(3, values);
	}

	public MatrixH3(boolean copyByReference, Order order, List<? extends V> values) {
		super(3, copyByReference, order, values);
	}

	public MatrixH3(boolean copyByReference, List<? extends V> values) {
		super(3, copyByReference, values);
	}

	public MatrixH3(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, order, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(values);

		assertDimensions(getThis(), 4);
	}

	@SafeVarargs
	public MatrixH3(boolean copyByReference, Order order, V... values) {
		super(3, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixH3(boolean copyByReference, V... values) {
		super(3, copyByReference, values);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, Number... values) {
		super(3, order, valueFactory, values);
	}

	public MatrixH3(Factory<V> valueFactory, Number... values) {
		super(3, valueFactory, values);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, int[] values) {
		super(3, order, valueFactory, values);
	}

	public MatrixH3(Factory<V> valueFactory, int[] values) {
		super(3, valueFactory, values);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, long[] values) {
		super(3, order, valueFactory, values);
	}

	public MatrixH3(Factory<V> valueFactory, long[] values) {
		super(3, valueFactory, values);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, float[] values) {
		super(3, order, valueFactory, values);
	}

	public MatrixH3(Factory<V> valueFactory, float[] values) {
		super(3, valueFactory, values);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, double[] values) {
		super(3, order, valueFactory, values);
	}

	public MatrixH3(Factory<V> valueFactory, double[] values) {
		super(3, valueFactory, values);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	public MatrixH3(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 4);
	}

	@SafeVarargs
	public MatrixH3(Order order, Vector<?, V>... values) {
		super(order, values);

		assertDimensions(getThis(), 4);
	}

	@SafeVarargs
	public MatrixH3(Vector<?, V>... values) {
		super(values);

		assertDimensions(getThis(), 4);
	}

	@SafeVarargs
	public MatrixH3(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);

		assertDimensions(getThis(), 4);
	}

	@SafeVarargs
	public MatrixH3(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);

		assertDimensions(getThis(), 4);
	}

	@Override
	public Matrix3<V> getTransformationMatrix() {
		return new Matrix3<V>(true, getOrder(), getTransformationData2(), null);
	}

	@Override
	public MatrixH3<V> copy() {
		return new MatrixH3<V>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector4<V>> getRowVectors() {
		return (List<Vector4<V>>) super.getRowVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<VectorH3<V>> getColumnVectors() {
		return (List<VectorH3<V>>) super.getColumnVectors();
	}

	@Override
	public final Vector4<V> getRowVector(int row) {
		return new Vector4<V>(true, getRowVectorData(row));
	}

	@Override
	public final VectorH3<V> getColumnVector(int column) {
		return new VectorH3<V>(column == getDimensions() - 1 ? Type.Absolute
				: Type.Relative, true, getColumnVectorData(getProjectedDimensions())
				.subList(0, getProjectedDimensions()));
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

			return new VectorH3<V>(newType, true, majorElements);
		} else {
			return new Vector4<V>(true, getData2().get(index));
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

			return new VectorH3<V>(newType, true, minorElements);
		} else {
			return new Vector4<V>(true, getData2().get(index));
		}
	}
}
