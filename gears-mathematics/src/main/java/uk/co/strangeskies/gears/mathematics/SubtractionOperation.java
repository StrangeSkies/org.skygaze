package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;

public class SubtractionOperation<O extends Subtractable<?, ? super T>, T>
		implements BinaryOperation<O, Subtractable<? extends O, ? super T>, T> {
	@Override
	public O apply(Subtractable<? extends O, ? super T> firstOperand,
			T secondOperand) {
		return firstOperand.getSubtracted(secondOperand);
	}
}
