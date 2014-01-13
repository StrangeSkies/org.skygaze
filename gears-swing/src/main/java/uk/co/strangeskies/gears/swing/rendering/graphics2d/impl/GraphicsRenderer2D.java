package uk.co.strangeskies.gears.swing.rendering.graphics2d.impl;

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.strangeskies.gears.rendering.Renderable;
import uk.co.strangeskies.gears.rendering.RendererBuffer;
import uk.co.strangeskies.gears.rendering.Scene;
import uk.co.strangeskies.gears.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;
import uk.co.strangeskies.gears.rendering.rendering2d.Renderer2D;
import uk.co.strangeskies.gears.swing.rendering.graphics2d.CircularExtensionDependencyException;
import uk.co.strangeskies.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtension;
import uk.co.strangeskies.gears.swing.rendering.graphics2d.GraphicsRenderer2DExtensionProcessingContext;
import uk.co.strangeskies.gears.utilities.factory.Factory;

public class GraphicsRenderer2D implements Renderer2D {
	protected class ExtensionProcessor<T extends Data2D> {
		private final GraphicsRenderer2DExtension<T> extension;
		private final GraphicsRenderer2DExtensionProcessingContext<T> processingContext;

		public ExtensionProcessor(final Graphics2D graphics, final Camera2D camera,
				final GraphicsRenderer2DExtension<T> extension,
				final Renderable<Data2D> renderable) {
			this.extension = extension;

			processingContext = new GraphicsRenderer2DExtensionProcessingContext<T>() {
				@Override
				public Graphics2D getGraphics2D() {
					return graphics;
				}

				@Override
				public Camera2D getCamera() {
					return camera;
				}

				@Override
				public Renderable<Data2D> getRenderable() {
					return renderable;
				}

				@Override
				public T getExtensionData() {
					return renderable.getExtensionData(extension.getDataClass());
				}
			};
		}

		public void process() {
			extension.process(processingContext);
		}

		public void postProcess() {
			extension.postProcess(processingContext);
		}
	}

	private final Map<Class<? extends Data2D>, GraphicsRenderer2DExtension<?>> extensions;

	private final Factory<Graphics2D> graphicsContext;
	private final RendererBuffer buffer;

	public GraphicsRenderer2D(Factory<Graphics2D> graphicsContext,
			RendererBuffer buffer) {
		this.graphicsContext = graphicsContext;
		this.buffer = buffer;

		extensions = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	public <T extends Data2D> GraphicsRenderer2DExtension<T> putExtension(
			GraphicsRenderer2DExtension<T> extension) {
		GraphicsRenderer2DExtension<T> oldExtension = (GraphicsRenderer2DExtension<T>) extensions
				.put(extension.getDataClass(), extension);

		try {
			getOrderedExtensions();
		} catch (CircularExtensionDependencyException e) {
			extensions.put(oldExtension.getDataClass(), oldExtension);
			throw e;
		}

		return oldExtension;
	}

	@Override
	public boolean supportsDataExtension(
			Class<? extends Data2D> extensionDataClass) {
		return extensions.keySet().contains(extensionDataClass);
	}

	@Override
	public void render(/*@ReadOnly*/Camera2D camera) {
		Graphics2D graphics = graphicsContext.create();
		Scene<Data2D> scene = camera.getScene();

		Deque<ExtensionProcessor<?>> extensionProcessors = new ArrayDeque<>();
		for (Renderable<Data2D> renderable : scene.getRenderables()) {
			for (GraphicsRenderer2DExtension<?> extension : getOrderedExtensions()) {
				ExtensionProcessor<?> extensionProcessor = new ExtensionProcessor<>(
						graphics, camera, extension, renderable);
				extensionProcessor.process();
				extensionProcessors.add(extensionProcessor);
			}
			while (!extensionProcessors.isEmpty()) {
				extensionProcessors.pop().postProcess();
			}
		}
	}

	public List<GraphicsRenderer2DExtension<?>> getOrderedExtensions() {
		Set<GraphicsRenderer2DExtension<?>> unorderedExtensions = new HashSet<>();
		List<GraphicsRenderer2DExtension<?>> orderedExtensions = new ArrayList<>();
		while (!unorderedExtensions.isEmpty()) {
			boolean orderingSuccessful = false;
			for (GraphicsRenderer2DExtension<?> extension : new HashSet<>(
					unorderedExtensions)) {
				if (orderedExtensions.containsAll(extension
						.getProcessingOrderDependencies())) {
					orderingSuccessful = true;
					unorderedExtensions.remove(extension);
					orderedExtensions.add(extension);
				}
			}
			if (!orderingSuccessful) {
				throw new CircularExtensionDependencyException();
			}
		}
		return orderedExtensions;
	}

	@Override
	public RendererBuffer getBuffer() {
		return buffer;
	}
}
