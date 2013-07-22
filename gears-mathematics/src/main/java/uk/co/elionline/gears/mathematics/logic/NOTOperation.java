package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;

public class NOTOperation<O> implements
		UnaryOperation<O, NOTable<?, ? extends O>> {
	@Override
	public O apply(NOTable<?, ? extends O> firstOperand) {
		return firstOperand.getNot();
	}
}
