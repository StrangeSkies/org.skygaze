package uk.co.strangeskies.gears.mathematics.functions.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.utilities.Factory;

public class SetTransformationFunction<T, F> extends
		CollectionTransformationFunction<T, F, Set<T>> {

	public SetTransformationFunction(Function<? extends T, ? super F> function) {
		super(function, new Factory<Set<T>>() {
			@Override
			public Set<T> create() {
				return new HashSet<>();
			}
		});
	}

	public static <X, Y> Set<X> applyTo(Collection<? extends Y> collection,
			Function<? extends X, ? super Y> function) {
		return new SetTransformationFunction<X, Y>(function).applyTo(collection);
	}
}
