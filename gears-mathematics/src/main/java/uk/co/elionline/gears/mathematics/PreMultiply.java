package uk.co.elionline.gears.mathematics;

import uk.co.elionline.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;

public class PreMultiply<O extends NonCommutativelyMultipliable<?, ? super T>, T>
		extends
		BinaryOperationExpression<O, NonCommutativelyMultipliable<? extends O, ? super T>, T> {
	public PreMultiply(
			Expression<? extends NonCommutativelyMultipliable<? extends O, ? super T>> firstOperand,
			Expression<? extends T> secondOperand) {
		super(firstOperand, secondOperand, new PreMultiplicationOperation<O, T>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
