package uk.co.elionline.emerge.mathematics.geometry.matrices;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.geometry.Translatable;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Self;

public interface Vector<S extends Vector<S, V>, V extends Value<V>> extends
		Self<S>, Matrix<S, V>, Translatable<S> {
	public enum Orientation {
		Row(Order.RowMajor), Column(Order.ColumnMajor);

		private final Order associatedOrder;

		private Orientation(Order associatedOrder) {
			this.associatedOrder = associatedOrder;
		}

		public final Order getAssociatedOrder() {
			return associatedOrder;
		}

		public static final Orientation getFromAssociatedOrder(Order order) {
			if (order == Order.RowMajor) {
				return Row;
			} else {
				return Column;
			}
		}
	}

	public S transpose();

	public S setOrientation(Orientation orientation);

	public int getDimensions();

	public Orientation getOrientation();

	public Expression<Orientation> getOrientationExpression();

	public V getElement(int index);
}