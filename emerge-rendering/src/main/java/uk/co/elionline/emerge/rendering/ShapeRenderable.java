package uk.co.elionline.emerge.rendering;

import java.awt.Shape;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.geometry.matrices.MatrixH2;

public class ShapeRenderable extends Renderable {
	private final Expression<? extends Shape> shape;
	private final Expression<? extends Colour> colour;

	public ShapeRenderable(Expression<? extends Shape> shape,
			Expression<? extends Colour> color,
			Expression<? extends MatrixH2<?>> transformation) {
		super(transformation);

		this.shape = shape;
		this.colour = color;
	}

	public Shape getShape() {
		return shape.getValue();
	}

	public Colour getColour() {
		return colour.getValue();
	}

	@Override
	public void renderTo(Renderer2D renderer) {
		super.renderTo(renderer);

		renderer.setColour(getColour());

		renderer.render(getShape());
	}
}
