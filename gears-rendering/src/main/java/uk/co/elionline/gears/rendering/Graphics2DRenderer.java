package uk.co.elionline.gears.rendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import uk.co.elionline.gears.mathematics.geometry.matrices.Matrix.Order;
import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH2;

public abstract class Graphics2DRenderer implements Renderer2D {
	private Graphics2D graphics;

	// private Camera2 camera;
	// private MatrixH2Const<?> transformation;

	public Graphics2DRenderer() {
		super();
	}

	@Override
	public void initialise() {
		graphics = getGraphics2D();
	}

	@Override
	public void render(Sprite sprite) {
		graphics.drawImage(sprite.getImage(), 0, 0, null);
	}

	@Override
	public void render(Shape shape) {
		graphics.fill(shape);
	}

	@Override
	public void display() {
		graphics.dispose();
		graphics = getGraphics2D();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, getWidth(), getHeight());
	}

	protected abstract Graphics2D getGraphics2D();

	@Override
	public void setColour(Colour colour) {
		graphics.setColor(colour.getColor());
	}

	@Override
	public void setTransformation2(MatrixH2<?> transformation) {
		double[] matrixData = transformation.getMutableMatrix().getDoubleData(
				Order.ColumnMajor);
		AffineTransform affineTransform = new AffineTransform(matrixData);
		graphics.setTransform(affineTransform);
	}

	@Override
	public void setCamera2(Camera2 camera) {

	}
}
