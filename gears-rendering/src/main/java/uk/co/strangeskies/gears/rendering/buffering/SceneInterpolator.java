package uk.co.strangeskies.gears.rendering.buffering;

import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.Interpolate;
import uk.co.strangeskies.gears.mathematics.InterpolationOperation;
import uk.co.strangeskies.gears.mathematics.expressions.DecouplingExpression;
import uk.co.strangeskies.gears.mathematics.expressions.collections.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;
import uk.co.strangeskies.gears.utilities.Self;

public interface SceneInterpolator extends SceneBuffer {
	public DoubleValue getDelta();

	public <T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends T, ? extends T> interpolation,
			InterpolationOperation<? super T, ? extends I> operation);

	public <F, T, I> Interpolate<T, I> bufferInterpolation(
			DoubleBuffer<? extends F, ? extends F> interpolation,
			InterpolationOperation<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function);

	public <F, T extends Self<? extends T>, I> Interpolate<T, I> bufferDecoupledInterpolation(
			DoubleBuffer<? extends DecouplingExpression<? extends T>, T> interpolation,
			InterpolationOperation<? super T, ? extends I> operation);

	public <F extends Self<? extends F>, T, I> Interpolate<T, I> bufferDecoupledInterpolation(
			DoubleBuffer<? extends DecouplingExpression<? extends F>, F> interpolation,
			InterpolationOperation<? super T, ? extends I> operation,
			Function<? super F, ? extends T> function);
}
