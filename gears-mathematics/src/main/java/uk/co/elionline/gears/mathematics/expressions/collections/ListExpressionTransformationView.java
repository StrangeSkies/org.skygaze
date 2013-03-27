package uk.co.elionline.gears.mathematics.expressions.collections;

import java.util.AbstractList;
import java.util.List;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.expressions.ImmutableExpression;
import uk.co.elionline.gears.mathematics.functions.Function;

/**
 * A view of a list which will be automatically updated along with the original,
 * but who's elements will be a transformation of the original associated
 * elements by way of the function passed to the constructor. The implementation
 * employs lazy evaluation, so try to use get() as little as possible by reusing
 * the result.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 *          The type of the elements of this list.
 * @param <F>
 *          The type of the elements of the backing list.
 */
public class ListExpressionTransformationView<T, F> extends AbstractList<T> {
	private final Expression<? extends List<? extends F>> backingList;
	private final Expression<? extends Function<? extends T, ? super F>> function;

	public ListExpressionTransformationView(
			Expression<? extends List<? extends F>> backingList,
			Function<? extends T, ? super F> function) {
		this.backingList = backingList;
		this.function = new ImmutableExpression<Function<? extends T, ? super F>>(
				function);
	}

	public ListExpressionTransformationView(
			Expression<? extends List<? extends F>> backingList,
			Expression<? extends Function<? extends T, ? super F>> function) {
		this.backingList = backingList;
		this.function = function;
	}

	public ListExpressionTransformationView(List<? extends F> backingList,
			Function<? extends T, ? super F> function) {
		this.backingList = new ImmutableExpression<>(backingList);
		this.function = new ImmutableExpression<>(function);
	}

	public ListExpressionTransformationView(List<? extends F> backingList,
			Expression<? extends Function<? extends T, ? super F>> function) {
		this.backingList = new ImmutableExpression<>(backingList);
		this.function = function;
	}

	@Override
	public final T get(int index) {
		return function.getValue().applyTo(backingList.getValue().get(index));
	}

	public final List<F> getBackingList() {
		return new ListExpressionView<F>(backingList);
	}

	public final Expression<? extends Function<? extends T, ? super F>> getFunction() {
		return function;
	}

	@Override
	public final int size() {
		return backingList.getValue().size();
	}
}
