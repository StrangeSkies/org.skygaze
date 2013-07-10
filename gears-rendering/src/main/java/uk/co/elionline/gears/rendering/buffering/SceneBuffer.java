package uk.co.elionline.gears.rendering.buffering;

import uk.co.elionline.gears.mathematics.expressions.DecouplingExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.expressions.collections.DecoupledExpressionResultBuffer;
import uk.co.elionline.gears.mathematics.expressions.collections.DecoupledExpressionResultTransformationBuffer;
import uk.co.elionline.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.elionline.gears.mathematics.expressions.collections.ExpressionResultBuffer;
import uk.co.elionline.gears.mathematics.expressions.collections.ExpressionResultTransformationBuffer;
import uk.co.elionline.gears.mathematics.expressions.collections.OperationApplicationBuffer;
import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.utilities.Copyable;
import uk.co.elionline.gears.utilities.Self;

public interface SceneBuffer {
	public void flip();

	public void registerBuffer(DoubleBuffer<?, ?> buffer);

	public void unregisterBuffer(DoubleBuffer<?, ?> buffer);

	public <T extends Copyable<? extends T>> DoubleBuffer<T, T> buffer(T item);

	public <T extends Cloneable> DoubleBuffer<T, T> buffer(T item);

	public <B, T> OperationApplicationBuffer<B, T> buffer(T item,
			Function<B, T> function);

	public <T> ExpressionResultBuffer<T> bufferResult(
			Expression<? extends T> expression);

	public <T, F> ExpressionResultTransformationBuffer<T, F> bufferResult(
			Expression<? extends F> expression, Function<T, ? super F> function);

	public <T extends Self<? extends T>> DecoupledExpressionResultBuffer<T> bufferDecoupledResult(
			DecouplingExpression<? extends T> expression);

	public <T, F extends Self<? extends F>> DecoupledExpressionResultTransformationBuffer<T, F> bufferDecoupledResult(
			DecouplingExpression<? extends F> expression,
			Function<T, ? super F> function);
}
