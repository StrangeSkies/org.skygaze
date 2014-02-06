package uk.co.strangeskies.gears.utilities.function.collection;

import java.util.List;
import java.util.function.Function;

public class SubListFunction<E> implements Function<List<E>, List<E>> {
	private int fromIndex;
	private int toIndex;

	public SubListFunction(int fromIndex, int toIndex) {
		this.fromIndex = fromIndex;
		this.toIndex = toIndex;
	}

	@Override
	public final List<E> apply(List<E> to) {
		return to.subList(fromIndex, toIndex);
	}
}
