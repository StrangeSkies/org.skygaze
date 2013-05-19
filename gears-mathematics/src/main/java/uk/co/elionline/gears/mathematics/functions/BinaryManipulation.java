package uk.co.elionline.gears.mathematics.functions;

public abstract class BinaryManipulation<T, U> implements
		BinaryOperation<T, T, U> {
	@Override
	public final T apply(T firstOperand, U secondOperand) {
		manipulate(firstOperand, secondOperand);

		return firstOperand;
	}

	protected abstract void manipulate(T firstOperand, U secondOperand);
}
