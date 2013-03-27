package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;

public class PreRotation2Operation<O> implements
		BinaryOperation<O, NonCommutativelyRotatable2<? extends O>, Value<?>> {
	@Override
	public O apply(NonCommutativelyRotatable2<? extends O> firstOperand,
			Value<?> secondOperand) {
		return firstOperand.getPreRotated(secondOperand);
	}
}
