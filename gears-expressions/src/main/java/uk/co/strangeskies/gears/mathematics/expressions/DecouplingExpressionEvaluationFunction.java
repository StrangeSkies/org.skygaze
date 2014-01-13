package uk.co.strangeskies.gears.mathematics.expressions;

import java.util.function.Function;

import uk.co.strangeskies.gears.utilities.Self;

public class DecouplingExpressionEvaluationFunction<D extends Self<? extends D>>
		implements Function<DecouplingExpression<? extends D>, D> {
	@Override
	public D apply(DecouplingExpression<? extends D> input) {
		return input.getDecoupledValue();
	}
}
