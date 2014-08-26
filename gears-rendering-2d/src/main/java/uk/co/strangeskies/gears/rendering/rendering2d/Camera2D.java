package uk.co.strangeskies.gears.rendering.rendering2d;

import uk.co.strangeskies.gears.rendering.Camera;
import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.geometry.matrix.impl.MatrixH2Impl;

public interface Camera2D extends Camera<Data2D> {
	public Expression<? extends MatrixH2Impl<?>> getTransformation();

	public void setTransformation(Expression<? extends MatrixH2Impl<?>> position);
}
