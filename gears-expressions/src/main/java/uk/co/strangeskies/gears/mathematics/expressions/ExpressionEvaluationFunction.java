package uk.co.strangeskies.gears.mathematics.expressions;

import java.util.function.Function;

public class ExpressionEvaluationFunction<T> implements
		Function<Expression<? extends T>, T> {
	@Override
	public T apply(Expression<? extends T> input) {
		return input.getValue();
	}
}
