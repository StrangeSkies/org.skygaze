package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;

public class MultiplicationOperation<O extends Multipliable<?, ? super T>, T>
		implements BinaryOperation<O, Multipliable<? extends O, ? super T>, T> {
	@Override
	public O apply(Multipliable<? extends O, ? super T> firstOperand,
			T secondOperand) {
		return firstOperand.getMultiplied(secondOperand);
	}
}
