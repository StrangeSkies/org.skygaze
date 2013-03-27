package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.utilities.Self;

public interface Addable<S extends Addable<S, T>, T> extends Self<S> {
	/**
	 * add the value to this
	 * 
	 * @param value
	 *          the value to add to this
	 * @return this
	 */
	public S add(/*@ReadOnly*/T value);

	/**
	 * add the value to a copy of this
	 * 
	 * @param value
	 *          the value to add to the copy
	 * @return the copy with the added value
	 */
	public S getAdded(/*@ReadOnly AddableReadOnly<S> this,*//*@ReadOnly*/T value);
}
