package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class Addition<O extends Addable<?, ? super T>, T> extends
		BinaryOperationExpression<Addable<? extends O, ? super T>, T, O> {
	public Addition(
			Expression<? extends Addable<? extends O, ? super T>> firstOperand,
			Expression<? extends T> secondOperand) {
		super(firstOperand, secondOperand, new Add<O, T>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " + " + getSecondOperand() + ")";
	}
}
