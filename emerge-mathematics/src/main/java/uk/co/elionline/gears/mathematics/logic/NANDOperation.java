package uk.co.elionline.gears.mathematics.logic;

import uk.co.elionline.gears.mathematics.functions.BinaryOperation;

public class NANDOperation implements
    BinaryOperation</*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue> {
  @Override
  public /*@ReadOnly*/BooleanValue apply(/*@ReadOnly*/BooleanValue firstOperand,
      /*@ReadOnly*/BooleanValue secondOperand) {
    return firstOperand.getNand(secondOperand);
  }
}
