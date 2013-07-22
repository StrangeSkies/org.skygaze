package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Self;

public interface Rotatable2<S extends Rotatable2<S>> extends Self<S> {
	public S rotate(Value<?> angle);

	public S rotate(Value<?> angle, Vector2<?> centre);

	public S getRotated(Value<?> angle);

	public S getRotated(Value<?> angle, Vector2<?> centre);
}
