package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;
import uk.co.elionline.emerge.mathematics.values.Value;

public class Rotation2Operation<O> implements
		BinaryOperation<O, Rotatable2<? extends O>, Value<?>> {
	@Override
	public O apply(Rotatable2<? extends O> firstOperand, Value<?> secondOperand) {
		return firstOperand.getRotated(secondOperand);
	}
}
