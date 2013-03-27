package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.expressions.UnaryOperationExpression;

public class NOT extends
    UnaryOperationExpression</*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue> {
  public NOT(Expression<? extends /*@ReadOnly*/BooleanValue> operand) {
    super(operand, new NOTOperation());
  }
}
