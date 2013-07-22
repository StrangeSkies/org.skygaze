package uk.co.elionline.gears.rendering.buffering.impl;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import uk.co.elionline.gears.mathematics.Interpolate;
import uk.co.elionline.gears.mathematics.InterpolationOperation;
import uk.co.elionline.gears.mathematics.expressions.DecouplingExpression;
import uk.co.elionline.gears.mathematics.expressions.DecouplingExpressionEvaluationFunction;
import uk.co.elionline.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.elionline.gears.mathematics.functions.CopyFunction;
import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.mathematics.functions.FunctionComposition;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.rendering.buffering.SceneInterpolator;
import uk.co.elionline.gears.utilities.IdentityComparator;
import uk.co.elionline.gears.utilities.Self;

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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public void push() {
		super.push();

		for (DoubleBuffer<?, ?> interpolation : interpolations.keySet()) {
			interpolation.push();
		}
	}
}
