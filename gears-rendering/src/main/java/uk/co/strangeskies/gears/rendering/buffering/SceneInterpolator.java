package uk.co.strangeskies.gears.rendering.buffering;

import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.Interpolate;
import uk.co.strangeskies.gears.mathematics.InterpolationFunction;
import uk.co.strangeskies.gears.mathematics.expression.buffer.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;

public interface SceneInterpolator extends SceneBuffer {
	public DoubleValue getDelta();

	public <T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends T, ? extends T> interpolation,
			InterpolationFunction<? super T, ? extends I> operation);

	public <F, T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends F, ? extends F> interpolation,
			InterpolationFunction<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function);
}
