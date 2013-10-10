package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;

public class AdditionOperation<O extends Addable<?, ? super T>, T> implements
		BinaryOperation<O, Addable<? extends O, ? super T>, T> {
	@Override
	public O apply(Addable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getAdded(secondOperand);
	}
}
