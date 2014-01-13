package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.function.BiFunction;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public class ExpressionResultBuffer<T> extends
		ExpressionBuffer<Expression<? extends T>, T> {
	public static <T> BiFunction<T, Expression<? extends T>, T> createOperation() {
		return new BiFunction<T, Expression<? extends T>, T>() {
			@Override
			public T apply(T firstOperand, Expression<? extends T> secondOperand) {
				return secondOperand.getValue();
			}
		};
	}

	public ExpressionResultBuffer(T front, Expression<? extends T> back) {
		super(front, back, ExpressionResultBuffer.<T> createOperation());
	}

	public ExpressionResultBuffer(Expression<? extends T> back) {
		super(null, back, ExpressionResultBuffer.<T> createOperation());
	}

	public ExpressionResultBuffer(
			DoubleBuffer<? extends Expression<? extends T>, ? extends T> doubleBuffer) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				ExpressionResultBuffer.<T> createOperation());
	}
}
