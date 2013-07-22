package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;

public class ANDOperation<O extends ANDable<?, ? super T>, T> implements
		BinaryOperation<O, ANDable<? extends O, ? super T>, T> {
	@Override
	public O apply(ANDable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getAnd(secondOperand);
	}
}
