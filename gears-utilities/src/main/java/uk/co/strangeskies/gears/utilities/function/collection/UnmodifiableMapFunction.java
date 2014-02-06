package uk.co.strangeskies.gears.utilities.function.collection;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class UnmodifiableMapFunction<K, E> implements
		Function<Map<? extends K, ? extends E>, Map<K, E>> {
	public UnmodifiableMapFunction() {
	}

	@Override
	public final Map<K, E> apply(Map<? extends K, ? extends E> list) {
		return Collections.<K, E> unmodifiableMap(list);
	}
}
