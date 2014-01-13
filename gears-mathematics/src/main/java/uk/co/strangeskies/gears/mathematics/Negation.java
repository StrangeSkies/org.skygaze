package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.UnaryOperationExpression;

public class Negation<O> extends
		UnaryOperationExpression<Negatable<?, ? extends O>, O> {
	public Negation(Expression<? extends Negatable<?, ? extends O>> operand) {
		super(operand, new Negate<O>());
	}

	@Override
	public String toString() {
		return "¬" + getOperand();
	}
}
