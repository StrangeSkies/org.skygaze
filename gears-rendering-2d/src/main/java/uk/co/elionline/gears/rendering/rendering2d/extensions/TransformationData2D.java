package uk.co.elionline.gears.rendering.rendering2d.extensions;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.MatrixH2;
import uk.co.elionline.gears.rendering.rendering2d.Data2D;
import uk.co.elionline.gears.utilities.SimpleProperty;

public interface TransformationData2D extends
		SimpleProperty<Expression<? extends MatrixH2<?>>>, Data2D {
}
