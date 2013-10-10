package uk.co.strangeskies.gears.mathematics.expressions;

import uk.co.strangeskies.gears.mathematics.functions.Function;

public class ExpressionEvaluationFunction<T> implements
		Function<T, Expression<? extends T>> {
	@Override
	public T applyTo(Expression<? extends T> input) {
		return input.getValue();
	}
}
