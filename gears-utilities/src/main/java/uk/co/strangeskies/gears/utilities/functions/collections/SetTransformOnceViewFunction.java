package uk.co.strangeskies.gears.utilities.functions.collections;

import java.util.Collection;
import java.util.Set;
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
public class SetTransformOnceViewFunction<F, T> implements
		Function<Collection<? extends F>, Set<T>> {
	private final Function<? super F, ? extends T> function;

	public SetTransformOnceViewFunction(Function<? super F, ? extends T> function) {
		this.function = function;
	}

	@Override
	public final Set<T> apply(Collection<? extends F> input) {
		return new SetTransformOnceView<>(input, function);
	}

	public final Function<? super F, ? extends T> getFunction() {
		return function;
	}
}
