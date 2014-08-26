package uk.co.strangeskies.gears.rendering.rendering2d.extensions;

import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;
import uk.co.strangeskies.mathematics.geometry.matrix.MatrixH2;

public interface TransformationData2D extends Data2D {
	public MatrixH2<?> getTransformationMatrix();
}
