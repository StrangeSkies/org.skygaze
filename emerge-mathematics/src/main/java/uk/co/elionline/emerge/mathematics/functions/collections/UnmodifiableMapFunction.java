package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.Collections;
import java.util.Map;

import uk.co.elionline.emerge.mathematics.functions.Function;

public class UnmodifiableMapFunction<K, E> implements
		Function<Map<K, E>, Map<? extends K, ? extends E>> {
	public UnmodifiableMapFunction() {
	}

	@Override
	public final Map<K, E> applyTo(Map<? extends K, ? extends E> list) {
		return Collections.<K, E> unmodifiableMap(list);
	}
}
