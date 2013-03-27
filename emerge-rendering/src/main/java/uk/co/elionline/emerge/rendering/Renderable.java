package uk.co.elionline.emerge.rendering;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.geometry.matrices.MatrixH2;

public abstract class Renderable {
	private boolean visible;
	private final Expression<? extends MatrixH2<?>> transformation;

	public Renderable(Expression<? extends MatrixH2<?>> transformation) {
		visible = true;

		this.transformation = transformation;
	}

	public MatrixH2<?> getTransformation() {
		return transformation.getValue();
	}

	/**
	 * render this renderable!
	 * 
	 * @param renderer
	 *          the Renderer to render to.
	 * @param delta
	 *          the time (from 0 to 1) of this render since the last frame
	 */
	public void renderTo(Renderer2D renderer) {
		renderer.setTransformation2(getTransformation());
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}
}
