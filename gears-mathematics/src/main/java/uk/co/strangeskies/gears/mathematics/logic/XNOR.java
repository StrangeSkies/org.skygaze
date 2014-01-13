package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class XNOR<O extends XNORable<?, ? super T>, T>
		extends
		BinaryOperationExpression</* @ReadOnly */XNORable<? extends O, ? super T>, /*
																																								 * @
																																								 * ReadOnly
																																								 */T, /*
																																											 * @
																																											 * ReadOnly
																																											 */O> {
	public XNOR(
			Expression<? extends /* @ReadOnly */XNORable<? extends O, ? super T>> firstOperand,
			Expression<? extends /* @ReadOnly */T> secondOperand) {
		super(firstOperand, secondOperand, new XNOROperation<O, T>());
	}
}
