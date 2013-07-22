package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector;

public interface NonCommutativelyTranslatable<S extends NonCommutativelyTranslatable<S>>
		extends Translatable<S> {
	public S preTranslate(Vector<?, ?> translation);

	public S getPreTranslated(Vector<?, ?> translation);
}
