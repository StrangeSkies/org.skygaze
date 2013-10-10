package uk.co.strangeskies.gears.rendering;

import java.nio.BufferUnderflowException;

public interface RendererBuffer {
	public void push() throws BufferUnderflowException;

	public void requestSize(int size) throws IllegalArgumentException;
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