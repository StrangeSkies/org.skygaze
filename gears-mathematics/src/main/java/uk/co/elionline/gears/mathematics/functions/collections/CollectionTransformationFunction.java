package uk.co.elionline.gears.mathematics.functions.collections;

import java.util.Collection;

import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.utilities.Factory;

public class CollectionTransformationFunction<T, F, C extends Collection<T>>
		implements Function<C, Collection<? extends F>> {
	private final Function<? extends T, ? super F> function;
	private final Factory<C> collectionFactory;

	public CollectionTransformationFunction(
			Function<? extends T, ? super F> function, Factory<C> collectionFactory) {
		this.function = function;
		this.collectionFactory = collectionFactory;
	}

	@Override
	public final C applyTo(Collection<? extends F> input) {
		C collection = collectionFactory.create();
		for (F item : input) {
			collection.add(function.applyTo(item));
		}
		return collection;
	}

	public final Function<? extends T, ? super F> getFunction() {
		return function;
	}
}
