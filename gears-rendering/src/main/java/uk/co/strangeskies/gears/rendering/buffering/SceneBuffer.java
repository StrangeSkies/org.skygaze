package uk.co.strangeskies.gears.rendering.buffering;

import java.util.function.Function;

import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.expression.buffer.DoubleBuffer;
import uk.co.strangeskies.mathematics.expression.buffer.ExpressionBuffer;
import uk.co.strangeskies.mathematics.expression.buffer.FunctionBuffer;
import uk.co.strangeskies.utilities.Copyable;

public interface SceneBuffer {
	public void push();

	public void registerBuffer(DoubleBuffer<?, ?> buffer);

	public void unregisterBuffer(DoubleBuffer<?, ?> buffer);

	public <T extends Copyable<? extends T>> DoubleBuffer<T, T> buffer(T item);

	public <T extends Cloneable> DoubleBuffer<T, T> buffer(T item);

	public <T, B> FunctionBuffer<T, B> buffer(T item, Function<T, B> function);

	public <T> ExpressionBuffer<Expression<? extends T>, T> bufferResult(
			Expression<? extends T> expression);

	public <F, T> ExpressionBuffer<Expression<? extends F>, T> bufferResult(
			Expression<? extends F> expression, Function<? super F, T> function);
}
