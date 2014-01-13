package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class Subtraction<O extends Subtractable<?, ? super T>, T> extends
		BinaryOperationExpression<Subtractable<? extends O, ? super T>, T, O> {
	public Subtraction(
			Expression<? extends Subtractable<? extends O, ? super T>> firstOperand,
			Expression<? extends T> secondOperand) {
		super(firstOperand, secondOperand, new Subtract<O, T>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " - " + getSecondOperand() + ")";
	}
}
