package uk.co.elionline.gears.mathematics.expressions;

import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.utilities.Self;

public class DecouplingExpressionEvaluationFunction<D extends Self<? extends D>>
		implements Function<D, DecouplingExpression<? extends D>> {
	@Override
	public D applyTo(DecouplingExpression<? extends D> input) {
		return input.getDecoupledValue();
	}
}
