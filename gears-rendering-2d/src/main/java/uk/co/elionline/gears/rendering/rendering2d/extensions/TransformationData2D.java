package uk.co.elionline.gears.rendering.rendering2d.extensions;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.impl.MatrixH2Impl;
import uk.co.elionline.gears.rendering.rendering2d.Data2D;
import uk.co.elionline.gears.utilities.Property;

public interface TransformationData2D
		extends
		Property<Expression<? extends MatrixH2Impl<?>>, Expression<? extends MatrixH2Impl<?>>>,
		Data2D {
}
