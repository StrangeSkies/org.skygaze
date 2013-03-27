package uk.co.elionline.gears.mathematics.expressions.collections;

import java.util.List;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.expressions.FunctionApplicationExpression;
import uk.co.elionline.gears.mathematics.expressions.ImmutableExpression;
import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.mathematics.functions.collections.ListTransformationView;

public class ExpressionListTransformationView<T, F> extends
    ListTransformationView<Expression<? extends T>, Expression<? extends F>> {
  public ExpressionListTransformationView(
      List<? extends Expression<? extends F>> backingList,
      final Function<? extends T, ? super F> function) {
    super(backingList,
        new Function<Expression<? extends T>, Expression<? extends F>>() {
          @Override
          public Expression<? extends T> applyTo(Expression<? extends F> input) {
            return new FunctionApplicationExpression<>(input,
                new ImmutableExpression<>(function));
          }
        });
  }
}
