package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.geometry.NonCommutativelyRotatable2;
import uk.co.elionline.emerge.mathematics.geometry.matrices.VectorH.Type;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class MatrixH2<V extends Value<V>> extends MatrixH<MatrixH2<V>, V>
		implements NonCommutativelyRotatable2<MatrixH2<V>> {
	public MatrixH2(Order order, Factory<V> valueFactory) {
		super(2, order, valueFactory);
	}

	public MatrixH2(Factory<V> valueFactory) {
		super(2, valueFactory);
	}

	public MatrixH2(Matrix<?, V> other) {
		super(assertDimensions(other, 3, 2));
	}

	public MatrixH2(MatrixH<?, V> other) {
		super(assertDimensions(other, 3));
	}

	public MatrixH2(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, assertDimensions(other, 3, 2));
	}

	public MatrixH2(Factory<V> valueFactory, MatrixH<?, ?> other) {
		super(valueFactory, assertDimensions(other, 3));
	}

	public <I> MatrixH2(Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(2, order, values, creationOperation);
	}

	public <I> MatrixH2(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(2, values, creationOperation);
	}

	public <I> MatrixH2(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, values, creationOperation);

		assertDimensions(getThis(), 3);
	}

	public <I> MatrixH2(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(2, order, valueFactory, values);
	}

	public MatrixH2(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(2, valueFactory, values);
	}

	public MatrixH2(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, Value<?>... values) {
		super(2, order, valueFactory, values);
	}

	public MatrixH2(Factory<V> valueFactory, Value<?>... values) {
		super(2, valueFactory, values);
	}

	public MatrixH2(Order order, List<? extends Value<V>> values) {
		super(2, order, values);
	}

	public MatrixH2(List<? extends Value<V>> values) {
		super(2, values);
	}

	public MatrixH2(Order order, List<? extends List<? extends Value<V>>> values,
			Void flag) {
		super(order, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values);

		assertDimensions(getThis(), 3);
	}

	@SafeVarargs
	public MatrixH2(Order order, Value<V>... values) {
		super(2, order, values);
	}

	@SafeVarargs
	public MatrixH2(Value<V>... values) {
		super(2, values);
	}

	public MatrixH2(boolean copyByReference, Order order, List<? extends V> values) {
		super(2, copyByReference, order, values);
	}

	public MatrixH2(boolean copyByReference, List<? extends V> values) {
		super(2, copyByReference, values);
	}

	public MatrixH2(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, order, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(values);

		assertDimensions(getThis(), 3);
	}

	@SafeVarargs
	public MatrixH2(boolean copyByReference, Order order, V... values) {
		super(2, copyByReference, order, values);
	}

	@SafeVarargs
	public MatrixH2(boolean copyByReference, V... values) {
		super(2, copyByReference, values);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, Number... values) {
		super(2, order, valueFactory, values);
	}

	public MatrixH2(Factory<V> valueFactory, Number... values) {
		super(2, valueFactory, values);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, int[] values) {
		super(2, order, valueFactory, values);
	}

	public MatrixH2(Factory<V> valueFactory, int[] values) {
		super(2, valueFactory, values);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, long[] values) {
		super(2, order, valueFactory, values);
	}

	public MatrixH2(Factory<V> valueFactory, long[] values) {
		super(2, valueFactory, values);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, float[] values) {
		super(2, order, valueFactory, values);
	}

	public MatrixH2(Factory<V> valueFactory, float[] values) {
		super(2, valueFactory, values);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, double[] values) {
		super(2, order, valueFactory, values);
	}

	public MatrixH2(Factory<V> valueFactory, double[] values) {
		super(2, valueFactory, values);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	public MatrixH2(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);

		assertDimensions(getThis(), 3);
	}

	@SafeVarargs
	public MatrixH2(Order order, Vector<?, V>... values) {
		super(order, values);

		assertDimensions(getThis(), 3);
	}

	@SafeVarargs
	public MatrixH2(Vector<?, V>... values) {
		super(values);

		assertDimensions(getThis(), 3);
	}

	@SafeVarargs
	public MatrixH2(boolean copyByReference, Order order, Vector<?, V>... values) {
		super(copyByReference, order, values);

		assertDimensions(getThis(), 3);
	}

	@SafeVarargs
	public MatrixH2(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);

		assertDimensions(getThis(), 3);
	}

	@Override
	public Matrix2<V> getTransformationMatrix() {
		return new Matrix2<V>(true, getOrder(), getTransformationData2(), null);
	}

	@Override
	public MatrixH2<V> copy() {
		return new MatrixH2<V>(this);
	}

	@Override
	public MatrixH2<V> getPreRotated(Value<?> value) {
		return copy().preRotate(value);
	}

	@Override
	public MatrixH2<V> getPreRotated(Value<?> angle, Vector2<?> centre) {
		return copy().preRotate(angle, centre);
	}

	@Override
	public MatrixH2<V> getRotated(Value<?> angle) {
		return copy().rotate(angle);
	}

	@Override
	public MatrixH2<V> rotate(Value<?> angle) {
		getTransformationMatrix().rotate(angle);

		return getThis();
	}

	@Override
	public MatrixH2<V> getRotated(Value<?> angle, Vector2<?> centre) {
		return copy().rotate(angle, centre);
	}

	@Override
	public MatrixH2<V> rotate(Value<?> angle, Vector2<?> centre) {
		getTransformationMatrix().rotate(angle, centre);

		return getThis();
	}

	@Override
	public MatrixH2<V> preRotate(Value<?> value) {
		getTransformationMatrix().preRotate(value);

		return getThis();
	}

	@Override
	public MatrixH2<V> preRotate(Value<?> angle, Vector2<?> centre) {
		getTransformationMatrix().preRotate(angle, centre);

		return getThis();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector3<V>> getRowVectors() {
		return (List<Vector3<V>>) super.getRowVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<VectorH2<V>> getColumnVectors() {
		return (List<VectorH2<V>>) super.getColumnVectors();
	}

	@Override
	public final Vector3<V> getRowVector(int row) {
		return new Vector3<V>(true, getRowVectorData(row));
	}

	@Override
	public final VectorH2<V> getColumnVector(int column) {
		return new VectorH2<V>(column == getDimensions() - 1 ? Type.Absolute
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

			return new VectorH2<V>(newType, true, majorElements);
		} else {
			return new Vector3<V>(true, getData2().get(index));
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

			return new VectorH2<V>(newType, true, minorElements);
		} else {
			return new Vector3<V>(true, getData2().get(index));
		}
	}
}
