package uk.co.elionline.gears.utilities.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import uk.co.elionline.gears.utilities.Factory;

public class HashMultiMap<K, V, C extends Collection<V>> extends HashMap<K, C>
		implements MultiMap<K, V, C> {
	private static final long serialVersionUID = 1L;

	private final Factory<C> collectionFactory;

	HashMultiMap(Factory<C> collectionFactory) {
		this.collectionFactory = collectionFactory;
	}

	@Override
	public boolean add(K key, V value) {
		C values = get(key);

		if (values == null) {
			values = collectionFactory.create();
			put(key, values);
		}

		return values.add(value);
	}

	@Override
	public boolean addAll(K key, Collection<? extends V> values) {
		C currentValues = get(key);

		if (currentValues == null) {
			currentValues = collectionFactory.create();
			put(key, currentValues);
		}

		return currentValues.addAll(values);
	}

	@Override
	public boolean addToAll(V value) {
		boolean added = false;

		for (K key : keySet()) {
			added = add(key, value) || added;
		}

		return added;
	}

	@Override
	public boolean addAllToAll(Collection<? extends V> values) {
		boolean added = false;

		for (K key : keySet()) {
			added = addAll(key, values) || added;
		}

		return added;
	}

	@Override
	public boolean remove(K key, V value) {
		C values = get(key);

		boolean removed = values != null && values.remove(value);

		if (removed && values.isEmpty()) {
			remove(key);
		}

		return removed;
	}

	@Override
	public boolean removeAll(K key, Collection<? extends V> values) {
		C currentValues = get(key);

		boolean removed = currentValues != null && currentValues.removeAll(values);

		if (removed && currentValues.isEmpty()) {
			remove(key);
		}

		return removed;
	}

	@Override
	public boolean removeFromAll(V value) {
		Iterator<C> valueIterator = values().iterator();

		boolean removed = false;

		while (valueIterator.hasNext()) {
			C values = valueIterator.next();

			removed = values.remove(value) || removed;

			if (values.isEmpty()) {
				valueIterator.remove();
			}
		}

		return removed;
	}

	@Override
	public boolean removeAllFromAll(Collection<? extends V> values) {
		Iterator<C> valueIterator = values().iterator();

		boolean removed = false;

		while (valueIterator.hasNext()) {
			C currentValues = valueIterator.next();

			removed = currentValues.removeAll(values) || removed;

			if (currentValues.isEmpty()) {
				valueIterator.remove();
			}
		}

		return removed;
	}

	@Override
	public boolean contains(K key, V value) {
		C values = get(key);
		return values != null && get(key).contains(value);
	}

	@Override
	public C getAllValues() {
		C allValues = collectionFactory.create();

		for (C values : values()) {
			allValues.addAll(values);
		}

		return allValues;
	}
}
