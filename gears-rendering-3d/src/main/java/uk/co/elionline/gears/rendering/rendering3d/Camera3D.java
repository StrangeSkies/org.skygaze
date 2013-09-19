package uk.co.elionline.gears.rendering.rendering3d;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.impl.Matrix4Impl;
import uk.co.elionline.gears.mathematics.geometry.matrix.impl.MatrixH3Impl;
import uk.co.elionline.gears.rendering.Camera;

public interface Camera3D extends Camera<Data3D> {
	public Expression<? extends MatrixH3Impl<?>> getTransformation();

	public void setTransformation(Expression<? extends MatrixH3Impl<?>> position);

	public Expression<? extends Matrix4Impl<?>> getProjection();

	public void setProjection(Expression<? extends Matrix4Impl<?>> position);
}
