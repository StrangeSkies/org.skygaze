package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.values.Value;

public class PreRotate2<O>
		extends
		BinaryOperationExpression<O, NonCommutativelyRotatable2<? extends O>, Value<?>> {
	public PreRotate2(
			Expression<? extends NonCommutativelyRotatable2<? extends O>> firstOperand,
			Expression<? extends Value<?>> secondOperand) {
		super(firstOperand, secondOperand, new PreRotation2Operation<O>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
