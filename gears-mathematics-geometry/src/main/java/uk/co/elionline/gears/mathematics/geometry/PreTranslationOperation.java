package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector;

public class PreTranslationOperation<O> implements
		BinaryOperation<O, NonCommutativelyTranslatable<? extends O>, Vector<?, ?>> {
	@Override
	public O apply(NonCommutativelyTranslatable<? extends O> firstOperand,
			Vector<?, ?> secondOperand) {
		return firstOperand.getPreTranslated(secondOperand);
	}
}
