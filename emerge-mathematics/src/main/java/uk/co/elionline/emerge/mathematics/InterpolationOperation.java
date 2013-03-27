package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.functions.TrinaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;

public interface InterpolationOperation<I, T> extends
		TrinaryOperation<I, T, T, Value<?>> {
}
