package uk.co.elionline.gears.engine.sections;

public interface Section {
	/**
	 * Called once when pushed onto an interface stack to be run.
	 * 
	 * @param sectionProcessingContext
	 *          This interface represents possible interaction with the
	 *          SectionStackProcessor this section was entered from. It is
	 *          required that methods on the provided SectionProcessingContext be
	 *          called from the thread on which this method is running.
	 */
	public void enter(SectionProcessingContext context);

	/**
	 * Called by the SectionStack to pause this interface when another is pushed
	 * onto the stack.
	 */
	public void halt();

	/**
	 * Called by the SectionStack when this interface resumes, after the one above
	 * on the stack finishes and is popped off.
	 */
	public void resume();
}
