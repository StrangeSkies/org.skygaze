package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.utilities.Self;

public interface BooleanCombinationBehaviour<S extends BooleanCombinationBehaviour<S, T> & Self<S>, T>
		extends ANDable<S, T>, NANDable<S, T>, NORable<S, T>, ORable<S, T>,
		XNORable<S, T>, XORable<S, T> {
}
