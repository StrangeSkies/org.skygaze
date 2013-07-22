package uk.co.elionline.gears.rendering.rendering3d;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.Matrix4;
import uk.co.elionline.gears.mathematics.geometry.matrix.MatrixH3;
import uk.co.elionline.gears.rendering.Camera;

public interface Camera3D extends Camera<Data3D> {
	public Expression<? extends MatrixH3<?>> getTransformation();

	public void setTransformation(Expression<? extends MatrixH3<?>> position);

	public Expression<? extends Matrix4<?>> getProjection();

	public void setProjection(Expression<? extends Matrix4<?>> position);
}
