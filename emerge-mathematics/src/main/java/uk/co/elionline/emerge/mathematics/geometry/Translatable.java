package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;
import uk.co.elionline.emerge.utilities.Self;

public interface Translatable<S extends Translatable<S>> extends Self<S> {
	public S translate(Vector<?, ?> translation);

	public S getTranslated(Vector<?, ?> translation);
}
