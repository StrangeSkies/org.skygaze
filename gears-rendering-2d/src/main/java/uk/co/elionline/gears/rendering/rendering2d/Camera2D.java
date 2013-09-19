package uk.co.elionline.gears.rendering.rendering2d;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.impl.MatrixH2Impl;
import uk.co.elionline.gears.rendering.Camera;

public interface Camera2D extends Camera<Data2D> {
	public Expression<? extends MatrixH2Impl<?>> getTransformation();

	public void setTransformation(Expression<? extends MatrixH2Impl<?>> position);
}
