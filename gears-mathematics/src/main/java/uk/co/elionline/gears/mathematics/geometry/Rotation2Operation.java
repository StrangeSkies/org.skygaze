package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;
import uk.co.elionline.gears.mathematics.values.Value;

public class Rotation2Operation<O> implements
		BinaryOperation<O, Rotatable2<? extends O>, Value<?>> {
	@Override
	public O apply(Rotatable2<? extends O> firstOperand, Value<?> secondOperand) {
		return firstOperand.getRotated(secondOperand);
	}
}
