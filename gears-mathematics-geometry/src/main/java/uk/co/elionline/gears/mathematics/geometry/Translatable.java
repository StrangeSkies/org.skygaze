package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector;
import uk.co.elionline.gears.utilities.Self;

public interface Translatable<S extends Translatable<S>> extends Self<S> {
	public S translate(Vector<?, ?> translation);

	public S getTranslated(Vector<?, ?> translation);
}
