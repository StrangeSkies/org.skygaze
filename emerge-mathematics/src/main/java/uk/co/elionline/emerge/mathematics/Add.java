package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;

public class Add<O extends Addable<?, ? super T>, T> extends
		BinaryOperationExpression<O, Addable<? extends O, ? super T>, T> {
	public Add(
			Expression<? extends Addable<? extends O, ? super T>> firstOperand,
			Expression<? extends T> secondOperand) {
		super(firstOperand, secondOperand, new AdditionOperation<O, T>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " + " + getSecondOperand() + ")";
	}
}
