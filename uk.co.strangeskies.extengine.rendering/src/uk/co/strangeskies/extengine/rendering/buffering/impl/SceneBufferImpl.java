package uk.co.strangeskies.extengine.rendering.buffering.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;
import java.util.function.Function;

import uk.co.strangeskies.extengine.rendering.buffering.SceneBuffer;
import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.expression.buffer.DoubleBuffer;
import uk.co.strangeskies.mathematics.expression.buffer.ExpressionBuffer;
import uk.co.strangeskies.mathematics.expression.buffer.FunctionBuffer;
import uk.co.strangeskies.utilities.Copyable;
import uk.co.strangeskies.utilities.EqualityComparator;

public class SceneBufferImpl implements SceneBuffer {
	private final TreeSet<DoubleBuffer<?, ?, ?>> buffers;

	public SceneBufferImpl() {
		buffers = new TreeSet<>(EqualityComparator.identityComparator());
	}

	@Override
	public void registerBuffer(DoubleBuffer<?, ?, ?> buffer) {
		buffers.add(buffer);
	}

	@Override
	public void unregisterBuffer(DoubleBuffer<?, ?, ?> buffer) {
		buffers.remove(buffer);
	}

	@Override
	public <T extends Copyable<? extends T>> DoubleBuffer<?, T, T> buffer(T item) {
		DoubleBuffer<?, T, T> buffer = new FunctionBuffer<>(item, i -> i.copy());

		registerBuffer(buffer);

		return buffer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Cloneable> DoubleBuffer<?, T, T> buffer(T item) {
		DoubleBuffer<?, T, T> buffer;
		buffer = new FunctionBuffer<>(item, c -> {
			try {
				return (T) Object.class.getMethod("clone").invoke(c);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				throw new UnsupportedOperationException(e);
			}
		});

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <T, B> FunctionBuffer<T, B> buffer(T item, Function<T, B> function) {
		FunctionBuffer<T, B> buffer = new FunctionBuffer<>(item, function);

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <T> ExpressionBuffer<Expression<?, ? extends T>, T> bufferResult(Expression<?, ? extends T> expression) {
		ExpressionBuffer<Expression<?, ? extends T>, T> buffer = new ExpressionBuffer<>(expression,
				e -> e.decoupleValue());

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <F, T> ExpressionBuffer<Expression<?, ? extends F>, T> bufferResult(Expression<?, ? extends F> expression,
			Function<? super F, T> function) {
		ExpressionBuffer<Expression<?, ? extends F>, T> buffer = new ExpressionBuffer<>(expression,
				e -> function.apply(e.decoupleValue()));

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public void push() {
		for (DoubleBuffer<?, ?, ?> bufferedExpression : buffers) {
			bufferedExpression.push();
		}
	}
}
