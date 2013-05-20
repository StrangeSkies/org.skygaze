package uk.co.elionline.gears.utilities.flowcontrol;

import uk.co.elionline.gears.utilities.Factory;

public class FactoryFutureMap<K extends Factory<? extends V>, V> extends
		StoredFutureMap<K, V> {
	public FactoryFutureMap() {
		super(new Mapping<K, V>() {
			@Override
			public V prepare(K key) {
				return key.create();
			}
		});
	}
}
