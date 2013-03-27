package uk.co.elionline.gears.mathematics;

import uk.co.elionline.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;

public class Subtract<O extends Subtractable<?, ? super T>, T> extends
		BinaryOperationExpression<O, Subtractable<? extends O, ? super T>, T> {
	public Subtract(
			Expression<? extends Subtractable<? extends O, ? super T>> firstOperand,
			Expression<? extends T> secondOperand) {
		super(firstOperand, secondOperand, new SubtractionOperation<O, T>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " - " + getSecondOperand() + ")";
	}
}
