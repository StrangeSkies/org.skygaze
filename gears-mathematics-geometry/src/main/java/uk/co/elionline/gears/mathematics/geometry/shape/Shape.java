package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Copyable;
import uk.co.elionline.gears.utilities.Self;

public interface Shape<S extends Shape<S>> extends Self<S>, Copyable<S> {
	public Value<?> getArea(/*@readOnly this*/);

	public Value<?> getPerimeter(/*@readOnly this*/);

	public boolean contains(
	/*@readOnly this, *//*@ReadOnly*/Vector2<?> other);

	public boolean touches(/*@readOnly this, *//*@ReadOnly*/Vector2<?> other);

	public boolean intersects(
	/*@readOnly this, *//*@ReadOnly*/Shape<?> other);

	public boolean touches(/*@readOnly this, *//*@ReadOnly*/Shape<?> other);

	public Bounds2<?> getBounds(/*@readOnly this*/);
}
