package uk.co.strangeskies.gears.rendering.rendering3d.extensions;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.impl.MatrixH3Impl;
import uk.co.strangeskies.gears.rendering.rendering3d.Data3D;
import uk.co.strangeskies.gears.utilities.Property;

public interface TransformationData3D
		extends
		Property<Expression<? extends MatrixH3Impl<?>>, Expression<? extends MatrixH3Impl<?>>>,
		Data3D {
}
