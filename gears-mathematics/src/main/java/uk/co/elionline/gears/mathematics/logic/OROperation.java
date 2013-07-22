package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;

public class OROperation<O extends ORable<?, ? super T>, T> implements
		BinaryOperation<O, ORable<? extends O, ? super T>, T> {
	@Override
	public O apply(ORable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getOr(secondOperand);
	}
}
