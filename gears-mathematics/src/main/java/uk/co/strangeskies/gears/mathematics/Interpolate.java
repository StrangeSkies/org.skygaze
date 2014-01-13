package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.TrinaryOperationExpression;
import uk.co.strangeskies.gears.mathematics.values.Value;

public class Interpolate<T, I> extends
		TrinaryOperationExpression<T, T, Value<?>, I> {
	public Interpolate(Expression<? extends T> from, Expression<? extends T> to,
			Expression<? extends Value<?>> delta,
			InterpolationOperation<? super T, ? extends I> interpolation) {
		super(from, to, delta, interpolation);
	}
}
