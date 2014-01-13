package uk.co.strangeskies.gears.utilities.functions;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public class CloneFunction<T extends Cloneable> implements Function<T, T> {
	@SuppressWarnings("unchecked")
	@Override
	public T apply(T input) {
		try {
			return (T) input.getClass().getMethod("clone").invoke(input);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
