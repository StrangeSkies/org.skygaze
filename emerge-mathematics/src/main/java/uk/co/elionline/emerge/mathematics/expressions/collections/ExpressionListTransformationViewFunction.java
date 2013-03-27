package uk.co.elionline.emerge.mathematics.expressions.collections;

import java.util.List;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.functions.Function;



/**
 * A function which returns a ListTransformationView for a given List, which
 * will convert elements using Function passed to the constructor.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 * @param <F>
 */
public class ExpressionListTransformationViewFunction<T, F>
    implements
    Function<List<Expression<? extends T>>, List<? extends Expression<? extends F>>> {
  private final Function<? extends T, ? super F> function;

  public ExpressionListTransformationViewFunction(
      Function<? extends T, ? super F> function) {
    this.function = function;
  }

  @Override
  public final List<Expression<? extends T>> applyTo(
      List<? extends Expression<? extends F>> input) {
    return new ExpressionListTransformationView<>(input, function);
  }

  public final Function<? extends T, ? super F> getFunction() {
    return function;
  }
}
