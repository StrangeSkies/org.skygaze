package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.utilities.Self;

public interface ANDable<S extends ANDable<S, T> & Self<S>, T> {
	public S and(T value);

	public S getAnd(T value);
}
