package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;

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
