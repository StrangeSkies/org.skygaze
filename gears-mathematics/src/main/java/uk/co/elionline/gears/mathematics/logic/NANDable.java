package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.utilities.Self;

public interface NANDable<S extends NANDable<S, T> & Self<S>, T> {
	public S nand(T value);

	public S getNand(T value);
}
