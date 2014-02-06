package uk.co.strangeskies.gears.utilities.function.collection;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class UnmodifiableListFunction<E> implements
		Function<List<? extends E>, List<E>> {
	public UnmodifiableListFunction() {
	}

	@Override
	public final List<E> apply(List<? extends E> list) {
		return Collections.<E> unmodifiableList(list);
	}
}
