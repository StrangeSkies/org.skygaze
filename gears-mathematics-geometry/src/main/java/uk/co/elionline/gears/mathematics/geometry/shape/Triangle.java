package uk.co.elionline.gears.mathematics.geometry.shape;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.Value;

public interface Triangle<V extends Value<V>> extends
		SimplePolygon<Triangle<V>, V> {
	public void set(/*@ReadOnly*/Vector2<?> newCornerA, /*@ReadOnly*/
			Vector2<?> newCornerB, /*@ReadOnly*/Vector2<?> newCornerC);

	public void set(float ax, float ay, float bx, float by, float cx, float cy);

	public void set(/*@ReadOnly*/Triangle<?> orig);

	public void setA(/*@ReadOnly*/Vector2<?> newCornerA);

	public void setB(/*@ReadOnly*/Vector2<?> newCornerB);

	public void setC(/*@ReadOnly*/Vector2<?> newCornerC);

	public/*@ReadOnly*/Vector2<V> GetA() /*@ReadOnly*/;

	public/*@ReadOnly*/Vector2<V> GetB() /*@ReadOnly*/;

	public/*@ReadOnly*/Vector2<V> GetC() /*@ReadOnly*/;
}
