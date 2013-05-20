package uk.co.elionline.gears.utilities.collections;

import java.util.AbstractList;
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

	@Override
	public List<V> get(final Object key) {
		return new AbstractList<V>() {
			@Override
			public V get(int index) {
				List<V> backingList = ArrayListMultiHashMap.this.superGet(key);
				if (backingList == null) {
					throw new ArrayIndexOutOfBoundsException();
				}
				return backingList.get(index);
			}

			@Override
			public V set(int index, V element) {
				List<V> backingList = ArrayListMultiHashMap.this.superGet(key);
				if (backingList == null) {
					throw new ArrayIndexOutOfBoundsException();
				}
				return backingList.set(index, element);
			}

			@SuppressWarnings("unchecked")
			@Override
			public void add(int index, V element) {
				List<V> backingList = ArrayListMultiHashMap.this.superGet(key);
				if (backingList == null) {
					if (index == 0) {
						ArrayListMultiHashMap.this.add((K) key, element);
					} else {
						throw new ArrayIndexOutOfBoundsException();
					}
				}
				backingList.add(index, element);
			}

			@Override
			public V remove(int index) {
				List<V> backingList = ArrayListMultiHashMap.this.superGet(key);
				if (backingList == null) {
					throw new ArrayIndexOutOfBoundsException();
				}
				return backingList.remove(index);
			}

			@Override
			public int size() {
				List<V> backingList = ArrayListMultiHashMap.this.superGet(key);
				if (backingList == null) {
					return 0;
				}
				return backingList.size();
			}
		};
	}

	protected List<V> superGet(Object key) {
		return super.get(key);
	}
}
