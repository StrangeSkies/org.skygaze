package uk.co.elionline.emerge.mathematics.logic;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;

public class XOROperation implements
		BinaryOperation<BooleanValue, BooleanValue, BooleanValue> {
	@Override
	public BooleanValue apply(BooleanValue firstOperand,
			BooleanValue secondOperand) {
		return firstOperand.getXor(secondOperand);
	}
}
