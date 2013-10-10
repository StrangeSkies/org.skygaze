package uk.co.strangeskies.gears.mathematics;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;

public class PreMultiplicationOperation<O extends NonCommutativelyMultipliable<?, ? super T>, T>
		implements
		BinaryOperation<O, NonCommutativelyMultipliable<? extends O, ? super T>, T> {
	@Override
	public O apply(
			NonCommutativelyMultipliable<? extends O, ? super T> firstOperand,
			T secondOperand) {
		return firstOperand.getPreMultiplied(secondOperand);
	}
}
