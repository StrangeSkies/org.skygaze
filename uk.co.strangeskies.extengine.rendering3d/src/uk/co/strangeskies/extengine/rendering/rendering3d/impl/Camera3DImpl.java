package uk.co.strangeskies.extengine.rendering.rendering3d.impl;

import uk.co.strangeskies.extengine.rendering.Scene;
import uk.co.strangeskies.extengine.rendering.rendering3d.Camera3D;
import uk.co.strangeskies.extengine.rendering.rendering3d.Data3D;
import uk.co.strangeskies.mathematics.expression.Expression;
import uk.co.strangeskies.mathematics.geometry.matrix.Matrix4;
import uk.co.strangeskies.mathematics.geometry.matrix.MatrixH3;

public class Camera3DImpl implements Camera3D {
	private Scene<Data3D> scene;

	private Expression<? extends MatrixH3<?>> transformation;
	private Expression<? extends Matrix4<?>> projection;

	@Override
	public Scene<Data3D> getScene() {
		return scene;
	}

	@Override
	public void setScene(Scene<Data3D> scene) {
		this.scene = scene;
	}

	@Override
	public Expression<? extends MatrixH3<?>> getTransformation() {
		return transformation;
	}

	@Override
	public void setTransformation(
			Expression<? extends MatrixH3<?>> transformation) {
		this.transformation = transformation;
	}

	@Override
	public Expression<? extends Matrix4<?>> getProjection() {
		return projection;
	}

	@Override
	public void setProjection(Expression<? extends Matrix4<?>> projection) {
		this.projection = projection;
	}
}
