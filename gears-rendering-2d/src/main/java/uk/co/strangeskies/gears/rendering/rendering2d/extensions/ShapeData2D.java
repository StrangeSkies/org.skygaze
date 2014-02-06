package uk.co.strangeskies.gears.rendering.rendering2d.extensions;

import uk.co.strangeskies.gears.mathematics.expression.Expression;
import uk.co.strangeskies.gears.mathematics.geometry.shape.Shape;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;

public interface ShapeData2D extends Data2D {
	public Expression<Shape<?>> getShape();
}
