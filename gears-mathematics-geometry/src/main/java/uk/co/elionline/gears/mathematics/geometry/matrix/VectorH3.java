package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class VectorH3<V extends Value<V>> extends VectorH<VectorH3<V>, V> {
	public VectorH3(Type type, int size, Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(type, 3, order, orientation, valueFactory);
	}

	public VectorH3(Type type, int size, Factory<V> valueFactory) {
		super(type, 3, valueFactory);
	}

	public VectorH3(Type type, Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(type, orientation, valueFactory, other);
	}

	public VectorH3(Type type, Factory<V> valueFactory, Matrix<?, ?> other) {
		super(type, valueFactory, other);
	}

	public VectorH3(Type type, Orientation orientation, Matrix<?, V> other) {
		super(type, orientation, other);
	}

	public VectorH3(Type type, Matrix<?, V> other) {
		super(type, other);
	}

	public VectorH3(Type type, Factory<V> valueFactory, Vector<?, ?> other) {
		super(type, valueFactory, other);
	}

	public VectorH3(Type type, Vector<?, V> other) {
		super(type, other);
	}

	public VectorH3(Factory<V> valueFactory, VectorH<?, ?> other) {
		super(valueFactory, other);
	}

	public VectorH3(VectorH<?, V> other) {
		super(other);
	}

	public <I> VectorH3(Type type, Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(type, order, orientation, values, creationOperation);
	}

	public <I> VectorH3(Type type, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(type, values, creationOperation);
	}

	protected <I> VectorH3(Type type, Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(type, order, orientation, values, creationOperation, null);
	}

	protected <I> VectorH3(Type type, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(type, values, creationOperation, null);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(type, valueFactory, values);
	}

	protected VectorH3(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(type, order, orientation, valueFactory, values, null);
	}

	protected VectorH3(Type type, Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(type, valueFactory, values, null);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Value<?>... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, Value<?>... values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(type, order, orientation, values);
	}

	public VectorH3(Type type, List<? extends Value<V>> values) {
		super(type, values);
	}

	protected VectorH3(Type type, Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(type, order, orientation, values, (Void) null);
	}

	protected VectorH3(Type type,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(type, values, (Void) null);
	}

	@SafeVarargs
	public VectorH3(Type type, Order order, Orientation orientation,
			Value<V>... values) {
		super(type, order, orientation, values);
	}

	@SafeVarargs
	public VectorH3(Type type, Value<V>... values) {
		super(type, values);
	}

	public VectorH3(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(type, copyByReference, order, orientation, values);
	}

	public VectorH3(Type type, final boolean copyByReference,
			List<? extends V> values) {
		super(type, copyByReference, values);
	}

	protected VectorH3(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(type, copyByReference, order, orientation, values, null);
	}

	protected VectorH3(Type type, boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(type, copyByReference, values, null);
	}

	@SafeVarargs
	public VectorH3(Type type, boolean copyByReference, Order order,
			Orientation orientation, V... values) {
		super(type, copyByReference, order, orientation, values);
	}

	@SafeVarargs
	public VectorH3(Type type, boolean copyByReference, V... values) {
		super(type, copyByReference, values);
	}

	@SafeVarargs
	public VectorH3(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Number... values) {
		super(type, order, orientation, valueFactory, values);

	}

	public VectorH3(Type type, Factory<V> valueFactory, Number... values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, int[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, int[] values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, long[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, long[] values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, float[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, float[] values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, double[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, double[] values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, int[]... values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, long[]... values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, float[]... values) {
		super(type, valueFactory, values);
	}

	public VectorH3(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH3(Type type, Factory<V> valueFactory, double[]... values) {
		super(type, valueFactory, values);
	}

	@Override
	protected void finalise() {
		super.finalise();

		assertDimensions(this, 4);
	}

	@Override
	public Vector3<V> getMutableVector() {
		Vector3<V> mutableVector = new Vector3<>(true, getData().subList(0, 3));

		mutableVector.resizeImplementation(getProjectedDimensions());

		return mutableVector;
	}

	@Override
	public VectorH3<V> copy() {
		return new VectorH3<>(this);
	}

	public V getX() {
		return getElement(0);
	}

	public V getY() {
		return getElement(1);
	}

	public V getW() {
		return getElement(2);
	}
}
