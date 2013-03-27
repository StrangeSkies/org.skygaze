package uk.co.elionline.emerge.mathematics.functions;

public abstract class UnaryManipulation<T> implements UnaryOperation<T, T> {
	@Override
	public T apply(T operand) {
		manipulate(operand);

		return operand;
	}

	protected abstract void manipulate(T operand);
}
