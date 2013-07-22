package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;

public class OR<O extends ORable<?, ? super T>, T>
		extends
		BinaryOperationExpression</*@ReadOnly*/O, /*@ReadOnly*/ORable<? extends O, ? super T>, /*@ReadOnly*/T> {
	public OR(
			Expression<? extends /*@ReadOnly*/ORable<? extends O, ? super T>> firstOperand,
			Expression<? extends /*@ReadOnly*/T> secondOperand) {
		super(firstOperand, secondOperand, new OROperation<O, T>());
	}
}
