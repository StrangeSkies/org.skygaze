package uk.co.elionline.emerge.mathematics.logic;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;

public class ANDOperation implements
    BinaryOperation</*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue, /*@ReadOnly*/BooleanValue> {
  @Override
  public /*@ReadOnly*/BooleanValue apply(/*@ReadOnly*/BooleanValue firstOperand,
      /*@ReadOnly*/BooleanValue secondOperand) {
    return firstOperand.getAnd(secondOperand);
  }
}
