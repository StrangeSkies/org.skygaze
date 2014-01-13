package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.List;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.ImmutableExpression;

/**
 * A function which returns a ListTransformationView for a given List, which
 * will convert elements using Function passed to the constructor.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 * @param <F>
 */
public class ListExpressionTransformationViewFunction<F, T> implements
		Function<Expression<? extends List<? extends F>>, List<T>> {
	private final Expression<? extends Function<? super F, ? extends T>> function;

	public ListExpressionTransformationViewFunction(
			Expression<? extends Function<? super F, ? extends T>> function) {
		this.function = function;
	}

	public ListExpressionTransformationViewFunction(Function<F, T> function) {
		this.function = new ImmutableExpression<>(function);
	}

	@Override
	public final List<T> apply(Expression<? extends List<? extends F>> input) {
		return new ListExpressionTransformationView<F, T>(input, function);
	}

	public final Expression<? extends Function<? super F, ? extends T>> getFunction() {
		return function;
	}
}
