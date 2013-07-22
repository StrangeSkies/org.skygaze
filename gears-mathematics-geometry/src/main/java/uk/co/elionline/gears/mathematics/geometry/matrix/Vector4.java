package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class Vector4<V extends Value<V>> extends
		AbstractVector<Vector4<V>, V> {
	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory) {
		super(4, order, orientation, valueFactory);
	}

	public Vector4(Factory<V> valueFactory) {
		super(4, valueFactory);
	}

	public Vector4(Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(orientation, valueFactory, other);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);

		assertDimensions(this, 4);
	}

	public Vector4(Orientation orientation, Matrix<?, V> other) {
		super(orientation, other);

		assertDimensions(this, 4);
	}

	public Vector4(Matrix<?, V> other) {
		super(other);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, Vector<?, ?> other) {
		super(valueFactory, other);

		assertDimensions(this, 4);
	}

	public Vector4(Vector<?, V> other) {
		super(other);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, Vector4<?> other) {
		super(valueFactory, other);
	}

	public Vector4(Vector4<V> other) {
		super(other);
	}

	public <I> Vector4(Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, orientation, values, creationOperation);

		assertDimensions(this, 4);
	}

	public <I> Vector4(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);

		assertDimensions(this, 4);
	}

	protected <I> Vector4(Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, orientation, values, creationOperation, null);

		assertDimensions(this, 4);
	}

	protected <I> Vector4(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation, null);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	protected Vector4(Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, orientation, valueFactory, values, null);

		assertDimensions(this, 4);
	}

	protected Vector4(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values, null);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory,
			Value<?>... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, Value<?>... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(order, orientation, values);

		assertDimensions(this, 4);
	}

	public Vector4(List<? extends Value<V>> values) {
		super(values);

		assertDimensions(this, 4);
	}

	protected Vector4(Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(order, orientation, values, (Void) null);

		assertDimensions(this, 4);
	}

	protected Vector4(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values, (Void) null);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Vector4(Order order, Orientation orientation, Value<V>... values) {
		super(order, orientation, values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Vector4(Value<V>... values) {
		super(values);

		assertDimensions(this, 4);
	}

	public Vector4(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(copyByReference, order, orientation, values);

		assertDimensions(this, 4);
	}

	public Vector4(final boolean copyByReference, List<? extends V> values) {
		super(copyByReference, values);

		assertDimensions(this, 4);
	}

	protected Vector4(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(copyByReference, order, orientation, values, null);

		assertDimensions(this, 4);
	}

	protected Vector4(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, values, null);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Vector4(boolean copyByReference, Order order, Orientation orientation,
			V... values) {
		super(copyByReference, order, orientation, values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Vector4(boolean copyByReference, V... values) {
		super(copyByReference, values);

		assertDimensions(this, 4);
	}

	@SafeVarargs
	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory,
			Number... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, Number... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory,
			int[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, int[] values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory,
			long[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, long[] values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory,
			float[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, float[] values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation, Factory<V> valueFactory,
			double[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, double[] values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 4);
	}

	public Vector4(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 4);
	}

	@Override
	public Vector4<V> copy() {
		return new Vector4<V>(this);
	}

	public V getX() {
		return getElement(0);
	}

	public V getY() {
		return getElement(1);
	}

	public V getZ() {
		return getElement(2);
	}

	public V getW() {
		return getElement(3);
	}
}
