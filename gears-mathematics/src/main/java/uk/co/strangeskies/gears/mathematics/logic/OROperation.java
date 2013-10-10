package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;

public class OROperation<O extends ORable<?, ? super T>, T> implements
		BinaryOperation<O, ORable<? extends O, ? super T>, T> {
	@Override
	public O apply(ORable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getOr(secondOperand);
	}
}
