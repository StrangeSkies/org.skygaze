package uk.co.strangeskies.gears.input;

import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.gears.mathematics.values.IntValue;

/**
 * class for all the event tracking
 * 
 * @author Joe
 * 
 */
public interface WindowManagerInputController {
	public boolean isWindowMinimising();

	public boolean isWindowRestoring();

	public boolean isWindowMinimised();

	public boolean isWindowClosing();

	public void buffer();

	public Vector2<IntValue> getWindowSize();

	public Vector2<IntValue> getWindowPosition();
}
