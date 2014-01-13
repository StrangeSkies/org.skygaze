package uk.co.strangeskies.gears.mathematics.logic;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class NOR<O extends NORable<?, ? super T>, T>
		extends
		BinaryOperationExpression</* @ReadOnly */NORable<? extends O, ? super T>, /*
																																							 * @
																																							 * ReadOnly
																																							 */T, /*
																																										 * @
																																										 * ReadOnly
																																										 */O> {
	public NOR(
			Expression<? extends /* @ReadOnly */NORable<? extends O, ? super T>> firstOperand,
			Expression<? extends /* @ReadOnly */T> secondOperand) {
		super(firstOperand, secondOperand, new NOROperation<O, T>());
	}
}
