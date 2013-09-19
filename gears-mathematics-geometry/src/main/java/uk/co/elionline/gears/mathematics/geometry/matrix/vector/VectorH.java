package uk.co.elionline.gears.mathematics.geometry.matrix.vector;

import uk.co.elionline.gears.mathematics.Addable;
import uk.co.elionline.gears.mathematics.NonCommutativelyMultipliable;
import uk.co.elionline.gears.mathematics.Scalable;
import uk.co.elionline.gears.mathematics.Subtractable;
import uk.co.elionline.gears.mathematics.geometry.Translatable;
import uk.co.elionline.gears.mathematics.geometry.matrix.Matrix;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Self;

public interface VectorH<S extends VectorH<S, V>, V extends Value<V>> extends
/*@ReadOnly*/Vector<S, V>, Translatable<S>, /*@ReadOnly*/
Addable<S, Matrix<?, ?>>,
/*@ReadOnly*/Subtractable<S, Matrix<?, ?>>,
/*@ReadOnly*/NonCommutativelyMultipliable<S, Matrix<?, ?>>, Scalable<S>,
		Self<S> {
	public enum Type {
		Absolute, Relative;
	}

	public Type getType();

	public void setType(Type type);

	public int getProjectedDimensions();

	public Vector<?, V> getMutableVector();
}
