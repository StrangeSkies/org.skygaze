package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.function.BiFunction;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.utilities.Self;

public class DecoupledExpressionResultTransformationBuffer<F extends Self<? extends F>, T>
		extends ExpressionBuffer<DecouplingExpression<? extends F>, T> {
	public static <T, F> BiFunction<T, DecouplingExpression<? extends F>, T> createOperation(
			final Function<? super F, ? extends T> function) {
		return new BiFunction<T, DecouplingExpression<? extends F>, T>() {
			@Override
			public T apply(T firstOperand,
					DecouplingExpression<? extends F> secondOperand) {
				return function.apply(secondOperand.getDecoupledValue());
			}
		};
	}

	public DecoupledExpressionResultTransformationBuffer(T front,
			DecouplingExpression<? extends F> back,
			Function<? super F, ? extends T> function) {
		super(front, back, DecoupledExpressionResultTransformationBuffer
				.<T, F> createOperation(function));
	}

	public DecoupledExpressionResultTransformationBuffer(
			DecouplingExpression<? extends F> back,
			Function<? super F, ? extends T> function) {
		super(null, back, DecoupledExpressionResultTransformationBuffer
				.<T, F> createOperation(function));
	}

	public DecoupledExpressionResultTransformationBuffer(
			DoubleBuffer<? extends DecouplingExpression<? extends F>, ? extends T> doubleBuffer,
			Function<? super F, ? extends T> function) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				DecoupledExpressionResultTransformationBuffer
						.<T, F> createOperation(function));
	}
}
