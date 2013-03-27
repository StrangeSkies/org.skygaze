package uk.co.elionline.emerge.mathematics.expressions;

import uk.co.elionline.emerge.utilities.Self;

public interface DecouplingExpression<T extends Self<? extends T>> extends
		Expression<T> {
	/**
	 * This should be used when the caller wants the value of the expression with
	 * the additional guarantee that the value returned will not be changed along
	 * with the expression, and such that changes to it will not affect the
	 * expression.<br />
	 * <br />
	 * 
	 * This is provided rather than just requiring the user to copy or clone the
	 * value returned from getValue() because often, i.e. in the case of an
	 * Expression over an Immutable value type, we can provide a safe value
	 * without having to go through this extra step, but there may be situations
	 * where the user does not know that this will be the case and so would
	 * normally have to copy anyway.
	 * 
	 * @return
	 */
	public T getDecoupledValue();
}
