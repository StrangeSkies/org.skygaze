package uk.co.elionline.emerge.mathematics.expressions;

import uk.co.elionline.emerge.mathematics.functions.Function;

public class ExpressionEvaluationFunction<T> implements
		Function<T, Expression<? extends T>> {
	@Override
	public T applyTo(Expression<? extends T> input) {
		return input.getValue();
	}
}
