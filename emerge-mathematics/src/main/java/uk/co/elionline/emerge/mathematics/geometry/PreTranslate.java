package uk.co.elionline.emerge.mathematics.geometry;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;

public class PreTranslate<O>
		extends
		BinaryOperationExpression<O, NonCommutativelyTranslatable<? extends O>, Vector<?, ?>> {
	public PreTranslate(
			Expression<? extends NonCommutativelyTranslatable<? extends O>> firstOperand,
			Expression<? extends Vector<?, ?>> secondOperand) {
		super(firstOperand, secondOperand, new PreTranslationOperation<O>());
	}

	@Override
	public String toString() {
		return "(" + getFirstOperand() + " * " + getSecondOperand() + ")";
	}
}
