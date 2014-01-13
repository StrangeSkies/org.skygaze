package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.UnaryOperationExpression;

public class NOT<O> extends
		UnaryOperationExpression<NOTable<?, ? extends O>, O> {
	public NOT(Expression<? extends NOTable<?, ? extends O>> operand) {
		super(operand, new NOTOperation<O>());
	}

	@Override
	public String toString() {
		return "¬" + getOperand();
	}
}
