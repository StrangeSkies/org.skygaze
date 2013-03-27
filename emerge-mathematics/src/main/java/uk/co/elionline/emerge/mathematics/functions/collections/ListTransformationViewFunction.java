package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.Function;

/**
 * A function which returns a ListTransformationView for a given List, which
 * will convert elements using Function passed to the constructor.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 * @param <F>
 */
public class ListTransformationViewFunction<T, F> implements
		Function<List<T>, List<F>> {
	private final Function<? extends T, ? super F> function;

	public ListTransformationViewFunction(Function<T, F> function) {
		this.function = function;
	}

	@Override
	public final List<T> applyTo(List<F> input) {
		return new ListTransformationView<T, F>(input, function);
	}

	public final Function<? extends T, ? super F> getFunction() {
		return function;
	}
}
