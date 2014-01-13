package uk.co.strangeskies.gears.utilities.functions;

import java.util.function.UnaryOperator;

public abstract class UnaryManipulation<T> implements UnaryOperator<T> {
	@Override
	public final T apply(T operand) {
		manipulate(operand);

		return operand;
	}

	protected abstract void manipulate(T operand);
}
