package uk.co.strangeskies.extengine.rendering.rendering3d.extensions;

import uk.co.strangeskies.extengine.rendering.rendering3d.Data3D;
import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.geometry.matrix.MatrixH3;
import uk.co.strangeskies.utilities.Property;

public interface TransformationData3D
		extends Property<Expression<?, ? extends MatrixH3<?>>, Expression<?, ? extends MatrixH3<?>>>, Data3D {
}
