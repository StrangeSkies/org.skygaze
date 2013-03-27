package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.expressions.TrinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.values.Value;

public class Interpolate<I, T> extends
		TrinaryOperationExpression<I, T, T, Value<?>> {
	public Interpolate(Expression<? extends T> from, Expression<? extends T> to,
			Expression<? extends Value<?>> delta,
			InterpolationOperation<? extends I, ? super T> interpolation) {
		super(from, to, delta, interpolation);
	}
}
