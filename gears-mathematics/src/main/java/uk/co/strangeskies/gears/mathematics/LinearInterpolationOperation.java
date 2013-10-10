package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.values.Value;
import uk.co.strangeskies.gears.utilities.Utilities;

public class LinearInterpolationOperation<S extends T, T extends Scalable<S> & Subtractable<S, ? super T>>
		implements InterpolationOperation<S, T> {
	@Override
	public S apply(T from, T to, Value<?> delta) {
		if (Utilities.areEqual(from, to)) {
			return from.copy();
		}
		if (delta.equals(0)) {
			return from.copy();
		}
		if (delta.equals(1)) {
			return to.copy();
		}

		return from.getAdded(to.getSubtracted(from).getMultiplied(delta));
	}
}
