package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector;

public class Translate<O> extends
		BinaryOperationExpression<O, Translatable<? extends O>, Vector<?, ?>> {
	public Translate(
			Expression<? extends Translatable<? extends O>> firstOperand,
			Expression<? extends Vector<?, ?>> secondOperand) {
		super(firstOperand, secondOperand, new TranslationOperation<O>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
