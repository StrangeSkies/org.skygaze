package uk.co.elionline.emerge.mathematics.expressions;

import uk.co.elionline.emerge.mathematics.functions.Function;
import uk.co.elionline.emerge.utilities.Self;

public class DecouplingExpressionEvaluationFunction<D extends Self<? extends D>>
		implements Function<D, DecouplingExpression<? extends D>> {
	@Override
	public D applyTo(DecouplingExpression<? extends D> input) {
		return input.getDecoupledValue();
	}
}
