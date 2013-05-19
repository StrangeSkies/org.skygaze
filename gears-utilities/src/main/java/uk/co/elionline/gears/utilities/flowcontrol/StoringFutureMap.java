package uk.co.elionline.gears.utilities.flowcontrol;

import java.util.HashMap;

public class StoringFutureMap<K, V> extends FutureMap<K, V> {
	private final HashMap<K, V> results;

	public StoringFutureMap(final Mapping<K, V> mapping) {
		super(new FutureMap.Mapping<K, V>() {
			@Override
			public void prepare(K key) {
				results.put(key, mapping.prepare(key));
			}

			@Override
			public V get(K key) {
				return results.get(key);
			}
		});
		results = new HashMap<>();
	}

	public interface Mapping<K, V> {
		public V prepare(K key);
	}
}
