package uk.co.strangeskies.extengine.swing.rendering.graphics2d;

import java.util.Set;

import uk.co.strangeskies.extengine.rendering.rendering2d.Data2D;

public interface GraphicsRenderer2DExtension<T extends Data2D> {
	public void process(GraphicsRenderer2DExtensionProcessingContext<T> context);

	public void postProcess(
			GraphicsRenderer2DExtensionProcessingContext<T> context);

	public Class<T> getDataClass();

	public Set<Class<? extends Data2D>> getProcessingOrderDependencies();
}
