package uk.co.elionline.gears.swing.rendering.graphics2d;

import java.awt.Graphics2D;

import uk.co.elionline.gears.rendering.rendering2d.Camera2D;
import uk.co.elionline.gears.rendering.rendering2d.Data2D;

public interface GraphicsRenderer2DExtension<T extends Data2D> {
	public void processExtension(Graphics2D graphics, Camera2D camera,
			T extensionData);
}
