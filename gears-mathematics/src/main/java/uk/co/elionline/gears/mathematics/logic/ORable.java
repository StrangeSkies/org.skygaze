package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.utilities.Self;

public interface ORable<S extends ORable<S, T> & Self<S>, T> {
	public S or(T value);

	public S getOr(T value);
}
