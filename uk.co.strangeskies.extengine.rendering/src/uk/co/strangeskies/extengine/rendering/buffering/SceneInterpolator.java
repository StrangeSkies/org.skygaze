package uk.co.strangeskies.extengine.rendering.buffering;

import java.util.function.Function;

import uk.co.strangeskies.mathematics.expression.buffer.DoubleBuffer;
import uk.co.strangeskies.mathematics.operation.Interpolation;
import uk.co.strangeskies.mathematics.operation.InterpolationFunction;
import uk.co.strangeskies.mathematics.values.DoubleValue;

public interface SceneInterpolator extends SceneBuffer {
	public DoubleValue getDelta();

	public <T, I> Interpolation<T, I> bufferInterpolation(
			DoubleBuffer<? extends T, ? extends T> interpolation,
			InterpolationFunction<? super T, ? extends I> operation);

	public <F, T, I> Interpolation<T, I> bufferInterpolation(
			DoubleBuffer<? extends F, ? extends F> interpolation,
			InterpolationFunction<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function);
}
