package uk.co.strangeskies.gears.mathematics.expressions;

import uk.co.strangeskies.gears.utilities.Self;

/**
 * A variable for use in reactive programming. A Variable in this sense is a
 * first class expression, that is to say it is an expression whose value is
 * itself.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <S>
 *          See {@link Self} for more information. This must be self-bounding as
 *          the value of the expression is the variable itself.
 */
public interface Variable<S extends Variable<S>> extends Expression<S>,
		Self<S>, DecouplingExpression<S> {
	/**
	 * Must return a const representation of 'this'.
	 * 
	 * In the case of a CompoundExpression for example, this would be achieved
	 * most simply by overriding the abstract 'evaluate()' method to return 'this'
	 * or 'getThis()'...
	 */
	@Override
	public/*@ReadOnly*/S getValue(/*@ReadOnly Addable<S> this*/);

	/**
	 * Must return a representation of this which is safe from side effect
	 * modification between this Expression and the result returned.
	 * 
	 * In the case of a mutable class for example, this could be achieved by
	 * returning a copy of this object, but for an immutable class it can return a
	 * reference to this object, as would getValue().
	 */
	@Override
	public/*@ReadOnly*/S getDecoupledValue(/*@ReadOnly Addable<S> this*/);
}
