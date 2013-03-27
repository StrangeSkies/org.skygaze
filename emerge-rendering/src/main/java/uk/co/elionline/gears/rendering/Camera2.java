package uk.co.elionline.gears.rendering;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrices.Matrix3;
import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH2;

public class Camera2 {
	private Expression<? extends MatrixH2<?>> position;
	private Expression<? extends Matrix3<?>> projection;

	public Expression<? extends MatrixH2<?>> getPosition() {
		return position;
	}

	public void setPosition(Expression<? extends MatrixH2<?>> position) {
		this.position = position;
	}

	public Expression<? extends Matrix3<?>> getProjection() {
		return projection;
	}

	public void setProjection(Expression<? extends Matrix3<?>> projection) {
		this.projection = projection;
	}
}
