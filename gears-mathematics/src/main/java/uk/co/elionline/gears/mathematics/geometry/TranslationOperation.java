package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;
import uk.co.elionline.gears.mathematics.geometry.matrices.Vector;

public class TranslationOperation<O> implements
		BinaryOperation<O, Translatable<? extends O>, Vector<?, ?>> {
	@Override
	public O apply(Translatable<? extends O> firstOperand,
			Vector<?, ?> secondOperand) {
		return firstOperand.getTranslated(secondOperand);
	}
}
