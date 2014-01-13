package uk.co.strangeskies.gears.mathematics.expressions;

import java.util.function.Function;

public class ConditionalExpression<O> extends
		UnaryOperationExpression</* @ReadOnly */Boolean, O> {

	public ConditionalExpression(
			Expression<? extends /* @ReadOnly */Boolean> condition,
			final O valueWhenFulfilled, final O valueWhenUnfulfilled) {
		super(condition, new Function<Boolean, O>() {
			@Override
			public O apply(Boolean t) {
				return t ? valueWhenFulfilled : valueWhenUnfulfilled;
			}
		});
	}

	public final Expression<? extends /* @ReadOnly */Boolean> getCondition() {
		return getOperand();
	}
}
