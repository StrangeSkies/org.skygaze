package uk.co.elionline.gears.mathematics.functions;

public abstract class UnaryManipulation<T> implements UnaryOperation<T, T> {
	@Override
	public final T apply(T operand) {
		manipulate(operand);

		return operand;
	}

	protected abstract void manipulate(T operand);
}
