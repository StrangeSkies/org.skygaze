package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;

public class NOROperation<O extends NORable<?, ? super T>, T> implements
		BinaryOperation<O, NORable<? extends O, ? super T>, T> {
	@Override
	public O apply(NORable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getNor(secondOperand);
	}
}
