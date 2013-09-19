package uk.co.elionline.gears.rendering.rendering3d.extensions;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.impl.MatrixH3Impl;
import uk.co.elionline.gears.rendering.rendering3d.Data3D;
import uk.co.elionline.gears.utilities.Property;

public interface TransformationData3D
		extends
		Property<Expression<? extends MatrixH3Impl<?>>, Expression<? extends MatrixH3Impl<?>>>,
		Data3D {
}
