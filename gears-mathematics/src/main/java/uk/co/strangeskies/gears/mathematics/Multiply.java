package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class Multiply<O extends Multipliable<?, ? super T>, T> extends
		BinaryOperationExpression<O, Multipliable<? extends O, ? super T>, T> {
	public Multiply(
			Expression<? extends Multipliable<? extends O, ? super T>> firstOperand,
			Expression<? extends T> secondOperand) {
		super(firstOperand, secondOperand, new MultiplicationOperation<O, T>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
