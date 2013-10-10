package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.List;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.ImmutableExpression;
import uk.co.strangeskies.gears.mathematics.functions.Function;

/**
 * A function which returns a ListTransformationView for a given List, which
 * will convert elements using Function passed to the constructor.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 * @param <F>
 */
public class ListExpressionTransformationViewFunction<T, F> implements
		Function<List<T>, Expression<? extends List<? extends F>>> {
	private final Expression<? extends Function<? extends T, ? super F>> function;

	public ListExpressionTransformationViewFunction(
			Expression<? extends Function<? extends T, ? super F>> function) {
		this.function = function;
	}

	public ListExpressionTransformationViewFunction(Function<T, F> function) {
		this.function = new ImmutableExpression<>(function);
	}

	@Override
	public final List<T> applyTo(Expression<? extends List<? extends F>> input) {
		return new ListExpressionTransformationView<T, F>(input, function);
	}

	public final Expression<? extends Function<? extends T, ? super F>> getFunction() {
		return function;
	}
}
