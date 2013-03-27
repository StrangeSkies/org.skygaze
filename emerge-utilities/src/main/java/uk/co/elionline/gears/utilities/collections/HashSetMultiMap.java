package uk.co.elionline.gears.utilities.collections;

import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.gears.utilities.Factory;

public class HashSetMultiMap<K, V> extends HashMultiMap<K, V, Set<V>> implements
		SetMultiMap<K, V> {
	private static final long serialVersionUID = 1L;

	public HashSetMultiMap() {
		super(new Factory<Set<V>>() {
			@Override
			public Set<V> create() {
				return new HashSet<V>();
			}
		});
	}
}
