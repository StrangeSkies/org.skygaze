package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.Function;

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
public class ListTransformationView<T, F> extends AbstractList<T> {
	private final List<? extends F> backingList;
	private final Function<? extends T, ? super F> function;

	public ListTransformationView(List<? extends F> backingList,
			Function<? extends T, ? super F> function) {
		this.backingList = backingList;
		this.function = function;
	}

	@Override
	public final T get(int index) {
		return function.applyTo(backingList.get(index));
	}

	public final List<F> getBackingList() {
		return Collections.unmodifiableList(backingList);
	}

	@Override
	public final int size() {
		return backingList.size();
	}
}
