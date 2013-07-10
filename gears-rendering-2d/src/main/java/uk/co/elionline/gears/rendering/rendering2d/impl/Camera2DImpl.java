package uk.co.elionline.gears.rendering.rendering2d.impl;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH2;
import uk.co.elionline.gears.rendering.Scene;
import uk.co.elionline.gears.rendering.rendering2d.Camera2D;
import uk.co.elionline.gears.rendering.rendering2d.Data2D;

public class Camera2DImpl implements Camera2D {
	private Scene<Data2D> scene;

	private Expression<? extends MatrixH2<?>> transformation;

	@Override
	public Scene<Data2D> getScene() {
		return scene;
	}

	@Override
	public void setScene(Scene<Data2D> scene) {
		this.scene = scene;
	}

	@Override
	public Expression<? extends MatrixH2<?>> getTransformation() {
		return transformation;
	}

	@Override
	public void setTransformation(Expression<? extends MatrixH2<?>> transformation) {
		this.transformation = transformation;
	}
}
