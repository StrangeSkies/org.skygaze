package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.TrinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.values.Value;

public class Interpolate<I, T> extends
		TrinaryOperationExpression<I, T, T, Value<?>> {
	public Interpolate(Expression<? extends T> from, Expression<? extends T> to,
			Expression<? extends Value<?>> delta,
			InterpolationOperation<? extends I, ? super T> interpolation) {
		super(from, to, delta, interpolation);
	}
}
