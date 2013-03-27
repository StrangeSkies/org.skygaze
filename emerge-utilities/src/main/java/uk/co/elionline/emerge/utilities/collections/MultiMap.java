package uk.co.elionline.emerge.utilities.collections;

import java.util.Collection;
import java.util.Map;

public interface MultiMap<K, V, C extends Collection<V>> extends Map<K, C> {
	public boolean add(K key, V value);

	public boolean addAll(K key, Collection<? extends V> values);

	public boolean addToAll(V value);

	public boolean addAllToAll(Collection<? extends V> values);

	public boolean remove(K key, V value);

	public boolean removeAll(K key, Collection<? extends V> values);

	public boolean removeFromAll(V value);

	public boolean removeAllFromAll(Collection<? extends V> values);

	public boolean contains(K key, V value);

	public C getAllValues();
}
