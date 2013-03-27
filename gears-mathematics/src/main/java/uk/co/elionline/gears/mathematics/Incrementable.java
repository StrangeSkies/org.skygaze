package uk.co.elionline.gears.mathematics;

import uk.co.elionline.gears.utilities.Self;

public interface Incrementable<S extends Incrementable<S>> extends Self<S> {
	public S increment();

	public S decrement();

	public S getIncremented();

	public S getDecremented();
}
