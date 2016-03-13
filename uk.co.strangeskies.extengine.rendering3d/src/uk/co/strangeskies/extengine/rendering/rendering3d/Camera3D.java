package uk.co.strangeskies.extengine.rendering.rendering3d;

import uk.co.strangeskies.extengine.rendering.Camera;
import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.geometry.matrix.Matrix4;
import uk.co.strangeskies.mathematics.geometry.matrix.MatrixH3;

public interface Camera3D extends Camera<Data3D> {
	public Expression<?, ? extends MatrixH3<?>> getTransformation();

	public void setTransformation(Expression<?, ? extends MatrixH3<?>> position);

	public Expression<?, ? extends Matrix4<?>> getProjection();

	public void setProjection(Expression<?, ? extends Matrix4<?>> position);
}
