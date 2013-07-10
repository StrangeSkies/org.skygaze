package uk.co.elionline.gears.swing.rendering.graphics2d.impl;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.co.elionline.gears.rendering.Renderable;
import uk.co.elionline.gears.rendering.Scene;
import uk.co.elionline.gears.rendering.rendering2d.Camera2D;
import uk.co.elionline.gears.rendering.rendering2d.Data2D;
import uk.co.elionline.gears.rendering.rendering2d.Renderer2D;
import uk.co.elionline.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtension;

public abstract class GraphicsRenderer2D implements Renderer2D {
	private Map<GraphicsRenderer2DExtension<?>, Class<? extends Data2D>> extensions;

	protected abstract Graphics2D getGraphics();

	public <T extends Data2D> void addExtension(
			GraphicsRenderer2DExtension<T> extension, Class<T> dataClass) {
		extensions.put(extension, dataClass);
	}

	@Override
	public boolean supportsDataExtension(
			Class<? extends Data2D> extensionDataClass) {
		return extensions.values().contains(extensionDataClass);
	}

	@Override
	public boolean supportsDataExtensionCombination(
			Collection<? extends Class<? extends Data2D>> extensionDataClasses) {
		return extensions.values().containsAll(extensionDataClasses);
	}

	@Override
	public void render(/*@ReadOnly*/Camera2D camera) {
		Scene<Data2D> scene = camera.getScene();

		Graphics2D graphics = getGraphics();

		for (Renderable<Data2D> renderable : scene.getRenderables()) {
			Set<GraphicsRenderer2DExtension<?>> unprocessedExtensions = new HashSet<>(
					extensions.keySet());

			while (!unprocessedExtensions.isEmpty())
				for (GraphicsRenderer2DExtension<?> extension : new HashSet<>(
						unprocessedExtensions)) {
					// if dependencies are processed
					processExtension(graphics, camera, extension, renderable);
					unprocessedExtensions.remove(extension);
				}
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends Data2D> void processExtension(Graphics2D graphics,
			Camera2D camera, GraphicsRenderer2DExtension<T> extension,
			Renderable<Data2D> renderable) {
		extension.processExtension(graphics, camera,
				(T) renderable.getExtensionData(extensions.get(extension)));
	}
}
