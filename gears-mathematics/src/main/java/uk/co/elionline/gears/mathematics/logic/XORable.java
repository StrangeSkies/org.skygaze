package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.utilities.Self;

public interface XORable<S extends XORable<S, T> & Self<S>, T> {
	public S xor(T value);

	public S getXor(T value);
}
