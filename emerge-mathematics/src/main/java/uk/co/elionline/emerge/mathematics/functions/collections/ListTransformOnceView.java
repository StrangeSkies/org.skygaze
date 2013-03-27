package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uk.co.elionline.emerge.mathematics.functions.Function;
import uk.co.elionline.emerge.utilities.IdentityComparator;

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
public class ListTransformOnceView<T, F> extends AbstractList<T> {
	private final List<? extends F> backingList;
	private final Function<? extends T, ? super F> function;

	private final Map<F, T> transformations;

	public ListTransformOnceView(List<? extends F> backingList,
			final Function<? extends T, ? super F> function) {
		transformations = new TreeMap<>(new IdentityComparator<>());

		this.backingList = backingList;
		this.function = function;
	}

	@Override
	public final T get(int index) {
		F backingElement = backingList.get(index);
		T transformation = transformations.get(backingElement);
		if (transformation == null) {
			transformation = function.applyTo(backingElement);
			transformations.put(backingElement, transformation);

			if (transformations.keySet().size() > backingList.size() * 1.5) {
				transformations.keySet().retainAll(backingList);
			}
		}
		return transformation;
	}

	public final List<? extends F> getBackingList() {
		return backingList;
	}

	public final Function<? extends T, ? super F> getFunction() {
		return function;
	}

	@Override
	public final int size() {
		return backingList.size();
	}
}
