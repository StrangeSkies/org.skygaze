package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.utilities.Self;

public interface Incrementable<S extends Incrementable<S>> extends Self<S> {
	public S increment();

	public S decrement();

	public S getIncremented();

	public S getDecremented();
}
