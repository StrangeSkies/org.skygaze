package uk.co.elionline.emerge.contexts;

import uk.co.elionline.emerge.input.BasicInputController;
import uk.co.elionline.emerge.rendering.Renderer;

/**
 * this class describes an interface in the sense of a view which the user
 * interacts with, such as a menu or the main game interface
 * 
 * @author eli
 * 
 */
public abstract class Context {
	// current stack this interface is running on
	private ContextStack contextStack = null;

	/**
	 * called once when pushed onto an interface stack to be run
	 */
	protected abstract boolean start();

	/**
	 * called once when popped off an interface stack on exit
	 */
	protected abstract void stop();

	/**
	 * called when this interface pauses to push another onto the stack
	 */
	protected abstract void pause();

	/**
	 * called when this interface resumes when one above on the stack finishes
	 */
	protected abstract void resume();

	/**
	 * perform logic!
	 * 
	 * @return true to continue, false to exit
	 */
	protected abstract boolean logic();

	/**
	 * perform rendering!
	 */
	protected abstract void render(double delta);

	protected ContextStack getStack() {
		return contextStack;
	}

	/**
	 * method intended to be used only by InterfaceStack when it tries to push an
	 * Interface
	 * 
	 * @param contextStack
	 * @return
	 */
	final boolean pushOnto(ContextStack contextStack) {
		// if this interface is already running then fail
		if (this.contextStack != null) {
			return false;
		}

		// run on new stack
		this.contextStack = contextStack;
		return true;
	}

	/**
	 * try to push another interface onto the stack we are on
	 * 
	 * be careful where this is used! it will not return until the pushed context
	 * finishes. it will call pause() on this context before starting the new one,
	 * then resume() before returning to this one.
	 * 
	 * @param push
	 *          the interface to push
	 * @return true for success, false for fail
	 */
	protected final boolean push(Context push) {
		return contextStack.push(push);
	}

	protected final BasicInputController getInputController() {
		return contextStack.getInputController();
	}

	protected final Renderer getRenderer() {
		return contextStack.getRenderer();
	}

	protected abstract int getLogicFrequency();

	/**
	 * Buffer the logic state for rendering. This is called once per logic cycle,
	 * and should be designed to take a fairly constant time.
	 */
	protected abstract void buffer();
}
