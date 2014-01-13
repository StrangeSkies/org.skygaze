package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.function.BiFunction;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.utilities.Property;

public class ExpressionResultSettingBuffer<F extends Expression<? extends U>, U, T extends Property<?, ? super U>>
		extends ExpressionBuffer<F, T> {
	public static <T extends Property<?, ? super U>, U> BiFunction<T, Expression<? extends U>, T> createOperation() {
		return new BiFunction<T, Expression<? extends U>, T>() {
			@Override
			public T apply(T firstOperand, Expression<? extends U> secondOperand) {
				firstOperand.set(secondOperand.getValue());
				return firstOperand;
			}
		};
	}

	public ExpressionResultSettingBuffer(T front, F back) {
		super(front, back, ExpressionResultSettingBuffer.<T, U> createOperation());
	}

	public ExpressionResultSettingBuffer(
			DoubleBuffer<? extends F, ? extends T> doubleBuffer) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				ExpressionResultSettingBuffer.<T, U> createOperation());
	}
}
