package uk.co.elionline.gears.mathematics;

import uk.co.elionline.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.values.Value;

public class Scale<O> extends
		BinaryOperationExpression<O, Scalable<? extends O>, Value<?>> {
	public Scale(Expression<? extends Scalable<? extends O>> firstOperand,
			Expression<? extends Value<?>> secondOperand) {
		super(firstOperand, secondOperand, new ScalingOperation<O>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
