package uk.co.strangeskies.gears.mathematics.geometry.matrix.builder;

import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector3;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector4;
import uk.co.strangeskies.gears.mathematics.values.Value;

public interface ValueMatrixBuilder<V extends Value<V>> {
	public Vector2<V> vector2();

	public Vector3<V> vector3();

	public Vector4<V> vector4();
}
