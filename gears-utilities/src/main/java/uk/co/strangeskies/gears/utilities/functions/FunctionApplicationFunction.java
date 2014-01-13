package uk.co.strangeskies.gears.utilities.functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionApplicationFunction<T, F> implements
		BiFunction<F, Function<? super F, ? extends T>, T> {
	@Override
	public T apply(F firstOperand, Function<? super F, ? extends T> secondOperand) {
		return secondOperand.apply(firstOperand);
	}
}
