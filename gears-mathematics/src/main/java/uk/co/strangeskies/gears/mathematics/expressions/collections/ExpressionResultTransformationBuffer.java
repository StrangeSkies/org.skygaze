package uk.co.strangeskies.gears.mathematics.expressions.collections;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.functions.Function;

public class ExpressionResultTransformationBuffer<T, F> extends
    ExpressionBuffer<T, Expression<? extends F>> {
  public static <T, F> BinaryOperation<T, T, Expression<? extends F>> createOperation(
      final Function<? extends T, ? super F> function) {
    return new BinaryOperation<T, T, Expression<? extends F>>() {
      @Override
      public T apply(T firstOperand, Expression<? extends F> secondOperand) {
        return function.applyTo(secondOperand.getValue());
      }
    };
  }

  public ExpressionResultTransformationBuffer(T front,
      Expression<? extends F> back, Function<? extends T, ? super F> function) {
    super(front, back, ExpressionResultTransformationBuffer
        .<T, F> createOperation(function));
  }

  public ExpressionResultTransformationBuffer(Expression<? extends F> back,
      Function<? extends T, ? super F> function) {
    super(null, back, ExpressionResultTransformationBuffer
        .<T, F> createOperation(function));
  }

  public ExpressionResultTransformationBuffer(
      DoubleBuffer<? extends T, ? extends Expression<? extends F>> doubleBuffer,
      Function<? extends T, ? super F> function) {
    super(doubleBuffer.getFront(), doubleBuffer.getBack(),
        ExpressionResultTransformationBuffer.<T, F> createOperation(function));
  }
}
