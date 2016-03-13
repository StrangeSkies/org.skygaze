package uk.co.strangeskies.extengine.rendering.rendering2d;

import uk.co.strangeskies.extengine.rendering.Camera;
import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.geometry.matrix.MatrixH2;

public interface Camera2D extends Camera<Data2D> {
	public Expression<?, ? extends MatrixH2<?>> getTransformation();

	public void setTransformation(Expression<?, ? extends MatrixH2<?>> position);
}
