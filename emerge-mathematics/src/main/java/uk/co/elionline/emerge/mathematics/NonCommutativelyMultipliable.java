package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.utilities.Self;

public interface NonCommutativelyMultipliable<S extends NonCommutativelyMultipliable<S, T>, T>
		extends Multipliable<S, T>, Self<S> {
	public S preMultiply(T value);

	public S getPreMultiplied(T value);
}
