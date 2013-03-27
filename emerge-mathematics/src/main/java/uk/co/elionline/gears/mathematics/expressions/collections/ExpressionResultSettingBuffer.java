package uk.co.elionline.gears.mathematics.expressions.collections;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.functions.BinaryOperation;
import uk.co.elionline.gears.utilities.Property;

public class ExpressionResultSettingBuffer<T extends Property<?, ? super U>, F extends Expression<? extends U>, U>
		extends ExpressionBuffer<T, F> {
	public static <T extends Property<?, ? super U>, U> BinaryOperation<T, T, Expression<? extends U>> createOperation() {
		return new BinaryOperation<T, T, Expression<? extends U>>() {
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
			DoubleBuffer<? extends T, ? extends F> doubleBuffer) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				ExpressionResultSettingBuffer.<T, U> createOperation());
	}
}
