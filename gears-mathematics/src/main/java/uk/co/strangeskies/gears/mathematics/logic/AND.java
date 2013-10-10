package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class AND<O extends ANDable<?, ? super T>, T>
		extends
		BinaryOperationExpression</*@ReadOnly*/O, /*@ReadOnly*/ANDable<? extends O, ? super T>, /*@ReadOnly*/T> {
	public AND(
			Expression<? extends /*@ReadOnly*/ANDable<? extends O, ? super T>> firstOperand,
			Expression<? extends /*@ReadOnly*/T> secondOperand) {
		super(firstOperand, secondOperand, new ANDOperation<O, T>());
	}
}
