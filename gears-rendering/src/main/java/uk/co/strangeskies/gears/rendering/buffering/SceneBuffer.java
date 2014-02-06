package uk.co.strangeskies.gears.rendering.buffering;

import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.ExpressionBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.OperationApplicationBuffer;
import uk.co.strangeskies.gears.utilities.Copyable;

public interface SceneBuffer {
	public void push();

	public void registerBuffer(DoubleBuffer<?, ?> buffer);

	public void unregisterBuffer(DoubleBuffer<?, ?> buffer);

	public <T extends Copyable<? extends T>> DoubleBuffer<T, T> buffer(T item);

	public <T extends Cloneable> DoubleBuffer<T, T> buffer(T item);

	public <T, B> OperationApplicationBuffer<T, B> buffer(T item,
			Function<T, B> function);

	public <T> ExpressionBuffer<Expression<? extends T>, T> bufferResult(
			Expression<? extends T> expression);

	public <F, T> ExpressionBuffer<Expression<? extends F>, T> bufferResult(
			Expression<? extends F> expression, Function<? super F, T> function);
}
