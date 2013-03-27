package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.expressions.DecouplingExpression;
import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH2;
import uk.co.elionline.gears.mathematics.values.Value;

public interface AffineTransformation2<S extends AffineTransformation2<S, V>, V extends Value<V>>
		extends DecouplingExpression<MatrixH2<V>> {
}
