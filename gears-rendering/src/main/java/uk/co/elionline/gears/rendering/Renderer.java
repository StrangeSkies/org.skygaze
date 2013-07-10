package uk.co.elionline.gears.rendering;

import java.util.Collection;

public interface Renderer<T, C extends Camera<T>> {
	public boolean supportsDataExtension(Class<? extends T> extensionDataClass);

	public boolean supportsDataExtensionCombination(
			Collection<? extends Class<? extends T>> extensionDataClasses);

	public void render(C camera);
}

/*

public boolean requestFullscreen(boolean fullscreen);

public boolean isFullscreen();

public boolean isFullscreenAvailable();

public boolean isWindowedAvailable();

public List<Vector2<IntValue>> getAvailableResolutions();

public boolean requestResolution(Vector2<IntValue> resolution);

public boolean requestResolution(int width, int height);

public Vector2<IntValue> getResolution();

public int getWidth();

public int getHeight();

*/