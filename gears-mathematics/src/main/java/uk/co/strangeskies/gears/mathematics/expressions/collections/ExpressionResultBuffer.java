package uk.co.strangeskies.gears.mathematics.expressions.collections;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;

public class ExpressionResultBuffer<T> extends
    ExpressionBuffer<T, Expression<? extends T>> {
  public static <T> BinaryOperation<T, T, Expression<? extends T>> createOperation() {
    return new BinaryOperation<T, T, Expression<? extends T>>() {
      @Override
      public T apply(T firstOperand, Expression<? extends T> secondOperand) {
        return secondOperand.getValue();
      }
    };
  }

  public ExpressionResultBuffer(T front, Expression<? extends T> back) {
    super(front, back, ExpressionResultBuffer.<T> createOperation());
  }

  public ExpressionResultBuffer(Expression<? extends T> back) {
    super(null, back, ExpressionResultBuffer.<T> createOperation());
  }

  public ExpressionResultBuffer(
      DoubleBuffer<? extends T, ? extends Expression<? extends T>> doubleBuffer) {
    super(doubleBuffer.getFront(), doubleBuffer.getBack(),
        ExpressionResultBuffer.<T> createOperation());
  }
}
