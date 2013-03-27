package uk.co.elionline.gears.mathematics;

import uk.co.elionline.gears.mathematics.functions.TrinaryOperation;
import uk.co.elionline.gears.mathematics.values.Value;

public interface InterpolationOperation<I, T> extends
		TrinaryOperation<I, T, T, Value<?>> {
}
