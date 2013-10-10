package uk.co.strangeskies.gears.mathematics.functions.collections;

import java.util.Collection;
import java.util.Set;

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
public class SetTransformOnceViewFunction<T, F> implements
		Function<Set<T>, Collection<? extends F>> {
	private final Function<? extends T, ? super F> function;

	public SetTransformOnceViewFunction(Function<? extends T, ? super F> function) {
		this.function = function;
	}

	@Override
	public final Set<T> applyTo(Collection<? extends F> input) {
		return new SetTransformOnceView<>(input, function);
	}

	public final Function<? extends T, ? super F> getFunction() {
		return function;
	}
}
