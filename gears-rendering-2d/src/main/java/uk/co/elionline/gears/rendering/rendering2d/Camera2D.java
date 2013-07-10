package uk.co.elionline.gears.rendering.rendering2d;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH2;
import uk.co.elionline.gears.rendering.Camera;

public interface Camera2D extends Camera<Data2D> {
	public Expression<? extends MatrixH2<?>> getTransformation();

	public void setTransformation(Expression<? extends MatrixH2<?>> position);
}
