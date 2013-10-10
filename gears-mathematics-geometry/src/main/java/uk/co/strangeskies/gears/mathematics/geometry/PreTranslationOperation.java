package uk.co.strangeskies.gears.mathematics.geometry;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector;

public class PreTranslationOperation<O> implements
		BinaryOperation<O, NonCommutativelyTranslatable<? extends O>, Vector<?, ?>> {
	@Override
	public O apply(NonCommutativelyTranslatable<? extends O> firstOperand,
			Vector<?, ?> secondOperand) {
		return firstOperand.getPreTranslated(secondOperand);
	}
}
