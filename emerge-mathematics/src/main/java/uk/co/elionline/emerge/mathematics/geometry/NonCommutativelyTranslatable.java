package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;

public interface NonCommutativelyTranslatable<S extends NonCommutativelyTranslatable<S>>
		extends Translatable<S> {
	public S preTranslate(Vector<?, ?> translation);

	public S getPreTranslated(Vector<?, ?> translation);
}
