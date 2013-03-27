package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.utilities.Self;

public interface Multipliable<S extends Multipliable<S, T>, T> extends Self<S> {
	public S multiply(T value);

	public S getMultiplied(T value);
}
