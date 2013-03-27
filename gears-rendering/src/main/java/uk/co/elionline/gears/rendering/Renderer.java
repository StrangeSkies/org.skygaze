package uk.co.elionline.gears.rendering;

import java.awt.Shape;

import uk.co.elionline.gears.mathematics.geometry.matrices.MatrixH2;

public interface Renderer {
	public abstract void initialise();

	public abstract void render(Sprite sprite);

	public abstract void render(Shape shape);

	public abstract void setColour(Colour colour);

	public abstract void setTransformation2(MatrixH2<?> transformation);

	public abstract void setCamera2(Camera2 camera);

	public abstract void display();

	public abstract void requestSize(int width, int height);

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract void showSystemCursor(boolean show);
}