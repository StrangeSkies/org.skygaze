package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.utilities.Self;

public interface Negatable<S extends Negatable<S, N>, N extends Negatable<N, S>>
		extends Self<S> {
	public N negate();

	public N getNegated();
}
