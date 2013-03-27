package uk.co.elionline.emerge.mathematics.logic;

import uk.co.elionline.emerge.mathematics.expressions.BinaryOperationExpression;
import uk.co.elionline.emerge.mathematics.expressions.Expression;

public class OR
    extends
    BinaryOperationExpression</*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue> {
  public OR(Expression<? extends /*@ReadOnly*/BooleanValue> firstOperand,
      Expression<? extends /*@ReadOnly*/BooleanValue> secondOperand) {
    super(firstOperand, secondOperand, new OROperation());
  }
}
