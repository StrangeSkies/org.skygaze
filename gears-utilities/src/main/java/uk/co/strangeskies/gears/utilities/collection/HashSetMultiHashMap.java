package uk.co.strangeskies.gears.utilities.collection;

import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.gears.utilities.factory.Factory;

public class HashSetMultiHashMap<K, V> extends MultiHashMap<K, V, Set<V>>
		implements SetMultiMap<K, V> {
	private static final long serialVersionUID = 1L;

	public HashSetMultiHashMap() {
		super(new Factory<Set<V>>() {
			@Override
			public Set<V> create() {
				return new HashSet<V>();
			}
		});
	}
}
