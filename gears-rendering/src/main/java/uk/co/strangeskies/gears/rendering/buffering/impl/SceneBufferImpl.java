package uk.co.strangeskies.gears.rendering.buffering.impl;

import java.util.TreeSet;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.ExpressionBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.OperationApplicationBuffer;
import uk.co.strangeskies.gears.rendering.buffering.SceneBuffer;
import uk.co.strangeskies.gears.utilities.Copyable;
import uk.co.strangeskies.gears.utilities.IdentityComparator;

public class SceneBufferImpl implements SceneBuffer {
	private final TreeSet<DoubleBuffer<?, ?>> buffers;

	public SceneBufferImpl() {
		buffers = new TreeSet<>(new IdentityComparator<>());
	}

	@Override
	public void registerBuffer(DoubleBuffer<?, ?> buffer) {
		buffers.add(buffer);
	}

	@Override
	public void unregisterBuffer(DoubleBuffer<?, ?> buffer) {
		buffers.remove(buffer);
	}

	@Override
	public <T extends Copyable<? extends T>> DoubleBuffer<T, T> buffer(T item) {
		DoubleBuffer<T, T> buffer = new OperationApplicationBuffer<>(item,
				i -> i.copy());

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <T extends Cloneable> DoubleBuffer<T, T> buffer(T item) {
		CloneBuffer<T> buffer = new CloneBuffer<>(item);

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <T, B> OperationApplicationBuffer<T, B> buffer(T item,
			Function<T, B> function) {
		OperationApplicationBuffer<T, B> buffer = new OperationApplicationBuffer<>(
				item, function);

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <T> ExpressionBuffer<Expression<? extends T>, T> bufferResult(
			Expression<? extends T> expression) {
		ExpressionBuffer<Expression<? extends T>, T> buffer = new ExpressionBuffer<>(
				expression, e -> e.decoupleValue());

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <F, T> ExpressionBuffer<Expression<? extends F>, T> bufferResult(
			Expression<? extends F> expression, Function<? super F, T> function) {
		ExpressionBuffer<Expression<? extends F>, T> buffer = new ExpressionBuffer<>(
				expression, e -> function.apply(e.decoupleValue()));

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public void push() {
		for (DoubleBuffer<?, ?> bufferedExpression : buffers) {
			bufferedExpression.push();
		}
	}
}
