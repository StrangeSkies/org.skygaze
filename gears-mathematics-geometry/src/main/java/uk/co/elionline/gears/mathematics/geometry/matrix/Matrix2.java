package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.geometry.NonCommutativelyRotatable2;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class Matrix2<V extends Value<V>> extends MatrixS<Matrix2<V>, V>
		implements NonCommutativelyRotatable2<Matrix2<V>> {
	public Matrix2(Order order, Factory<V> valueFactory) {
		super(2, order, valueFactory);
	}

	public Matrix2(Factory<V> valueFactory) {
		super(2, valueFactory);
	}

	public Matrix2(Matrix<?, V> other) {
		super(assertDimensions(other, 2));
	}

	public Matrix2(Matrix2<V> other) {
		super(other);
	}

	public Matrix2(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, assertDimensions(other, 2));
	}

	public Matrix2(Factory<V> valueFactory, Matrix2<?> other) {
		super(valueFactory, other);
	}

	public <I> Matrix2(Order order, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(2, order, values, creationOperation);
	}

	public <I> Matrix2(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(2, values, creationOperation);
	}

	public <I> Matrix2(Order order, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, values, creationOperation);

		assertDimensions(this, 2);
	}

	public <I> Matrix2(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation);

		assertDimensions(this, 2);
	}

	public Matrix2(Order order, final Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(2, order, valueFactory, values);
	}

	public Matrix2(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(2, valueFactory, values);
	}

	public Matrix2(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Order order, Factory<V> valueFactory, Value<?>... values) {
		super(2, order, valueFactory, values);
	}

	public Matrix2(Factory<V> valueFactory, Value<?>... values) {
		super(2, valueFactory, values);
	}

	public Matrix2(Order order, List<? extends Value<V>> values) {
		super(2, order, values);
	}

	public Matrix2(List<? extends Value<V>> values) {
		super(2, values);
	}

	public Matrix2(Order order, List<? extends List<? extends Value<V>>> values,
			Void flag) {
		super(order, values);

		assertDimensions(this, 2);
	}

	public Matrix2(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Matrix2(Order order, Value<V>... values) {
		super(2, order, values);
	}

	@SafeVarargs
	public Matrix2(Value<V>... values) {
		super(2, values);
	}

	public Matrix2(boolean copyByReference, Order order, List<? extends V> values) {
		super(2, copyByReference, order, values);
	}

	public Matrix2(boolean copyByReference, List<? extends V> values) {
		super(2, copyByReference, values);
	}

	public Matrix2(boolean copyByReference, Order order,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, order, values);

		assertDimensions(this, 2);
	}

	public Matrix2(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Matrix2(boolean copyByReference, Order order, V... values) {
		super(2, copyByReference, order, values);
	}

	@SafeVarargs
	public Matrix2(boolean copyByReference, V... values) {
		super(2, copyByReference, values);
	}

	public Matrix2(Order order, Factory<V> valueFactory, Number... values) {
		super(2, order, valueFactory, values);
	}

	public Matrix2(Factory<V> valueFactory, Number... values) {
		super(2, valueFactory, values);
	}

	public Matrix2(Order order, Factory<V> valueFactory, int[] values) {
		super(2, order, valueFactory, values);
	}

	public Matrix2(Factory<V> valueFactory, int[] values) {
		super(2, valueFactory, values);
	}

	public Matrix2(Order order, Factory<V> valueFactory, long[] values) {
		super(2, order, valueFactory, values);
	}

	public Matrix2(Factory<V> valueFactory, long[] values) {
		super(2, valueFactory, values);
	}

	public Matrix2(Order order, Factory<V> valueFactory, float[] values) {
		super(2, order, valueFactory, values);
	}

	public Matrix2(Factory<V> valueFactory, float[] values) {
		super(2, valueFactory, values);
	}

	public Matrix2(Order order, Factory<V> valueFactory, double[] values) {
		super(2, order, valueFactory, values);
	}

	public Matrix2(Factory<V> valueFactory, double[] values) {
		super(2, valueFactory, values);
	}

	public Matrix2(Order order, Factory<V> valueFactory, int[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Order order, Factory<V> valueFactory, long[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Order order, Factory<V> valueFactory, float[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Order order, Factory<V> valueFactory, double[]... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Order order, Factory<V> valueFactory, Vector<?, ?>... values) {
		super(order, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Matrix2(Factory<V> valueFactory, Vector<?, ?>... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Matrix2(Order order, Vector<?, V>... values) {
		super(order, values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Matrix2(Vector<?, V>... values) {
		super(values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Matrix2(boolean copyByReference, Order order,
			Vector<?, V>... values) {
		super(copyByReference, order, values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Matrix2(boolean copyByReference, Vector<?, V>... values) {
		super(copyByReference, values);

		assertDimensions(this, 2);
	}

	@Override
	public Matrix2<V> copy() {
		return new Matrix2<V>(this);
	}

	@Override
	public Matrix2<V> getRotated(Value<?> angle) {
		return copy().rotate(angle);
	}

	@Override
	public Matrix2<V> rotate(Value<?> angle) {
		// TODO implement rotation
		return null;
	}

	@Override
	public Matrix2<V> getPreRotated(Value<?> value) {
		return copy().preRotate(value);
	}

	@Override
	public Matrix2<V> preRotate(Value<?> value) {
		// TODO implement pre-rotation
		return null;
	}

	@Override
	public Matrix2<V> getPreRotated(Value<?> angle, Vector2<?> centre) {
		return copy().preRotate(angle, centre);
	}

	@Override
	public Matrix2<V> getRotated(Value<?> angle, Vector2<?> centre) {
		return copy().rotate(angle, centre);
	}

	@Override
	public Matrix2<V> rotate(Value<?> angle, Vector2<?> centre) {
		// TODO implement rotation about point
		return null;
	}

	@Override
	public Matrix2<V> preRotate(Value<?> angle, Vector2<?> centre) {
		// TODO implement pre-rotation about point
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector2<V>> getRowVectors() {
		return (List<Vector2<V>>) super.getRowVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector2<V>> getColumnVectors() {
		return (List<Vector2<V>>) super.getColumnVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Vector2<V> getRowVector(int row) {
		return (Vector2<V>) super.getRowVector(row);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Vector2<V> getColumnVector(int column) {
		return (Vector2<V>) super.getColumnVector(column);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector2<V>> getMajorVectors() {
		return (List<Vector2<V>>) super.getMajorVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector2<V>> getMinorVectors() {
		return (List<Vector2<V>>) super.getMinorVectors();
	}

	@Override
	public final Vector2<V> getMajorVector(int index) {
		return new Vector2<V>(true, getData2().get(index));
	}

	@Override
	public final Vector2<V> getMinorVector(int index) {
		List<V> minorElements = new ArrayList<V>();
		for (List<V> elements : getData2()) {
			minorElements.add(elements.get(index));
		}
		return new Vector2<V>(true, minorElements);
	}
}
