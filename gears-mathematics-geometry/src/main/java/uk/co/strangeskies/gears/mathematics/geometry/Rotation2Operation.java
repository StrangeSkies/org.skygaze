package uk.co.strangeskies.gears.mathematics.geometry;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.values.Value;

public class Rotation2Operation<O> implements
		BinaryOperation<O, Rotatable2<? extends O>, Value<?>> {
	@Override
	public O apply(Rotatable2<? extends O> firstOperand, Value<?> secondOperand) {
		return firstOperand.getRotated(secondOperand);
	}
}
