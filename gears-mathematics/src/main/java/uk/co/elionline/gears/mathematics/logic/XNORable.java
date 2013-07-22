package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.utilities.Self;

public interface XNORable<S extends XNORable<S, T> & Self<S>, T> {
	public S xnor(T value);

	public S getXnor(T value);
}
