package uk.co.strangeskies.extengine.rendering.buffering.impl;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;

import uk.co.strangeskies.extengine.rendering.buffering.SceneInterpolator;
import uk.co.strangeskies.mathematics.expression.buffer.DoubleBuffer;
import uk.co.strangeskies.mathematics.operation.Interpolation;
import uk.co.strangeskies.mathematics.operation.InterpolationFunction;
import uk.co.strangeskies.mathematics.values.DoubleValue;
import uk.co.strangeskies.utilities.IdentityComparator;

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
	public <T, I> Interpolation<T, I> bufferInterpolation(
			DoubleBuffer<? extends T, ? extends T> interpolation,
			InterpolationFunction<? super T, ? extends I> operation) {
		DoubleBuffer<?, T> from = bufferResult(interpolation);

		DoubleBuffer<?, T> to = bufferResult(interpolation.getBackExpression());

		Interpolation<T, I> interpolate = new Interpolation<>(from, to, getDelta(),
				operation);

		registerInterpolation(interpolation, to, from);

		return interpolate;
	}

	@Override
	public <F, T, I> Interpolation<T, I> bufferInterpolation(
			DoubleBuffer<? extends F, ? extends F> interpolation,
			InterpolationFunction<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function) {
		DoubleBuffer<?, ? extends T> from = bufferResult(interpolation, function);

		DoubleBuffer<?, ? extends T> to = bufferResult(
				interpolation.getBackExpression(), function);

		Interpolation<T, I> interpolate = new Interpolation<>(from, to, getDelta(),
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
