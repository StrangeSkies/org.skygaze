package uk.co.strangeskies.gears.mathematics.geometry.matrix.vector;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.geometry.Translatable;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.Matrix;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;
import uk.co.strangeskies.gears.mathematics.values.Value;
import uk.co.strangeskies.gears.utilities.Self;

public interface Vector<S extends Vector<S, V>, V extends Value<V>> extends
		Self<S>, Matrix<S, V>, Translatable<S> {
	public enum Orientation {
		Row {
			@Override
			public Order getAssociatedOrder() {
				return Order.RowMajor;
			}
		},
		Column {
			@Override
			public Order getAssociatedOrder() {
				return Order.RowMajor;
			}
		};

		public abstract Order getAssociatedOrder();
	}

	public S transpose();

	public S setOrientation(Orientation orientation);

	public int getDimensions();

	public Orientation getOrientation();

	public Expression<Orientation> getOrientationExpression();

	public V getElement(int index);

	public DoubleValue getSize();

	public V getSizeSquared();

	public S setData(boolean setByReference, Vector<?, V> to);

	public S setData(Vector<?, ?> to);
}
