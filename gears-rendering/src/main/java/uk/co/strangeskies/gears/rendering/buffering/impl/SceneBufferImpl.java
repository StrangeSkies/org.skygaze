package uk.co.strangeskies.gears.rendering.buffering.impl;

import java.util.TreeSet;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.expressions.collections.CloneBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.CopyBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DecoupledExpressionResultBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DecoupledExpressionResultTransformationBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.ExpressionResultBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.ExpressionResultTransformationBuffer;
import uk.co.strangeskies.gears.mathematics.expressions.collections.OperationApplicationBuffer;
import uk.co.strangeskies.gears.rendering.buffering.SceneBuffer;
import uk.co.strangeskies.gears.utilities.Copyable;
import uk.co.strangeskies.gears.utilities.IdentityComparator;
import uk.co.strangeskies.gears.utilities.Self;

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
		CopyBuffer<T> buffer = new CopyBuffer<>(item);

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
	public <T> ExpressionResultBuffer<T> bufferResult(
			Expression<? extends T> expression) {
		ExpressionResultBuffer<T> buffer = new ExpressionResultBuffer<>(expression);

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <F, T> ExpressionResultTransformationBuffer<F, T> bufferResult(
			Expression<? extends F> expression, Function<? super F, T> function) {
		ExpressionResultTransformationBuffer<F, T> buffer = new ExpressionResultTransformationBuffer<>(
				expression, function);

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <T extends Self<? extends T>> DecoupledExpressionResultBuffer<T> bufferDecoupledResult(
			DecouplingExpression<? extends T> expression) {
		DecoupledExpressionResultBuffer<T> buffer = new DecoupledExpressionResultBuffer<>(
				expression);

		registerBuffer(buffer);

		return buffer;
	}

	@Override
	public <F extends Self<? extends F>, T> DecoupledExpressionResultTransformationBuffer<F, T> bufferDecoupledResult(
			DecouplingExpression<? extends F> expression,
			Function<? super F, T> function) {
		DecoupledExpressionResultTransformationBuffer<F, T> buffer = new DecoupledExpressionResultTransformationBuffer<>(
				expression, function);

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
