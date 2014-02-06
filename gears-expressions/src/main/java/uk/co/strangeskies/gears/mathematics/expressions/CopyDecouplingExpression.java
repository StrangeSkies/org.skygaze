package uk.co.strangeskies.gears.mathematics.expressions;

import uk.co.strangeskies.gears.utilities.Copyable;

public interface CopyDecouplingExpression<T extends Copyable<T>> extends
		Expression<T> {
	@Override
	public default T decoupleValue() {
		return getValue().copy();
	}
}
