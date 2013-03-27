package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.functions.UnaryOperation;

public class NegationOperation<O> implements
		UnaryOperation<O, Negatable<?, ? extends O>> {
	@Override
	public O apply(Negatable<?, ? extends O> firstOperand) {
		return firstOperand.getNegated();
	}
}
