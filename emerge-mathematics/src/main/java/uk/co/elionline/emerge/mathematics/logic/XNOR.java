package uk.co.elionline.emerge.mathematics.logic;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;

public class XNOR
    extends
    BinaryOperationExpression</*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue> {
  public XNOR(Expression<? extends /*@ReadOnly*/BooleanValue> firstOperand,
      Expression<? extends /*@ReadOnly*/BooleanValue> secondOperand) {
    super(firstOperand, secondOperand, new XNOROperation());
  }
}
