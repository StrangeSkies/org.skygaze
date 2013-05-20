package uk.co.elionline.gears.utilities.flowcontrol;

import java.util.HashMap;

public class FutureMap<K, V> {
	private final HashMap<K, Thread> preparationThreads;
	private final Mapping<K, V> mapping;

	public FutureMap(Mapping<K, V> mapping) {
		preparationThreads = new HashMap<>();
		this.mapping = mapping;
	}

	public void prepare(final K key) {
		synchronized (preparationThreads) {
			Thread thread = new Thread() {
				@Override
				public void run() {
					mapping.prepare(key);
				}
			};
			thread.start();
			preparationThreads.put(key, thread);
			preparationThreads.notify();
		}
	}

	public V get(K key) {
		Thread thread;
		synchronized (preparationThreads) {
			while (!preparationThreads.containsKey(key)) {
				try {
					preparationThreads.wait();
				} catch (InterruptedException e) {
				}
			}
			thread = preparationThreads.get(key);
		}
		while (true) {
			try {
				thread.join();
				break;
			} catch (InterruptedException e) {
			}
		}

		return get(key);
	}

	public void reset() {
		synchronized (preparationThreads) {
			for (K key : preparationThreads.keySet()) {
				get(key);
			}
		}
	}

	public interface Mapping<K, V> {
		public void prepare(K key);

		public V get(K key);
	}
}
