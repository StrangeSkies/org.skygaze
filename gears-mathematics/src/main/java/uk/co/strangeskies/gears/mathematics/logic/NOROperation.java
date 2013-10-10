package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;

public class NOROperation<O extends NORable<?, ? super T>, T> implements
		BinaryOperation<O, NORable<? extends O, ? super T>, T> {
	@Override
	public O apply(NORable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getNor(secondOperand);
	}
}
