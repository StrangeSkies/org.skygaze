package uk.co.strangeskies.gears.mathematics.geometry;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.values.Value;

public class PreRotation2Operation<O> implements
		BinaryOperation<O, NonCommutativelyRotatable2<? extends O>, Value<?>> {
	@Override
	public O apply(NonCommutativelyRotatable2<? extends O> firstOperand,
			Value<?> secondOperand) {
		return firstOperand.getPreRotated(secondOperand);
	}
}
