package uk.co.strangeskies.extengine.swing.rendering.graphics2d;

import java.awt.Graphics2D;

import uk.co.strangeskies.extengine.rendering.Renderable;
import uk.co.strangeskies.extengine.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.extengine.rendering.rendering2d.Data2D;

public interface GraphicsRenderer2DExtensionProcessingContext<T extends Data2D> {
	Graphics2D getGraphics2D();

	Camera2D getCamera();

	Renderable<Data2D> getRenderable();

	T getExtensionData();
}
