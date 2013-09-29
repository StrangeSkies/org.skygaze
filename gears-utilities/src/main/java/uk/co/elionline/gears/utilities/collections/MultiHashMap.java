package uk.co.elionline.gears.utilities.collections;

import java.util.Collection;
import java.util.HashMap;

import uk.co.elionline.gears.utilities.Factory;

public class MultiHashMap<K, V, C extends Collection<V>> extends HashMap<K, C>
		implements MultiMap<K, V, C> {
	private static final long serialVersionUID = 1L;

	private final Factory<C> collectionFactory;

	public MultiHashMap(Factory<C> collectionFactory) {
		this.collectionFactory = collectionFactory;
	}

	@Override
	public boolean add(K key, V value) {
		C values = super.get(key);

		if (values == null) {
			values = collectionFactory.create();
			put(key, values);
		}

		return values.add(value);
	}

	@Override
	public boolean addAll(K key, Collection<? extends V> values) {
		C currentValues = super.get(key);

		if (currentValues == null) {
			currentValues = collectionFactory.create();
			put(key, currentValues);
		}

		return currentValues.addAll(values);
	}

	@Override
	public boolean addToAll(V value) {
		return addToAll(keySet(), value);
	}

	@Override
	public boolean addAllToAll(Collection<? extends V> values) {
		return addAllToAll(keySet(), values);
	}

	@Override
	public boolean addToAll(Collection<? extends K> keys, V value) {
		boolean added = false;

		for (K key : keys) {
			added = add(key, value) || added;
		}

		return added;
	}

	@Override
	public boolean addAllToAll(Collection<? extends K> keys,
			Collection<? extends V> values) {
		boolean added = false;

		for (K key : keys) {
			added = addAll(key, values) || added;
		}

		return added;
	}

	@Override
	public boolean remove(K key, V value) {
		C values = super.get(key);

		boolean removed = values != null && values.remove(value);

		if (removed && values.isEmpty()) {
			remove(key);
		}

		return removed;
	}

	@Override
	public boolean removeAll(K key, Collection<? extends V> values) {
		C currentValues = super.get(key);

		boolean removed = currentValues != null && currentValues.removeAll(values);

		if (removed && currentValues.isEmpty()) {
			remove(key);
		}

		return removed;
	}

	@Override
	public boolean removeFromAll(V value) {
		return removeFromAll(keySet(), value);
	}

	@Override
	public boolean removeAllFromAll(Collection<? extends V> values) {
		return removeAllFromAll(keySet(), values);
	}

	@Override
	public boolean removeFromAll(Collection<? extends K> keys, V value) {
		boolean removed = false;

		for (K key : keys) {
			removed = remove(key, value) || removed;
		}

		return removed;
	}

	@Override
	public boolean removeAllFromAll(Collection<? extends K> keys,
			Collection<? extends V> values) {
		boolean removed = false;

		for (K key : keys) {
			removed = removeAll(key, values) || removed;
		}

		return removed;
	}

	@Override
	public boolean contains(K key, V value) {
		C values = super.get(key);
		return values != null && values.contains(value);
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
