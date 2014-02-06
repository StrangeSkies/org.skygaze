package uk.co.strangeskies.gears.rendering.buffering;

import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.Interpolate;
import uk.co.strangeskies.gears.mathematics.InterpolationOperation;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;

public interface SceneInterpolator extends SceneBuffer {
	public DoubleValue getDelta();

	public <T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends T, ? extends T> interpolation,
			InterpolationOperation<? super T, ? extends I> operation);

	public <F, T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends F, ? extends F> interpolation,
			InterpolationOperation<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function);
}
