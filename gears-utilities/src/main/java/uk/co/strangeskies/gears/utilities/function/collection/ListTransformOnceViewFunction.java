package uk.co.strangeskies.gears.utilities.function.collection;

import java.util.List;
import java.util.function.Function;

/**
 * A function which returns a ListTransformationView for a given List, which
 * will convert elements using Function passed to the constructor.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 * @param <F>
 */
public class ListTransformOnceViewFunction<T, F> implements
		Function<List<? extends F>, List<T>> {
	private final Function<? super F, ? extends T> function;

	public ListTransformOnceViewFunction(Function<? super F, ? extends T> function) {
		this.function = function;
	}

	@Override
	public final List<T> apply(List<? extends F> input) {
		return new ListTransformOnceView<>(input, function);
	}

	public final Function<? super F, ? extends T> getFunction() {
		return function;
	}
}
