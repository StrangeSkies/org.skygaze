package uk.co.strangeskies.extengine.rendering.rendering2d.impl;

import java.util.HashMap;
import java.util.Map;

import uk.co.strangeskies.extengine.rendering.Renderable;
import uk.co.strangeskies.extengine.rendering.rendering2d.Data2D;

public class Renderable2DImpl implements Renderable<Data2D> {
	private boolean visible;

	private final Map<Class<? extends Data2D>, Data2D> extensions;

	public Renderable2DImpl() {
		extensions = new HashMap<>();
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Data2D> T putExtensionData(T extensionData) {
		return (T) extensions.put(extensionData.getClass(), extensionData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Data2D> T removeExtensionData(Class<T> extensionDataClass) {
		return (T) extensions.remove(extensionDataClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Data2D> T getExtensionData(Class<T> extensionDataClass) {
		return (T) extensions.get(extensionDataClass);
	}
}
