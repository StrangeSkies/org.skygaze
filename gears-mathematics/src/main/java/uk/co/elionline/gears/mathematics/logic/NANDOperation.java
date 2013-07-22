package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;

public class NANDOperation<O extends NANDable<?, ? super T>, T> implements
		BinaryOperation<O, NANDable<? extends O, ? super T>, T> {
	@Override
	public O apply(NANDable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getNand(secondOperand);
	}
}
