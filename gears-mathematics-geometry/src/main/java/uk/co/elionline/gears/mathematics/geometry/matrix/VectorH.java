package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.util.List;

import uk.co.elionline.gears.mathematics.Addable;
import uk.co.elionline.gears.mathematics.NonCommutativelyMultipliable;
import uk.co.elionline.gears.mathematics.Scalable;
import uk.co.elionline.gears.mathematics.Subtractable;
import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.geometry.Translatable;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;
import uk.co.elionline.gears.utilities.Self;

public abstract class VectorH<S extends VectorH<S, V>, V extends Value<V>>
		extends /*@ReadOnly*/AbstractVector<S, V> implements
		Translatable<S>, /*@ReadOnly*/Addable<S, Matrix<?, ?>>,
		/*@ReadOnly*/Subtractable<S, Matrix<?, ?>>,
		/*@ReadOnly*/NonCommutativelyMultipliable<S, Matrix<?, ?>>, Scalable<S>,
		Self<S> {
	public enum Type {
		Absolute, Relative;
	}

	private Type type;

	public VectorH(Type type, int size, Order order, Orientation orientation,
			Factory<V> valueFactory) {
		super(size, order, orientation, valueFactory);

		this.type = type;
	}

	public VectorH(Type type, int size, Factory<V> valueFactory) {
		super(size, valueFactory);

		this.type = type;
	}

	public VectorH(Type type, Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(orientation, valueFactory, other);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);

		this.type = type;
	}

	public VectorH(Type type, Orientation orientation, Matrix<?, V> other) {
		super(orientation, other);

		this.type = type;
	}

	public VectorH(Type type, Matrix<?, V> other) {
		super(other);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, Vector<?, ?> other) {
		super(valueFactory, other);

		this.type = type;
	}

	public VectorH(Type type, Vector<?, V> other) {
		super(other);

		this.type = type;
	}

	public VectorH(Factory<V> valueFactory, VectorH<?, ?> other) {
		super(valueFactory, other.getMutableVector());

		this.type = other.getType();
	}

	public VectorH(VectorH<?, V> other) {
		super(other.getMutableVector());

		this.type = other.getType();
	}

	public <I> VectorH(Type type, Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, orientation, values, creationOperation);

		this.type = type;
	}

	public <I> VectorH(Type type, List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);

		this.type = type;
	}

	protected <I> VectorH(Type type, Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, orientation, values, creationOperation, null);

		this.type = type;
	}

	protected <I> VectorH(Type type, List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation, null);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(valueFactory, values);

		this.type = type;
	}

	protected VectorH(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, orientation, valueFactory, values, null);

		this.type = type;
	}

	protected VectorH(Type type, Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values, null);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Value<?>... values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, Value<?>... values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(order, orientation, values);

		this.type = type;
	}

	public VectorH(Type type, List<? extends Value<V>> values) {
		super(values);

		this.type = type;
	}

	protected VectorH(Type type, Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(order, orientation, values, (Void) null);

		this.type = type;
	}

	protected VectorH(Type type, List<? extends List<? extends Value<V>>> values,
			Void flag) {
		super(values, (Void) null);

		this.type = type;
	}

	@SafeVarargs
	public VectorH(Type type, Order order, Orientation orientation,
			Value<V>... values) {
		super(order, orientation, values);

		this.type = type;
	}

	@SafeVarargs
	public VectorH(Type type, Value<V>... values) {
		super(values);

		this.type = type;
	}

	public VectorH(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(copyByReference, order, orientation, values);

		this.type = type;
	}

	public VectorH(Type type, final boolean copyByReference,
			List<? extends V> values) {
		super(copyByReference, values);

		this.type = type;
	}

	protected VectorH(Type type, final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(copyByReference, order, orientation, values, null);

		this.type = type;
	}

	protected VectorH(Type type, boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, values, null);

		this.type = type;
	}

	@SafeVarargs
	public VectorH(Type type, boolean copyByReference, Order order,
			Orientation orientation, V... values) {
		super(copyByReference, order, orientation, values);

		this.type = type;
	}

	@SafeVarargs
	public VectorH(Type type, boolean copyByReference, V... values) {
		super(copyByReference, values);

		this.type = type;
	}

	@SafeVarargs
	public VectorH(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, Number... values) {
		super(order, orientation, valueFactory, values);

		this.type = type;

	}

	public VectorH(Type type, Factory<V> valueFactory, Number... values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, int[] values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, int[] values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, long[] values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, long[] values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, float[] values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, float[] values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			Factory<V> valueFactory, double[] values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, double[] values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(order, orientation, valueFactory, values);

		this.type = type;
	}

	public VectorH(Type type, Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		this.type = type;
	}

	@Override
	protected void finalise() {
		int size = getDimensions();

		resizeImplementation(size + 1);

		getElement(size).setValue(1);
	}

	public final Type getType() {
		return type;
	}

	public final void setType(Type type) {
		this.type = type;
	}

	public final int getProjectedDimensions() {
		return super.getDimensions() - 1;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final V getElement(int major, int minor) {
		return (V) super.getElement(major, minor);
	}

	@Override
	public final S add(Matrix<?, ?> other) {
		if (!other.getData().get(getProjectedDimensions()).equals(0)) {
			throw new IllegalArgumentException();
		}

		return super.add(other);
	}

	@Override
	public final S subtract(Matrix<?, ?> other) {
		if (!other.getData().get(getProjectedDimensions()).equals(0)) {
			throw new IllegalArgumentException();
		}

		return super.subtract(other);
	}

	@Override
	public final S multiply(double scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return super.multiply(scalar);
	}

	@Override
	public final S multiply(float scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return super.multiply(scalar);
	}

	@Override
	public final S multiply(long scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return super.multiply(scalar);
	}

	@Override
	public final S multiply(int scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return super.multiply(scalar);
	}

	@Override
	public final S multiply(Value<?> scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return super.multiply(scalar);
	}

	@Override
	public final S divide(double scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return divide(scalar);
	}

	@Override
	public final S divide(float scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return divide(scalar);
	}

	@Override
	public S divide(long scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return divide(scalar);
	}

	@Override
	public final S divide(int scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return divide(scalar);
	}

	@Override
	public final S divide(Value<?> scalar) {
		if (getType() == Type.Absolute) {
			throw new IllegalStateException();
		}

		return divide(scalar);
	}

	@Override
	public final S multiply(Matrix<?, ?> other) {
		List<Value<V>> multiplied = multiplyData(other.getData2());

		Value<V> lastElement = multiplied.get(getProjectedDimensions());
		if (!((lastElement.equals(0) && getType() == Type.Relative) || (lastElement
				.equals(1) && getType() == Type.Absolute))) {
			throw new IllegalArgumentException();
		}

		return setData(multiplied);
	}

	@Override
	public final S preMultiply(Matrix<?, ?> other) {
		List<Value<V>> multiplied = preMultiplyData(other.getData2());

		Value<V> lastElement = multiplied.get(getProjectedDimensions());
		if (!((lastElement.equals(0) && getType() == Type.Relative) || (lastElement
				.equals(1) && getType() == Type.Absolute))) {
			throw new IllegalArgumentException();
		}

		return setData(multiplied);
	}

	public abstract Vector<?, V> getMutableVector();

	@Override
	public final S translate(Vector<?, ?> translation) {
		if (translation.getDimensions() == getProjectedDimensions()) {
			getMutableVector().translate(translation);
		} else if (translation.getDimensions() == getDimensions()) {
			add(translation);
		}
		return getThis();
	}
}
