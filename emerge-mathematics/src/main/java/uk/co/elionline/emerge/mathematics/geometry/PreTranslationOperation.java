package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;

public class PreTranslationOperation<O> implements
		BinaryOperation<O, NonCommutativelyTranslatable<? extends O>, Vector<?, ?>> {
	@Override
	public O apply(NonCommutativelyTranslatable<? extends O> firstOperand,
			Vector<?, ?> secondOperand) {
		return firstOperand.getPreTranslated(secondOperand);
	}
}
