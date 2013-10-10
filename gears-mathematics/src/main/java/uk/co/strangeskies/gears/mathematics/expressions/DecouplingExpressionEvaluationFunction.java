package uk.co.strangeskies.gears.mathematics.expressions;

import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.utilities.Self;

public class DecouplingExpressionEvaluationFunction<D extends Self<? extends D>>
		implements Function<D, DecouplingExpression<? extends D>> {
	@Override
	public D applyTo(DecouplingExpression<? extends D> input) {
		return input.getDecoupledValue();
	}
}
