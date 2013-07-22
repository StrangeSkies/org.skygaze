package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.expressions.UnaryOperationExpression;

public class NOT<O> extends
		UnaryOperationExpression<O, NOTable<?, ? extends O>> {
	public NOT(Expression<? extends NOTable<?, ? extends O>> operand) {
		super(operand, new NOTOperation<O>());
	}

	@Override
	public String toString() {
		return "¬" + getOperand();
	}
}
