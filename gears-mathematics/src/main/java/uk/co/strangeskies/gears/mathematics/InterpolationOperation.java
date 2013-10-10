package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.functions.TrinaryOperation;
import uk.co.strangeskies.gears.mathematics.values.Value;

public interface InterpolationOperation<I, T> extends
		TrinaryOperation<I, T, T, Value<?>> {
}
