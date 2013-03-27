package uk.co.elionline.gears.swing.rendering;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.rendering.Graphics2DRenderer;
import uk.co.elionline.gears.rendering.Renderer2D;

@Component(service = Renderer2D.class)
public class SwingComponentRenderer extends Graphics2DRenderer {
	private final Canvas canvas;

	public SwingComponentRenderer() {
		canvas = new Canvas();
	}

	@Override
	public void initialise() {
		// only need to repaint manually
		getCanvas().setIgnoreRepaint(true);
		getCanvas().createBufferStrategy(2);

		super.initialise();
	}

	@Override
	public void requestSize(int width, int height) {
		getCanvas().setPreferredSize(new Dimension(width, height));
	}

	@Override
	public int getWidth() {
		return (int) getCanvas().getSize().getWidth();
	}

	@Override
	public int getHeight() {
		return (int) getCanvas().getSize().getWidth();
	}

	public java.awt.Component getComponent() {
		return getCanvas();
	}

	protected Canvas getCanvas() {
		return canvas;
	}

	@Override
	public void showSystemCursor(boolean show) {
		if (show) {
			getCanvas().setCursor(Cursor.getDefaultCursor());
		} else {
			// Transparent 1 pixel cursor image.
			BufferedImage cursorImg = new BufferedImage(1, 1,
					BufferedImage.TYPE_INT_ARGB);

			// Create a new blank cursor.
			java.awt.Cursor blankCursor = Toolkit.getDefaultToolkit()
					.createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

			// Set the blank cursor to the JFrame.
			getCanvas().setCursor(blankCursor);
		}
	}

	@Override
	protected Graphics2D getGraphics2D() {
		Graphics2D graphics = (Graphics2D) getCanvas().getBufferStrategy()
				.getDrawGraphics();
		if (getCanvas().isVisible()) {
			graphics.clip(new Rectangle(getWidth(), getHeight()));
		} else {
			graphics.clip(new Rectangle(0, 0));
		}
		return graphics;
	}

	@Override
	public void display() {
		getCanvas().getBufferStrategy().show();

		super.display();
	}
}
