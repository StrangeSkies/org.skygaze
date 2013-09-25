package uk.co.elionline.gears.mathematics;

import uk.co.elionline.gears.utilities.Self;

public interface Negatable<S extends Negatable<S, N>, N extends Negatable<? extends N, ? extends S>>
		extends Self<S> {
	public N negate();

	public N getNegated();
}
