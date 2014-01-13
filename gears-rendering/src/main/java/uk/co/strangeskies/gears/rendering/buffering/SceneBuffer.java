package uk.co.strangeskies.gears.rendering.buffering;

import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DecoupledExpressionResultBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DecoupledExpressionResultTransformationBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.ExpressionResultBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.ExpressionResultTransformationBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.OperationApplicationBuffer;
import uk.co.strangeskies.gears.utilities.Copyable;
import uk.co.strangeskies.gears.utilities.Self;

public interface SceneBuffer {
	public void push();

	public void registerBuffer(DoubleBuffer<?, ?> buffer);

	public void unregisterBuffer(DoubleBuffer<?, ?> buffer);

	public <T extends Copyable<? extends T>> DoubleBuffer<T, T> buffer(T item);

	public <T extends Cloneable> DoubleBuffer<T, T> buffer(T item);

	public <T, B> OperationApplicationBuffer<T, B> buffer(T item,
			Function<T, B> function);

	public <T> ExpressionResultBuffer<T> bufferResult(
			Expression<? extends T> expression);

	public <F, T> ExpressionResultTransformationBuffer<F, T> bufferResult(
			Expression<? extends F> expression, Function<? super F, T> function);

	public <T extends Self<? extends T>> DecoupledExpressionResultBuffer<T> bufferDecoupledResult(
			DecouplingExpression<? extends T> expression);

	public <F extends Self<? extends F>, T> DecoupledExpressionResultTransformationBuffer<F, T> bufferDecoupledResult(
			DecouplingExpression<? extends F> expression,
			Function<? super F, T> function);
}
