package uk.co.strangeskies.gears.rendering.rendering2d3d;

import uk.co.strangeskies.gears.rendering.Renderer;
import uk.co.strangeskies.gears.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;

public interface Renderer2D extends Renderer<Data2D, Camera2D> {
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