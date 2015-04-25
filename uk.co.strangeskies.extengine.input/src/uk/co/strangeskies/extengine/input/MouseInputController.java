package uk.co.strangeskies.extengine.input;

import java.util.List;
import java.util.Set;

import uk.co.strangeskies.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.mathematics.values.IntValue;

/**
 * class for all the event tracking
 * 
 * @author Joe
 * 
 */
public interface MouseInputController {
	public void buffer();

	public List<Integer> getMouseButtonsPressed();

	public Set<Integer> getMouseButtonsDown();

	public Vector2<IntValue> getMousePosition();

	public void setMousePosition(Vector2<IntValue> mousePosition);

	public void moveMousePosition(Vector2<IntValue> mousePositionDelta);

	public boolean isMouseInside();

	public boolean isMouseExiting();

	public boolean isMouseEntering();
}
