package uk.co.strangeskies.gears.utilities.functions;

import java.util.function.Function;

import uk.co.strangeskies.gears.utilities.Copyable;

public class CopyFunction<C extends Copyable<? extends T>, T extends Copyable<? extends T>>
		implements Function<C, T> {
	@Override
	public T apply(C input) {
		T copy = input.copy();

		return copy;
	}
}
