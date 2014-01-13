package uk.co.strangeskies.gears.utilities.functions;

import java.util.function.Function;

public class IdentityFunction<T> implements Function<T, T> {
	@Override
	public final T apply(T input) {
		return input;
	}
}
