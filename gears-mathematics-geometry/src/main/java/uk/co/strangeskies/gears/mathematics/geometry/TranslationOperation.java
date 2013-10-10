package uk.co.strangeskies.gears.mathematics.geometry;

import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector;

public class TranslationOperation<O> implements
		BinaryOperation<O, Translatable<? extends O>, Vector<?, ?>> {
	@Override
	public O apply(Translatable<? extends O> firstOperand,
			Vector<?, ?> secondOperand) {
		return firstOperand.getTranslated(secondOperand);
	}
}
