package uk.co.elionline.gears.swing.rendering.graphics2d.impl;

import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.rendering.rendering2d.Data2D;
import uk.co.elionline.gears.rendering.rendering2d.extensions.TransformationData2D;
import uk.co.elionline.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtension;
import uk.co.elionline.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtensionProcessingContext;

@Component
public class GraphicsRenderer2DTransformationExtension implements
		GraphicsRenderer2DExtension<TransformationData2D> {
	@Override
	public void process(
			GraphicsRenderer2DExtensionProcessingContext<TransformationData2D> context) {
		context.getGraphics2D().setTransform(
				new AffineTransform(context
						.getExtensionData()
						.getTransformationMatrix()
						.getPreMultiplied(
								context.getCamera().getTransformation().getValue())
						.getDoubleData()));
	}

	@Override
	public void postProcess(
			GraphicsRenderer2DExtensionProcessingContext<TransformationData2D> context) {
	}

	@Override
	public Class<TransformationData2D> getDataClass() {
		return TransformationData2D.class;
	}

	@Override
	public Set<Class<? extends Data2D>> getProcessingOrderDependencies() {
		return Collections.emptySet();
	}
}
