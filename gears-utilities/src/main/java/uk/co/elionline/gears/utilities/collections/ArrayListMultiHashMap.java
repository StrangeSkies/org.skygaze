package uk.co.elionline.gears.utilities.collections;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.gears.utilities.Factory;

public class ArrayListMultiHashMap<K, V> extends MultiHashMap<K, V, List<V>>
		implements ListMultiMap<K, V> {
	private static final long serialVersionUID = 1L;

	public ArrayListMultiHashMap() {
		super(new Factory<List<V>>() {
			@Override
			public List<V> create() {
				return new ArrayList<V>();
			}
		});
	}
}
