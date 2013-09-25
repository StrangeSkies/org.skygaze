package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.expressions.Variable;
import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Copyable;
import uk.co.elionline.gears.utilities.Property;
import uk.co.elionline.gears.utilities.Self;

public interface Shape<S extends Shape<S>> extends Self<S>, Copyable<S>,
		Property<S, S>, Variable<S> {
	public Value<?> getArea(/*@readOnly this*/);

	public Value<?> getPerimeter(/*@readOnly this*/);

	public boolean contains(
	/*@readOnly this, *//*@ReadOnly*/Vector2<?> point);

	public boolean touches(/*@readOnly this, *//*@ReadOnly*/Vector2<?> point);

	public boolean intersects(
	/*@readOnly this, *//*@ReadOnly*/Shape<?> shape);

	public boolean touches(/*@readOnly this, *//*@ReadOnly*/Shape<?> shape);

	public Bounds2<?> getBounds(/*@readOnly this*/);
}