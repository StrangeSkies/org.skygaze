package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.function.BiFunction;

import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.utilities.Self;

public class DecoupledExpressionResultBuffer<T extends Self<? extends T>>
		extends ExpressionBuffer<DecouplingExpression<? extends T>, T> {
	public static <T> BiFunction<T, DecouplingExpression<? extends T>, T> createOperation() {
		return new BiFunction<T, DecouplingExpression<? extends T>, T>() {
			@Override
			public T apply(T firstOperand,
					DecouplingExpression<? extends T> secondOperand) {
				return secondOperand.getDecoupledValue();
			}
		};
	}

	public DecoupledExpressionResultBuffer(T front,
			DecouplingExpression<? extends T> back) {
		super(front, back, DecoupledExpressionResultBuffer.<T> createOperation());
	}

	public DecoupledExpressionResultBuffer(DecouplingExpression<? extends T> back) {
		super(null, back, DecoupledExpressionResultBuffer.<T> createOperation());
	}

	public DecoupledExpressionResultBuffer(
			DoubleBuffer<? extends DecouplingExpression<? extends T>, ? extends T> doubleBuffer) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				DecoupledExpressionResultBuffer.<T> createOperation());
	}
}
