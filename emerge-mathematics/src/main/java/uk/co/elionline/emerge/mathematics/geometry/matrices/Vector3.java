package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class Vector3<V extends Value<V>> extends
		VectorImplementation<Vector3<V>, V> {
	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory) {
		super(3, order, orientation, valueFactory);
	}

	public Vector3(Factory<V> valueFactory) {
		super(3, valueFactory);
	}

	public Vector3(Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(orientation, valueFactory, other);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);

		assertDimensions(this, 3);
	}

	public Vector3(Orientation orientation, Matrix<?, V> other) {
		super(orientation, other);

		assertDimensions(this, 3);
	}

	public Vector3(Matrix<?, V> other) {
		super(other);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, Vector<?, ?> other) {
		super(valueFactory, other);

		assertDimensions(this, 3);
	}

	public Vector3(Vector<?, V> other) {
		super(other);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, Vector3<?> other) {
		super(valueFactory, other);
	}

	public Vector3(Vector3<V> other) {
		super(other);
	}

	public <I> Vector3(Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, orientation, values, creationOperation);

		assertDimensions(this, 3);
	}

	public <I> Vector3(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);

		assertDimensions(this, 3);
	}

	protected <I> Vector3(Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, orientation, values, creationOperation, null);

		assertDimensions(this, 3);
	}

	protected <I> Vector3(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation, null);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	protected Vector3(Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, orientation, valueFactory, values, null);

		assertDimensions(this, 3);
	}

	protected Vector3(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values, null);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory,
			Value<?>... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, Value<?>... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(order, orientation, values);

		assertDimensions(this, 3);
	}

	public Vector3(List<? extends Value<V>> values) {
		super(values);

		assertDimensions(this, 3);
	}

	protected Vector3(Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(order, orientation, values, (Void) null);

		assertDimensions(this, 3);
	}

	protected Vector3(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values, (Void) null);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Vector3(Order order, Orientation orientation, Value<V>... values) {
		super(order, orientation, values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Vector3(Value<V>... values) {
		super(values);

		assertDimensions(this, 3);
	}

	public Vector3(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(copyByReference, order, orientation, values);

		assertDimensions(this, 3);
	}

	public Vector3(final boolean copyByReference, List<? extends V> values) {
		super(copyByReference, values);

		assertDimensions(this, 3);
	}

	protected Vector3(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(copyByReference, order, orientation, values, null);

		assertDimensions(this, 3);
	}

	protected Vector3(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, values, null);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Vector3(boolean copyByReference, Order order, Orientation orientation,
			V... values) {
		super(copyByReference, order, orientation, values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Vector3(boolean copyByReference, V... values) {
		super(copyByReference, values);

		assertDimensions(this, 3);
	}

	@SafeVarargs
	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory,
			Number... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, Number... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory,
			int[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, int[] values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory,
			long[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, long[] values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory,
			float[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, float[] values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation, Factory<V> valueFactory,
			double[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, double[] values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 3);
	}

	public Vector3(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 3);
	}

	@Override
	public Vector3<V> copy() {
		return new Vector3<V>(this);
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
}
