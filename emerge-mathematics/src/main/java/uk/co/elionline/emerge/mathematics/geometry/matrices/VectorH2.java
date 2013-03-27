package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.geometry.Rotatable2;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class VectorH2<V extends Value<V>> extends VectorH<VectorH2<V>, V>
		implements Rotatable2<VectorH2<V>> {
	public VectorH2(Type type, int size, Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(type, 2, order, orientation, valueFactory);
	}

	public VectorH2(Type type, int size, Factory<V> valueFactory) {
		super(type, 2, valueFactory);
	}

	public VectorH2(Type type, Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(type, orientation, valueFactory, other);
	}

	public VectorH2(Type type, Factory<V> valueFactory, Matrix<?, ?> other) {
		super(type, valueFactory, other);
	}

	public VectorH2(Type type, Orientation orientation, Matrix<?, V> other) {
		super(type, orientation, other);
	}

	public VectorH2(Type type, Matrix<?, V> other) {
		super(type, other);
	}

	public VectorH2(Type type, Factory<V> valueFactory, Vector<?, ?> other) {
		super(type, valueFactory, other);
	}

	public VectorH2(Type type, Vector<?, V> other) {
		super(type, other);
	}

	public VectorH2(Factory<V> valueFactory, VectorH<?, ?> other) {
		super(valueFactory, other);
	}

	public VectorH2(VectorH<?, V> other) {
		super(other);
	}

	public <I> VectorH2(Type type, Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(type, order, orientation, values, creationOperation);
	}

	public <I> VectorH2(Type type, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(type, values, creationOperation);
	}

	protected <I> VectorH2(Type type, Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(type, order, orientation, values, creationOperation, null);
	}

	protected <I> VectorH2(Type type, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(type, values, creationOperation, null);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(type, valueFactory, values);
	}

	protected VectorH2(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(type, order, orientation, valueFactory, values, null);
	}

	protected VectorH2(Type type, Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(type, valueFactory, values, null);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Value<?>... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, Value<?>... values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(type, order, orientation, values);
	}

	public VectorH2(Type type, List<? extends Value<V>> values) {
		super(type, values);
	}

	protected VectorH2(Type type, Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(type, order, orientation, values, (Void) null);
	}

	protected VectorH2(Type type,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(type, values, (Void) null);
	}

	@SafeVarargs
	public VectorH2(Type type, Order order, Orientation orientation,
			Value<V>... values) {
		super(type, order, orientation, values);
	}

	@SafeVarargs
	public VectorH2(Type type, Value<V>... values) {
		super(type, values);
	}

	public VectorH2(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(type, copyByReference, order, orientation, values);
	}

	public VectorH2(Type type, final boolean copyByReference,
			List<? extends V> values) {
		super(type, copyByReference, values);
	}

	protected VectorH2(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(type, copyByReference, order, orientation, values, null);
	}

	protected VectorH2(Type type, boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(type, copyByReference, values, null);
	}

	@SafeVarargs
	public VectorH2(Type type, boolean copyByReference, Order order,
			Orientation orientation, V... values) {
		super(type, copyByReference, order, orientation, values);
	}

	@SafeVarargs
	public VectorH2(Type type, boolean copyByReference, V... values) {
		super(type, copyByReference, values);
	}

	@SafeVarargs
	public VectorH2(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Number... values) {
		super(type, order, orientation, valueFactory, values);

	}

	public VectorH2(Type type, Factory<V> valueFactory, Number... values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, int[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, int[] values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, long[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, long[] values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, float[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, float[] values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, double[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, double[] values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, int[]... values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, long[]... values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, float[]... values) {
		super(type, valueFactory, values);
	}

	public VectorH2(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorH2(Type type, Factory<V> valueFactory, double[]... values) {
		super(type, valueFactory, values);
	}

	@Override
	protected void finalise() {
		super.finalise();

		assertDimensions(this, 3);
	}

	@Override
	public Vector2<V> getMutableVector() {
		Vector2<V> mutableVector = new Vector2<>(true, getData().subList(0, 2));

		mutableVector.resizeImplementation(getProjectedDimensions());

		return mutableVector;
	}

	@Override
	public VectorH2<V> copy() {
		return new VectorH2<>(this);
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

	@Override
	public VectorH2<V> getRotated(Value<?> angle) {
		return copy().rotate(angle);
	}

	@Override
	public VectorH2<V> rotate(Value<?> angle) {
		getMutableVector().rotate(angle);

		return getThis();
	}

	@Override
	public VectorH2<V> getRotated(Value<?> angle, Vector2<?> centre) {
		return copy().rotate(angle, centre);
	}

	@Override
	public VectorH2<V> rotate(Value<?> angle, Vector2<?> centre) {
		getMutableVector().rotate(angle, centre);

		return getThis();
	}
}
