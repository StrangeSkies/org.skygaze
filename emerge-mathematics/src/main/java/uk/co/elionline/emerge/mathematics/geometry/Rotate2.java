package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.values.Value;

public class Rotate2<O> extends
		BinaryOperationExpression<O, Rotatable2<? extends O>, Value<?>> {
	public Rotate2(Expression<? extends Rotatable2<? extends O>> firstOperand,
			Expression<? extends Value<?>> secondOperand) {
		super(firstOperand, secondOperand, new Rotation2Operation<O>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
