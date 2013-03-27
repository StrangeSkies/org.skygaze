package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.emerge.mathematics.values.Value;

public interface NonCommutativelyRotatable2<S extends NonCommutativelyRotatable2<S>>
		extends Rotatable2<S> {
	public S preRotate(Value<?> value);

	public S preRotate(Value<?> angle, Vector2<?> centre);

	public S getPreRotated(Value<?> value);

	public S getPreRotated(Value<?> angle, Vector2<?> centre);
}
