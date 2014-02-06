package uk.co.strangeskies.gears.rendering.rendering2d.impl;

import uk.co.strangeskies.gears.mathematics.expression.Expression;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.impl.MatrixH2Impl;
import uk.co.strangeskies.gears.rendering.Scene;
import uk.co.strangeskies.gears.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;

public class Camera2DImpl implements Camera2D {
	private Scene<Data2D> scene;

	private Expression<? extends MatrixH2Impl<?>> transformation;

	@Override
	public Scene<Data2D> getScene() {
		return scene;
	}

	@Override
	public void setScene(Scene<Data2D> scene) {
		this.scene = scene;
	}

	@Override
	public Expression<? extends MatrixH2Impl<?>> getTransformation() {
		return transformation;
	}

	@Override
	public void setTransformation(Expression<? extends MatrixH2Impl<?>> transformation) {
		this.transformation = transformation;
	}
}
