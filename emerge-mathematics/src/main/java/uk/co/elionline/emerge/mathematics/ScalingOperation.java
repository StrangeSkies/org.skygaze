package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;

public class ScalingOperation<O> implements
		BinaryOperation<O, Scalable<? extends O>, Value<?>> {
	@Override
	public O apply(Scalable<? extends O> firstOperand,
			Value<?> secondOperand) {
		return firstOperand.getMultiplied(secondOperand);
	}
}
