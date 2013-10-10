package uk.co.strangeskies.gears.mathematics.functions.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.utilities.Factory;

public class ListTransformationFunction<T, F> extends
		CollectionTransformationFunction<T, F, List<T>> {

	public ListTransformationFunction(Function<? extends T, ? super F> function) {
		super(function, new Factory<List<T>>() {
			@Override
			public List<T> create() {
				return new ArrayList<>();
			}
		});
	}

	public static <X, Y> List<X> applyTo(Collection<? extends Y> collection,
			Function<? extends X, ? super Y> function) {
		return new ListTransformationFunction<X, Y>(function).applyTo(collection);
	}
}
