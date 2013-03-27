package uk.co.elionline.emerge.mathematics.functions;

import uk.co.elionline.emerge.mathematics.logic.BooleanValue;

public class ConditionalOperation<O> implements
    UnaryOperation<O, /*@ReadOnly*/BooleanValue> {
  private final O valueWhenFulfilled;
  private final O valueWhenUnfulfilled;

  public ConditionalOperation(O valueWhenFulfilled, O valueWhenUnfulfilled) {
    this.valueWhenFulfilled = valueWhenFulfilled;
    this.valueWhenUnfulfilled = valueWhenUnfulfilled;
  }

  @Override
  public O apply(/*@ReadOnly*/BooleanValue operand) {
    if (operand.getBooleanValue()) {
      return valueWhenFulfilled;
    } else {
      return valueWhenUnfulfilled;
    }
  }

  public final O getValueWhenFulfilled() {
    return valueWhenFulfilled;
  }

  public final O getValueWhenUnfulfilled() {
    return valueWhenUnfulfilled;
  }
}
