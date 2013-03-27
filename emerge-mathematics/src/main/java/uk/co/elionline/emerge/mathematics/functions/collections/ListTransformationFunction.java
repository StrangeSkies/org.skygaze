package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.Function;


public class ListTransformationFunction<T, F> implements
    Function<List<T>, List<? extends F>> {
  private final Function<? extends T, ? super F> function;

  public ListTransformationFunction(Function<? extends T, ? super F> function) {
    this.function = function;
  }

  @Override
  public final List<T> applyTo(List<? extends F> input) {
    return new ListTransformation<>(input, function);
  }

  public final Function<? extends T, ? super F> getFunction() {
    return function;
  }
}
