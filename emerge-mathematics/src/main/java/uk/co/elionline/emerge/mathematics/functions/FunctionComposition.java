package uk.co.elionline.emerge.mathematics.functions;

import uk.co.elionline.emerge.utilities.Decorator;


public class FunctionComposition<T, F> extends
    Decorator<TransparentFunctionComposition<T, ?, F>> implements
    Function<T, F> {
  public <I> FunctionComposition(
      Function<? extends I, ? super F> firstFunction,
      Function<? extends T, ? super I> secondFunction) {
    super(new TransparentFunctionComposition<>(firstFunction, secondFunction));
  }

  @Override
  public T applyTo(F input) {
    return getComponent().applyTo(input);
  }

  public final Function<?, ? super F> getFirstFunction() {
    return getComponent().getFirstFunction();
  }

  public final Function<? extends T, ?> getSecondFunction() {
    return getComponent().getSecondFunction();
  }
}
