package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.values.Value;

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
