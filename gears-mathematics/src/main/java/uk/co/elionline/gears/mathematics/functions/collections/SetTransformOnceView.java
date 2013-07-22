package uk.co.elionline.gears.mathematics.functions.collections;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.utilities.IdentityComparator;

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
public class SetTransformOnceView<T, F> extends AbstractSet<T> {
	private final Collection<? extends F> backingCollection;
	private final Function<? extends T, ? super F> function;

	private final Map<F, T> transformations;

	public SetTransformOnceView(Collection<? extends F> backingCollection,
			final Function<? extends T, ? super F> function) {
		transformations = new TreeMap<>(new IdentityComparator<>());

		this.backingCollection = backingCollection;
		this.function = function;
	}

	public final Collection<? extends F> getBackingCollection() {
		return backingCollection;
	}

	public final Function<? extends T, ? super F> getFunction() {
		return function;
	}

	@Override
	public final int size() {
		return backingCollection.size();
	}

	@Override
	public Iterator<T> iterator() {
		final Iterator<? extends F> backingIterator = backingCollection.iterator();
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return backingIterator.hasNext();
			}

			@Override
			public T next() {
				F backingElement = backingIterator.next();
				T transformation = transformations.get(backingElement);
				if (transformation == null) {
					transformation = function.applyTo(backingElement);
					transformations.put(backingElement, transformation);

					if (transformations.keySet().size() > backingCollection.size() * 1.5) {
						transformations.keySet().retainAll(backingCollection);
					}
				}
				return transformation;
			}

			@Override
			public void remove() {
				backingIterator.remove();
			}
		};
	}
}
