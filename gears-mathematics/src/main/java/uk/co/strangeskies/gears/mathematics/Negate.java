package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.UnaryOperationExpression;

public class Negate<O> extends
		UnaryOperationExpression<O, Negatable<?, ? extends O>> {
	public Negate(Expression<? extends Negatable<?, ? extends O>> operand) {
		super(operand, new NegationOperation<O>());
	}

	@Override
	public String toString() {
		return "¬" + getOperand();
	}
}
