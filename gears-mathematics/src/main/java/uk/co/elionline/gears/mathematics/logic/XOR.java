package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.gears.mathematics.expressions.Expression;

public class XOR<O extends XORable<?, ? super T>, T>
		extends
		BinaryOperationExpression</*@ReadOnly*/O, /*@ReadOnly*/XORable<? extends O, ? super T>, /*@ReadOnly*/T> {
	public XOR(
			Expression<? extends /*@ReadOnly*/XORable<? extends O, ? super T>> firstOperand,
			Expression<? extends /*@ReadOnly*/T> secondOperand) {
		super(firstOperand, secondOperand, new XOROperation<O, T>());
	}
}
