package uk.co.strangeskies.gears.mathematics.expressions;

import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.mathematics.functions.FunctionApplicationOperation;

public class FunctionApplicationExpression<T, F> extends
    BinaryOperationExpression<T, F, Function<? extends T, ? super F>> {
  public FunctionApplicationExpression(Expression<? extends F> expression,
      Expression<? extends Function<? extends T, ? super F>> function) {
    super(expression, function, new FunctionApplicationOperation<T, F>());
  }

  public FunctionApplicationExpression(F expression,
      Expression<? extends Function<? extends T, ? super F>> function) {
    this(new ImmutableExpression<>(expression), function);
  }

  @Override
  protected T evaluate() {
    return getSecondOperand().getValue().applyTo(getFirstOperand().getValue());
  }
}
