package uk.co.elionline.gears.mathematics.geometry;

import uk.co.elionline.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrices.Vector;

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
