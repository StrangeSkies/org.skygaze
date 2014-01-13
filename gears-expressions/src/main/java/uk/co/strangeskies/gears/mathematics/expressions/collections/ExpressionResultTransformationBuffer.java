package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.function.BiFunction;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class ExpressionResultTransformationBuffer<F, T> extends
		ExpressionBuffer<Expression<? extends F>, T> {
	public static <T, F> BiFunction<T, Expression<? extends F>, T> createOperation(
			final Function<? super F, ? extends T> function) {
		return new BiFunction<T, Expression<? extends F>, T>() {
			@Override
			public T apply(T firstOperand, Expression<? extends F> secondOperand) {
				return function.apply(secondOperand.getValue());
			}
		};
	}

	public ExpressionResultTransformationBuffer(T front,
			Expression<? extends F> back, Function<? super F, ? extends T> function) {
		super(front, back, ExpressionResultTransformationBuffer
				.<T, F> createOperation(function));
	}

	public ExpressionResultTransformationBuffer(Expression<? extends F> back,
			Function<? super F, ? extends T> function) {
		super(null, back, ExpressionResultTransformationBuffer
				.<T, F> createOperation(function));
	}

	public ExpressionResultTransformationBuffer(
			DoubleBuffer<? extends Expression<? extends F>, ? extends T> doubleBuffer,
			Function<? super F, ? extends T> function) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				ExpressionResultTransformationBuffer.<T, F> createOperation(function));
	}
}
