package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class XOR<O extends XORable<?, ? super T>, T>
		extends
		BinaryOperationExpression</* @ReadOnly */XORable<? extends O, ? super T>, /*
																																							 * @
																																							 * ReadOnly
																																							 */T, /*
																																										 * @
																																										 * ReadOnly
																																										 */O> {
	public XOR(
			Expression<? extends /* @ReadOnly */XORable<? extends O, ? super T>> firstOperand,
			Expression<? extends /* @ReadOnly */T> secondOperand) {
		super(firstOperand, secondOperand, new XOROperation<O, T>());
	}
}
