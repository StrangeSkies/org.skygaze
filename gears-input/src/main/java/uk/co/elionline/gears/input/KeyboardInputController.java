package uk.co.elionline.gears.input;

import java.util.List;
import java.util.Set;

/**
 * class for all the event tracking
 * 
 * @author Joe
 * 
 */
public interface KeyboardInputController {
	public void buffer();

	public List<Integer> getKeysPressed();

	public Set<Integer> getKeysDown();
}
