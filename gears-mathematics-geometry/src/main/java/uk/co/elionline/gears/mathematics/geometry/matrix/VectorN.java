package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class VectorN<V extends Value<V>> extends
		AbstractVector<VectorN<V>, V> {
	public VectorN(int size, Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(size, order, orientation, valueFactory);
	}

	public VectorN(int size, Factory<V> valueFactory) {
		super(size, valueFactory);
	}

	public VectorN(Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(orientation, valueFactory, other);
	}

	public VectorN(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public VectorN(Orientation orientation, Matrix<?, V> other) {
		super(orientation, other);
	}

	public VectorN(Matrix<?, V> other) {
		super(other);
	}

	public VectorN(Factory<V> valueFactory, Vector<?, ?> other) {
		super(valueFactory, other);
	}

	public VectorN(Vector<?, V> other) {
		super(other);
	}

	public <I> VectorN(Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, orientation, values, creationOperation);
	}

	public <I> VectorN(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	protected <I> VectorN(Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, orientation, values, creationOperation, null);
	}

	protected <I> VectorN(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation, null);
	}

	public VectorN(Order order, Orientation orientation, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(valueFactory, values);
	}

	protected VectorN(Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, orientation, valueFactory, values, null);
	}

	protected VectorN(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values, null);
	}

	public VectorN(Order order, Orientation orientation, Factory<V> valueFactory,
			Value<?>... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, Value<?>... values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(order, orientation, values);
	}

	public VectorN(List<? extends Value<V>> values) {
		super(values);
	}

	protected VectorN(Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(order, orientation, values, (Void) null);
	}

	protected VectorN(List<? extends List<? extends Value<V>>> values,
			Void flag) {
		super(values, (Void) null);
	}

	@SafeVarargs
	public VectorN(Order order, Orientation orientation,
			Value<V>... values) {
		super(order, orientation, values);
	}

	@SafeVarargs
	public VectorN(Value<V>... values) {
		super(values);
	}

	public VectorN(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(copyByReference, order, orientation, values);
	}

	public VectorN(final boolean copyByReference, List<? extends V> values) {
		super(copyByReference, values);
	}

	protected VectorN(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(copyByReference, order, orientation, values, null);
	}

	protected VectorN(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, values, null);
	}

	@SafeVarargs
	public VectorN(boolean copyByReference, Order order, Orientation orientation,
			V... values) {
		super(copyByReference, order, orientation, values);
	}

	@SafeVarargs
	public VectorN(boolean copyByReference, V... values) {
		super(copyByReference, values);
	}

	@SafeVarargs
	public VectorN(Order order, Orientation orientation, Factory<V> valueFactory,
			Number... values) {
		super(order, orientation, valueFactory, values);

	}

	public VectorN(Factory<V> valueFactory, Number... values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation, Factory<V> valueFactory,
			int[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, int[] values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation, Factory<V> valueFactory,
			long[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, long[] values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation, Factory<V> valueFactory,
			float[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, float[] values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation, Factory<V> valueFactory,
			double[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, double[] values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public VectorN(Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorN(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	@Override
	public VectorN<V> copy() {
		return new VectorN<V>(this);
	}
}
