package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class VectorHN<V extends Value<V>> extends VectorH<VectorHN<V>, V> {
	public VectorHN(Type type, int size, Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(type, size, order, orientation, valueFactory);
	}

	public VectorHN(Type type, int size, Factory<V> valueFactory) {
		super(type, size, valueFactory);
	}

	public VectorHN(Type type, Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(type, orientation, valueFactory, other);
	}

	public VectorHN(Type type, Factory<V> valueFactory, Matrix<?, ?> other) {
		super(type, valueFactory, other);
	}

	public VectorHN(Type type, Orientation orientation, Matrix<?, V> other) {
		super(type, orientation, other);
	}

	public VectorHN(Type type, Matrix<?, V> other) {
		super(type, other);
	}

	public VectorHN(Type type, Factory<V> valueFactory, Vector<?, ?> other) {
		super(type, valueFactory, other);
	}

	public VectorHN(Type type, Vector<?, V> other) {
		super(type, other);
	}

	public VectorHN(Factory<V> valueFactory, VectorH<?, ?> other) {
		super(valueFactory, other);
	}

	public VectorHN(VectorH<?, V> other) {
		super(other);
	}

	public <I> VectorHN(Type type, Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(type, order, orientation, values, creationOperation);
	}

	public <I> VectorHN(Type type, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(type, values, creationOperation);
	}

	protected <I> VectorHN(Type type, Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(type, order, orientation, values, creationOperation, null);
	}

	protected <I> VectorHN(Type type, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(type, values, creationOperation, null);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(type, valueFactory, values);
	}

	protected VectorHN(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(type, order, orientation, valueFactory, values, null);
	}

	protected VectorHN(Type type, Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(type, valueFactory, values, null);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Value<?>... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, Value<?>... values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(type, order, orientation, values);
	}

	public VectorHN(Type type, List<? extends Value<V>> values) {
		super(type, values);
	}

	protected VectorHN(Type type, Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(type, order, orientation, values, (Void) null);
	}

	protected VectorHN(Type type,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(type, values, (Void) null);
	}

	@SafeVarargs
	public VectorHN(Type type, Order order, Orientation orientation,
			Value<V>... values) {
		super(type, order, orientation, values);
	}

	@SafeVarargs
	public VectorHN(Type type, Value<V>... values) {
		super(type, values);
	}

	public VectorHN(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(type, copyByReference, order, orientation, values);
	}

	public VectorHN(Type type, final boolean copyByReference,
			List<? extends V> values) {
		super(type, copyByReference, values);
	}

	protected VectorHN(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(type, copyByReference, order, orientation, values, null);
	}

	protected VectorHN(Type type, boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(type, copyByReference, values, null);
	}

	@SafeVarargs
	public VectorHN(Type type, boolean copyByReference, Order order,
			Orientation orientation, V... values) {
		super(type, copyByReference, order, orientation, values);
	}

	@SafeVarargs
	public VectorHN(Type type, boolean copyByReference, V... values) {
		super(type, copyByReference, values);
	}

	@SafeVarargs
	public VectorHN(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Number... values) {
		super(type, order, orientation, valueFactory, values);

	}

	public VectorHN(Type type, Factory<V> valueFactory, Number... values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, int[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, int[] values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, long[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, long[] values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, float[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, float[] values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, double[] values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, double[] values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, int[]... values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, long[]... values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, float[]... values) {
		super(type, valueFactory, values);
	}

	public VectorHN(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(type, order, orientation, valueFactory, values);
	}

	public VectorHN(Type type, Factory<V> valueFactory, double[]... values) {
		super(type, valueFactory, values);
	}

	@Override
	public VectorN<V> getMutableVector() {
		VectorN<V> mutableVector = new VectorN<>(true, getData());

		mutableVector.resizeImplementation(getProjectedDimensions());

		return mutableVector;
	}

	@Override
	public VectorHN<V> copy() {
		return new VectorHN<>(this);
	}
}
