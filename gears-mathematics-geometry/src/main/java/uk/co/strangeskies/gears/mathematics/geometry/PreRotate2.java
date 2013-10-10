package uk.co.strangeskies.gears.mathematics.geometry;

import uk.co.strangeskies.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.values.Value;

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
