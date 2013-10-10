package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;

public class XOROperation<O extends XORable<?, ? super T>, T> implements
		BinaryOperation<O, XORable<? extends O, ? super T>, T> {
	@Override
	public O apply(XORable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getXor(secondOperand);
	}
}
