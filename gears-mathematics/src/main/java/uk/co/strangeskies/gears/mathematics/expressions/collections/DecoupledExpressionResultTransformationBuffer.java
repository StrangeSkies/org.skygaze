package uk.co.strangeskies.gears.mathematics.expressions.collections;

import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.utilities.Self;

public class DecoupledExpressionResultTransformationBuffer<T, F extends Self<? extends F>>
		extends ExpressionBuffer<T, DecouplingExpression<? extends F>> {
	public static <T, F> BinaryOperation<T, T, DecouplingExpression<? extends F>> createOperation(
			final Function<? extends T, ? super F> function) {
		return new BinaryOperation<T, T, DecouplingExpression<? extends F>>() {
			@Override
			public T apply(T firstOperand,
					DecouplingExpression<? extends F> secondOperand) {
				return function.applyTo(secondOperand.getDecoupledValue());
			}
		};
	}

	public DecoupledExpressionResultTransformationBuffer(T front,
			DecouplingExpression<? extends F> back,
			Function<? extends T, ? super F> function) {
		super(front, back, DecoupledExpressionResultTransformationBuffer
				.<T, F> createOperation(function));
	}

	public DecoupledExpressionResultTransformationBuffer(
			DecouplingExpression<? extends F> back,
			Function<? extends T, ? super F> function) {
		super(null, back, DecoupledExpressionResultTransformationBuffer
				.<T, F> createOperation(function));
	}

	public DecoupledExpressionResultTransformationBuffer(
			DoubleBuffer<? extends T, ? extends DecouplingExpression<? extends F>> doubleBuffer,
			Function<? extends T, ? super F> function) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				DecoupledExpressionResultTransformationBuffer
						.<T, F> createOperation(function));
	}
}
