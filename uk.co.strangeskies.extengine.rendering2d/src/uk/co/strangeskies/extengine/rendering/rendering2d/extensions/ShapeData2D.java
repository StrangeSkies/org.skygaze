package uk.co.strangeskies.extengine.rendering.rendering2d.extensions;

import uk.co.strangeskies.extengine.rendering.rendering2d.Data2D;
import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.geometry.shape.Shape;

public interface ShapeData2D extends Data2D {
	public Expression<Shape<?>> getShape();
}
