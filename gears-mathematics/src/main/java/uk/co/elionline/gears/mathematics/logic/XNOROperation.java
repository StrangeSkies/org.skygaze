package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;

public class XNOROperation<O extends XNORable<?, ? super T>, T> implements
		BinaryOperation<O, XNORable<? extends O, ? super T>, T> {
	@Override
	public O apply(XNORable<? extends O, ? super T> firstOperand, T secondOperand) {
		return firstOperand.getXnor(secondOperand);
	}
}
