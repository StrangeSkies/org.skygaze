package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.List;

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
public class ListTransformOnceViewFunction<T, F> implements
    Function<List<T>, List<? extends F>> {
  private final Function<? extends T, ? super F> function;

  public ListTransformOnceViewFunction(Function<? extends T, ? super F> function) {
    this.function = function;
  }

  @Override
  public final List<T> applyTo(List<? extends F> input) {
    return new ListTransformOnceView<>(input, function);
  }

  public final Function<? extends T, ? super F> getFunction() {
    return function;
  }
}
