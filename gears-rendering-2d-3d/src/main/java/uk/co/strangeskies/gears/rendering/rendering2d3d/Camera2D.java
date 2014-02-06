package uk.co.strangeskies.gears.rendering.rendering2d3d;

import uk.co.strangeskies.gears.mathematics.expression.Expression;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.impl.MatrixH2Impl;
import uk.co.strangeskies.gears.rendering.Camera;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;

public interface Camera2D extends Camera<Data2D> {
	public Expression<? extends MatrixH2Impl<?>> getTransformation();

	public void setTransformation(Expression<? extends MatrixH2Impl<?>> position);
}
