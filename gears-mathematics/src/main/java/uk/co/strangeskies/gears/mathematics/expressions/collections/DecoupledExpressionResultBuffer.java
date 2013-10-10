package uk.co.strangeskies.gears.mathematics.expressions.collections;

import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.utilities.Self;

public class DecoupledExpressionResultBuffer<T extends Self<? extends T>>
		extends ExpressionBuffer<T, DecouplingExpression<? extends T>> {
	public static <T> BinaryOperation<T, T, DecouplingExpression<? extends T>> createOperation() {
		return new BinaryOperation<T, T, DecouplingExpression<? extends T>>() {
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
			DoubleBuffer<? extends T, ? extends DecouplingExpression<? extends T>> doubleBuffer) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				DecoupledExpressionResultBuffer.<T> createOperation());
	}
}
