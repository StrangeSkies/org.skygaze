package uk.co.elionline.gears.swing.rendering.graphics2d.impl;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.rendering.rendering2d.Camera2D;
import uk.co.elionline.gears.rendering.rendering2d.extensions.TransformationData2D;
import uk.co.elionline.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtension;

@Component
public class GraphicsRenderer2DTransformationExtension implements
		GraphicsRenderer2DExtension<TransformationData2D> {
	@Override
	public void processExtension(Graphics2D graphics, Camera2D camera,
			TransformationData2D extensionData) {
		graphics.setTransform(new AffineTransform(extensionData.get().getValue()
				.getPreMultiplied(camera.getTransformation().getValue())
				.getDoubleData()));
	}
}
