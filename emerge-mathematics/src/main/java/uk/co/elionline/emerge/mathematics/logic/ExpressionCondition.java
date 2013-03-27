package uk.co.elionline.emerge.mathematics.logic;

import uk.co.elionline.emerge.mathematics.expressions.Expression;

public class ExpressionCondition implements Condition {
	private final Expression<? extends /*@ReadOnly*/BooleanValue> expression;

	public ExpressionCondition(Expression<? extends /*@ReadOnly*/BooleanValue> expression) {
		this.expression = expression;
	}

	@Override
	public final boolean isFulfilled() {
		return expression.getValue().getBooleanValue();
	}
}
