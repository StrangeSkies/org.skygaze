package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.functions.UnaryOperation;

public class NegationOperation<O> implements
		UnaryOperation<O, Negatable<?, ? extends O>> {
	@Override
	public O apply(Negatable<?, ? extends O> firstOperand) {
		return firstOperand.getNegated();
	}
}
