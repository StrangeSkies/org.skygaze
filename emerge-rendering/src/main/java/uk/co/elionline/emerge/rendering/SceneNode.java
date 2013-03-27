package uk.co.elionline.emerge.rendering;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import uk.co.elionline.emerge.mathematics.Interpolate;
import uk.co.elionline.emerge.mathematics.InterpolationOperation;
import uk.co.elionline.emerge.mathematics.expressions.DecouplingExpression;
import uk.co.elionline.emerge.mathematics.expressions.DecouplingExpressionEvaluationFunction;
import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.expressions.collections.AssignmentBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.CloneBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.CopyBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.DecoupledExpressionResultBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.DecoupledExpressionResultTransformationBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.DoubleBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.ExpressionResultBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.ExpressionResultTransformationBuffer;
import uk.co.elionline.emerge.mathematics.expressions.collections.OperationApplicationBuffer;
import uk.co.elionline.emerge.mathematics.functions.CopyFunction;
import uk.co.elionline.emerge.mathematics.functions.Function;
import uk.co.elionline.emerge.mathematics.functions.FunctionComposition;
import uk.co.elionline.emerge.mathematics.values.DoubleValue;
import uk.co.elionline.emerge.utilities.Copyable;
import uk.co.elionline.emerge.utilities.IdentityComparator;
import uk.co.elionline.emerge.utilities.Self;

public class SceneNode<R extends Renderable> {
	private final DoubleBuffer<R, R> renderable;
	private final DoubleValue delta;

	private final TreeSet<DoubleBuffer<?, ?>> buffers;
	private final TreeMap<DoubleBuffer<?, ?>, List<DoubleBuffer<?, ?>>> interpolations;

	public SceneNode() {
		buffers = new TreeSet<>(new IdentityComparator<>());
		interpolations = new TreeMap<>(new IdentityComparator<>());

		renderable = new AssignmentBuffer<R>((R) null);
		registerBuffer(renderable);

		delta = new DoubleValue(0);
	}

	public R getRenderable() {
		return renderable.getBack();
	}

	public R getBufferedRenderable() {
		return renderable.getBack();
	}

	public void setRenderable(R renderable) {
		this.renderable.setBack(renderable);
	}

	public final DoubleValue getDelta() {
		return delta;
	}

	public void registerBuffer(DoubleBuffer<?, ?> buffer) {
		buffers.add(buffer);
	}

	public void unregisterBuffer(DoubleBuffer<?, ?> buffer) {
		buffers.remove(buffer);
	}

	public <T extends Copyable<? extends T>> DoubleBuffer<T, T> buffer(T item) {
		CopyBuffer<T> buffer = new CopyBuffer<>(item);

		registerBuffer(buffer);

		return buffer;
	}

	public <T extends Cloneable> DoubleBuffer<T, T> buffer(T item) {
		CloneBuffer<T> buffer = new CloneBuffer<>(item);

		registerBuffer(buffer);

		return buffer;
	}

	public <B, T> OperationApplicationBuffer<B, T> buffer(T item,
			Function<B, T> function) {
		OperationApplicationBuffer<B, T> buffer = new OperationApplicationBuffer<B, T>(
				item, function);

		registerBuffer(buffer);

		return buffer;
	}

	public <T> ExpressionResultBuffer<T> bufferResult(
			Expression<? extends T> expression) {
		ExpressionResultBuffer<T> buffer = new ExpressionResultBuffer<T>(expression);

		registerBuffer(buffer);

		return buffer;
	}

	public <T, F> ExpressionResultTransformationBuffer<T, F> bufferResult(
			Expression<? extends F> expression, Function<T, ? super F> function) {
		ExpressionResultTransformationBuffer<T, F> buffer = new ExpressionResultTransformationBuffer<T, F>(
				expression, function);

		registerBuffer(buffer);

		return buffer;
	}

	public <T extends Self<? extends T>> DecoupledExpressionResultBuffer<T> bufferDecoupledResult(
			DecouplingExpression<? extends T> expression) {
		DecoupledExpressionResultBuffer<T> buffer = new DecoupledExpressionResultBuffer<T>(
				expression);

		registerBuffer(buffer);

		return buffer;
	}

	public <T, F extends Self<? extends F>> DecoupledExpressionResultTransformationBuffer<T, F> bufferDecoupledResult(
			DecouplingExpression<? extends F> expression,
			Function<T, ? super F> function) {
		DecoupledExpressionResultTransformationBuffer<T, F> buffer = new DecoupledExpressionResultTransformationBuffer<T, F>(
				expression, function);

		registerBuffer(buffer);

		return buffer;
	}

	public void registerInterpolation(DoubleBuffer<?, ?> interpolation,
			DoubleBuffer<?, ?>... buffers) {
		interpolations.put(interpolation, Arrays.asList(buffers));

		for (DoubleBuffer<?, ?> buffer : buffers) {
			registerBuffer(buffer);
		}
	}

	public void unregisterInterpolation(DoubleBuffer<?, ?> interpolation) {
		List<DoubleBuffer<?, ?>> buffers = interpolations.remove(interpolation);

		for (DoubleBuffer<?, ?> buffer : buffers) {
			unregisterBuffer(buffer);
		}
	}

	public <I, T> Interpolate<I, T> bufferInterpolation(
			DoubleBuffer<? extends T, ? extends T> interpolation,
			InterpolationOperation<? extends I, ? super T> operation) {
		DoubleBuffer<T, ?> from = bufferResult(interpolation);

		DoubleBuffer<T, ?> to = bufferResult(interpolation.getBackExpression());

		Interpolate<I, T> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	public <I, T, F> Interpolate<I, T> bufferInterpolation(
			DoubleBuffer<? extends F, ? extends F> interpolation,
			InterpolationOperation<? extends I, ? super T> operation,
			Function<? extends T, ? super F> function) {
		DoubleBuffer<? extends T, ?> from = bufferResult(interpolation, function);

		DoubleBuffer<? extends T, ?> to = bufferResult(
				interpolation.getBackExpression(), function);

		Interpolate<I, T> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	public <I, T extends Self<? extends T>, F> Interpolate<I, T> bufferDecoupledInterpolation(
			DoubleBuffer<T, ? extends DecouplingExpression<? extends T>> interpolation,
			InterpolationOperation<? extends I, ? super T> operation) {
		DoubleBuffer<T, ?> from = bufferResult(interpolation,
				new CopyFunction<T, T>());

		DoubleBuffer<T, ?> to = bufferResult(interpolation.getBackExpression(),
				new DecouplingExpressionEvaluationFunction<T>());

		Interpolate<I, T> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	public <I, T, F extends Self<? extends F>> Interpolate<I, T> bufferDecoupledInterpolation(
			DoubleBuffer<F, ? extends DecouplingExpression<? extends F>> interpolation,
			InterpolationOperation<? extends I, ? super T> operation,
			Function<? extends T, ? super F> function) {
		DoubleBuffer<? extends T, ?> from = bufferResult(interpolation, function);

		DoubleBuffer<? extends T, ?> to = bufferResult(
				interpolation.getBackExpression(), new FunctionComposition<>(
						new DecouplingExpressionEvaluationFunction<F>(), function));

		Interpolate<I, T> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	public void buffer() {
		for (DoubleBuffer<?, ?> bufferedExpression : buffers) {
			bufferedExpression.push();
		}

		for (DoubleBuffer<?, ?> interpolation : interpolations.keySet()) {
			interpolation.push();
		}
	}

	public void render(Renderer renderer, double delta) {
		getDelta().setValue(delta);
		getBufferedRenderable().renderTo(renderer);
	}
}
