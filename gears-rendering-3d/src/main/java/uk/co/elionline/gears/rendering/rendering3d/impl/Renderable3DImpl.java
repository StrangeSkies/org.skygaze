package uk.co.elionline.gears.rendering.rendering3d.impl;

import java.util.HashMap;
import java.util.Map;

import uk.co.elionline.gears.rendering.Renderable;
import uk.co.elionline.gears.rendering.rendering3d.Data3D;

public class Renderable3DImpl implements Renderable<Data3D> {
	private boolean visible;

	private final Map<Class<? extends Data3D>, Data3D> extensions;

	public Renderable3DImpl() {
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
	public <T extends Data3D> T putExtensionData(T extensionData) {
		return (T) extensions.put(extensionData.getClass(), extensionData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Data3D> T removeExtensionData(Class<T> extensionDataClass) {
		return (T) extensions.remove(extensionDataClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Data3D> T getExtensionData(Class<T> extensionDataClass) {
		return (T) extensions.get(extensionDataClass);
	}
}
