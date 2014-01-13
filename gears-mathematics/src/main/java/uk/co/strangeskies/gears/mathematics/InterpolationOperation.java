package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.values.Value;
import uk.co.strangeskies.gears.utilities.functions.TriFunction;

public interface InterpolationOperation<T, I> extends
		TriFunction<T, T, Value<?>, I> {
}
