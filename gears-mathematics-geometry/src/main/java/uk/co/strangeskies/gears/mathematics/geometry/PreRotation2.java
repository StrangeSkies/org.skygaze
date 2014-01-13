package uk.co.strangeskies.gears.mathematics.geometry;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.values.Value;

public class PreRotation2<O>
		extends
		BinaryOperationExpression<NonCommutativelyRotatable2<? extends O>, Value<?>, O> {
	public PreRotation2(
			Expression<? extends NonCommutativelyRotatable2<? extends O>> firstOperand,
			Expression<? extends Value<?>> secondOperand) {
		super(firstOperand, secondOperand, new PreRotate2<O>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
