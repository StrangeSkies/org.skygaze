package uk.co.elionline.gears.swing.rendering.graphics2d.impl;

import java.util.Collections;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.rendering.rendering2d.Data2D;
import uk.co.elionline.gears.rendering.rendering2d.extensions.ColourData2D;
import uk.co.elionline.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtension;
import uk.co.elionline.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtensionProcessingContext;

@Component
public class GraphicsRenderer2DColourExtension implements
		GraphicsRenderer2DExtension<ColourData2D> {
	@Override
	public void process(
			GraphicsRenderer2DExtensionProcessingContext<ColourData2D> context) {
		context.getGraphics2D().setColor(context.getData().getColor());
	}

	@Override
	public void postProcess(
			GraphicsRenderer2DExtensionProcessingContext<ColourData2D> context) {
	}

	@Override
	public Class<ColourData2D> getDataClass() {
		return ColourData2D.class;
	}

	@Override
	public Set<Class<? extends Data2D>> getProcessingOrderDependencies() {
		return Collections.emptySet();
	}
}
