package uk.co.elionline.emerge.mathematics.geometry.matrices;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;

public class VectorR<V extends Value<V>> extends
		VectorImplementation<VectorR<V>, V> {
	public VectorR(int size, Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(size, order, orientation, valueFactory);
	}

	public VectorR(int size, Factory<V> valueFactory) {
		super(size, valueFactory);
	}

	public VectorR(Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(orientation, valueFactory, other);
	}

	public VectorR(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);
	}

	public VectorR(Orientation orientation, Matrix<?, V> other) {
		super(orientation, other);
	}

	public VectorR(Matrix<?, V> other) {
		super(other);
	}

	public VectorR(Factory<V> valueFactory, Vector<?, ?> other) {
		super(valueFactory, other);
	}

	public VectorR(Vector<?, V> other) {
		super(other);
	}

	public <I> VectorR(Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, orientation, values, creationOperation);
	}

	public <I> VectorR(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);
	}

	protected <I> VectorR(Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, orientation, values, creationOperation, null);
	}

	protected <I> VectorR(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation, null);
	}

	public VectorR(Order order, Orientation orientation, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(valueFactory, values);
	}

	protected VectorR(Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, orientation, valueFactory, values, null);
	}

	protected VectorR(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values, null);
	}

	public VectorR(Order order, Orientation orientation, Factory<V> valueFactory,
			Value<?>... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, Value<?>... values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(order, orientation, values);
	}

	public VectorR(List<? extends Value<V>> values) {
		super(values);
	}

	protected VectorR(Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(order, orientation, values, (Void) null);
	}

	protected VectorR(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values, (Void) null);
	}

	@SafeVarargs
	public VectorR(Order order, Orientation orientation, Value<V>... values) {
		super(order, orientation, values);
	}

	@SafeVarargs
	public VectorR(Value<V>... values) {
		super(values);
	}

	public VectorR(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(copyByReference, order, orientation, values);
	}

	public VectorR(final boolean copyByReference, List<? extends V> values) {
		super(copyByReference, values);
	}

	protected VectorR(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(copyByReference, order, orientation, values, null);
	}

	protected VectorR(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, values, null);
	}

	@SafeVarargs
	public VectorR(boolean copyByReference, Order order, Orientation orientation,
			V... values) {
		super(copyByReference, order, orientation, values);
	}

	@SafeVarargs
	public VectorR(boolean copyByReference, V... values) {
		super(copyByReference, values);
	}

	@SafeVarargs
	public VectorR(Order order, Orientation orientation, Factory<V> valueFactory,
			Number... values) {
		super(order, orientation, valueFactory, values);

	}

	public VectorR(Factory<V> valueFactory, Number... values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation, Factory<V> valueFactory,
			int[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, int[] values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation, Factory<V> valueFactory,
			long[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, long[] values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation, Factory<V> valueFactory,
			float[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, float[] values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation, Factory<V> valueFactory,
			double[] values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, double[] values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);
	}

	public VectorR(Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(order, orientation, valueFactory, values);
	}

	public VectorR(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);
	}

	public VectorR<V> resize(int dimensions) {
		return super.resizeImplementation(dimensions);
	}

	@Override
	public VectorR<V> copy() {
		return new VectorR<V>(this);
	}
}
