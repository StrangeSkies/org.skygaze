package uk.co.elionline.gears.rendering.rendering3d.extensions;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH3;
import uk.co.elionline.gears.rendering.rendering3d.Data3D;
import uk.co.elionline.gears.utilities.Property;

public interface TransformationData3D
		extends
		Property<Expression<? extends MatrixH3<?>>, Expression<? extends MatrixH3<?>>>,
		Data3D {
}
