package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.values.Value;

public class ScalingOperation<O> implements
		BinaryOperation<O, Scalable<? extends O>, Value<?>> {
	@Override
	public O apply(Scalable<? extends O> firstOperand,
			Value<?> secondOperand) {
		return firstOperand.getMultiplied(secondOperand);
	}
}
