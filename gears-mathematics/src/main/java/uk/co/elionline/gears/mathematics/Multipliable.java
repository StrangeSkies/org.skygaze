package uk.co.elionline.gears.mathematics;

import uk.co.elionline.gears.utilities.Self;

public interface Multipliable<S extends Multipliable<S, T>, T> extends Self<S> {
	public S multiply(T value);

	public S getMultiplied(T value);
}
