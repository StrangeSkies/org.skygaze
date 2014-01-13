package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class PreMultiplication<O extends NonCommutativelyMultipliable<?, ? super T>, T>
		extends
		BinaryOperationExpression<NonCommutativelyMultipliable<? extends O, ? super T>, T, O> {
	public PreMultiplication(
			Expression<? extends NonCommutativelyMultipliable<? extends O, ? super T>> firstOperand,
			Expression<? extends T> secondOperand) {
		super(firstOperand, secondOperand, new PreMultiply<O, T>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
