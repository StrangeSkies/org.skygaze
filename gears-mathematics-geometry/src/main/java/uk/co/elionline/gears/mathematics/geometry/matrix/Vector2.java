package uk.co.elionline.gears.mathematics.geometry.matrix;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.geometry.Rotatable2;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class Vector2<V extends Value<V>> extends
		AbstractVector<Vector2<V>, V> implements Rotatable2<Vector2<V>> {
	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory) {
		super(2, order, orientation, valueFactory);
	}

	public Vector2(Factory<V> valueFactory) {
		super(2, valueFactory);
	}

	public Vector2(Orientation orientation, Factory<V> valueFactory,
			Matrix<?, ?> other) {
		super(orientation, valueFactory, other);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, Matrix<?, ?> other) {
		super(valueFactory, other);

		assertDimensions(this, 2);
	}

	public Vector2(Orientation orientation, Matrix<?, V> other) {
		super(orientation, other);

		assertDimensions(this, 2);
	}

	public Vector2(Matrix<?, V> other) {
		super(other);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, Vector<?, ?> other) {
		super(valueFactory, other);

		assertDimensions(this, 2);
	}

	public Vector2(Vector<?, V> other) {
		super(other);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, Vector2<?> other) {
		super(valueFactory, other);
	}

	public Vector2(Vector2<V> other) {
		super(other);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			Point other) {
		super(order, orientation, valueFactory, other.x, other.y);
	}

	public Vector2(Factory<V> valueFactory, Point other) {
		super(valueFactory, other.x, other.y);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			Point2D other) {
		super(order, orientation, valueFactory, other.getX(), other.getY());
	}

	public Vector2(Factory<V> valueFactory, Point2D other) {
		super(valueFactory, other.getX(), other.getY());
	}

	public Vector2(Factory<V> valueFactory, Dimension dimension) {
		super(valueFactory, dimension.width, dimension.height);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			Dimension dimension) {
		super(order, orientation, valueFactory, dimension.width, dimension.height);
	}

	public <I> Vector2(Order order, Orientation orientation,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(order, orientation, values, creationOperation);

		assertDimensions(this, 2);
	}

	public <I> Vector2(List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		super(values, creationOperation);

		assertDimensions(this, 2);
	}

	protected <I> Vector2(Order order, Orientation orientation,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(order, orientation, values, creationOperation, null);

		assertDimensions(this, 2);
	}

	protected <I> Vector2(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation, Void flag) {
		super(values, creationOperation, null);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, List<? extends Value<?>> values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	protected Vector2(Order order, Orientation orientation,
			final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(order, orientation, valueFactory, values, null);

		assertDimensions(this, 2);
	}

	protected Vector2(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values, Void flag) {
		super(valueFactory, values, null);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			Value<?>... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, Value<?>... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation,
			List<? extends Value<V>> values) {
		super(order, orientation, values);

		assertDimensions(this, 2);
	}

	public Vector2(List<? extends Value<V>> values) {
		super(values);

		assertDimensions(this, 2);
	}

	protected Vector2(Order order, Orientation orientation,
			List<? extends List<? extends Value<V>>> values, Void flag) {
		super(order, orientation, values, (Void) null);

		assertDimensions(this, 2);
	}

	protected Vector2(List<? extends List<? extends Value<V>>> values, Void flag) {
		super(values, (Void) null);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Vector2(Order order, Orientation orientation, Value<V>... values) {
		super(order, orientation, values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Vector2(Value<V>... values) {
		super(values);

		assertDimensions(this, 2);
	}

	public Vector2(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends V> values) {
		super(copyByReference, order, orientation, values);

		assertDimensions(this, 2);
	}

	public Vector2(final boolean copyByReference, List<? extends V> values) {
		super(copyByReference, values);

		assertDimensions(this, 2);
	}

	protected Vector2(final boolean copyByReference, Order order,
			Orientation orientation, List<? extends List<? extends V>> values,
			Void flag) {
		super(copyByReference, order, orientation, values, null);

		assertDimensions(this, 2);
	}

	protected Vector2(boolean copyByReference,
			List<? extends List<? extends V>> values, Void flag) {
		super(copyByReference, values, null);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Vector2(boolean copyByReference, Order order, Orientation orientation,
			V... values) {
		super(copyByReference, order, orientation, values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Vector2(boolean copyByReference, V... values) {
		super(copyByReference, values);

		assertDimensions(this, 2);
	}

	@SafeVarargs
	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			Number... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, Number... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			int[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, int[] values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			long[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, long[] values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			float[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, float[] values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation, Factory<V> valueFactory,
			double[] values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, double[] values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation,
			final Factory<V> valueFactory, int[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, int[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation,
			final Factory<V> valueFactory, long[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, long[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation,
			final Factory<V> valueFactory, float[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, float[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Order order, Orientation orientation,
			final Factory<V> valueFactory, double[]... values) {
		super(order, orientation, valueFactory, values);

		assertDimensions(this, 2);
	}

	public Vector2(Factory<V> valueFactory, double[]... values) {
		super(valueFactory, values);

		assertDimensions(this, 2);
	}

	@Override
	public final Vector2<V> copy() {
		return new Vector2<V>(this);
	}

	public final V getX() {
		return getElement(0);
	}

	public final V getY() {
		return getElement(1);
	}

	@Override
	public final Vector2<V> getRotated(Value<?> angle) {
		return copy().rotate(angle);
	}

	@Override
	public final Vector2<V> rotate(Value<?> angle) {
		// TODO implement rotation
		return null;
	}

	@Override
	public final Vector2<V> getRotated(Value<?> angle, Vector2<?> centre) {
		return copy().rotate(angle, centre);
	}

	@Override
	public final Vector2<V> rotate(Value<?> angle, Vector2<?> centre) {
		// TODO implement rotation about point
		return null;
	}

	public final Dimension getDimension() {
		return new Dimension(getX().intValue(), getY().intValue());
	}
}
