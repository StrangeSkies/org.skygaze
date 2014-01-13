package uk.co.strangeskies.gears.rendering.buffering.impl;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.Interpolate;
import uk.co.strangeskies.gears.mathematics.InterpolationOperation;
import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpressionEvaluationFunction;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;
import uk.co.strangeskies.gears.rendering.buffering.SceneInterpolator;
import uk.co.strangeskies.gears.utilities.IdentityComparator;
import uk.co.strangeskies.gears.utilities.Self;
import uk.co.strangeskies.gears.utilities.functions.CopyFunction;

public class SceneInterpolatorImpl extends SceneBufferImpl implements
		SceneInterpolator {
	private final DoubleValue delta;

	private final TreeMap<DoubleBuffer<?, ?>, List<DoubleBuffer<?, ?>>> interpolations;

	public SceneInterpolatorImpl() {
		interpolations = new TreeMap<>(new IdentityComparator<>());

		delta = new DoubleValue(0);
	}

	@Override
	public final DoubleValue getDelta() {
		return delta;
	}

	protected void registerInterpolation(DoubleBuffer<?, ?> interpolation,
			DoubleBuffer<?, ?>... buffers) {
		interpolations.put(interpolation, Arrays.asList(buffers));

		for (DoubleBuffer<?, ?> buffer : buffers) {
			registerBuffer(buffer);
		}
	}

	protected void unregisterInterpolation(DoubleBuffer<?, ?> interpolation) {
		List<DoubleBuffer<?, ?>> buffers = interpolations.remove(interpolation);

		for (DoubleBuffer<?, ?> buffer : buffers) {
			unregisterBuffer(buffer);
		}
	}

	@Override
	public <T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends T, ? extends T> interpolation,
			InterpolationOperation<? super T, ? extends I> operation) {
		DoubleBuffer<?, T> from = bufferResult(interpolation);

		DoubleBuffer<?, T> to = bufferResult(interpolation.getBackExpression());

		Interpolate<T, I> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	@Override
	public <F, T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends F, ? extends F> interpolation,
			InterpolationOperation<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function) {
		DoubleBuffer<?, ? extends T> from = bufferResult(interpolation, function);

		DoubleBuffer<?, ? extends T> to = bufferResult(
				interpolation.getBackExpression(), function);

		Interpolate<T, I> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	@Override
	public <F, T extends Self<? extends T>, I> Interpolate<T, I> bufferDecoupledInterpolation(
			DoubleBuffer<? extends DecouplingExpression<? extends T>, T> interpolation,
			InterpolationOperation<? super T, ? extends I> operation) {
		DoubleBuffer<?, T> from = bufferResult(interpolation,
				new CopyFunction<T, T>());

		DoubleBuffer<?, T> to = bufferResult(interpolation.getBackExpression(),
				f -> f.getDecoupledValue());

		Interpolate<T, I> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	@Override
	public <F extends Self<? extends F>, T, I> Interpolate<T, I> bufferDecoupledInterpolation(
			DoubleBuffer<? extends DecouplingExpression<? extends F>, F> interpolation,
			InterpolationOperation<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function) {
		DoubleBuffer<?, ? extends T> from = bufferResult(interpolation, function);

		DoubleBuffer<?, ? extends T> to = bufferResult(
				interpolation.getBackExpression(),
				function
						.<DecouplingExpression<? extends F>> compose(new DecouplingExpressionEvaluationFunction<F>()));

		Interpolate<T, I> interpolate = new Interpolate<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	@Override
	public void push() {
		super.push();

		for (DoubleBuffer<?, ?> interpolation : interpolations.keySet()) {
			interpolation.push();
		}
	}
}
