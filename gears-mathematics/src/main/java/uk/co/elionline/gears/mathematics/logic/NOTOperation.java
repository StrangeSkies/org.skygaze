package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;

public class NOTOperation implements
    UnaryOperation</*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue> {
  @Override
  public /*@ReadOnly*/BooleanValue apply(/*@ReadOnly*/BooleanValue operand) {
    return operand.getNot();
  }
}
