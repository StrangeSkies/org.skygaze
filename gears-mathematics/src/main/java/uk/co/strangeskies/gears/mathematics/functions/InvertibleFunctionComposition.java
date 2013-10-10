package uk.co.strangeskies.gears.mathematics.functions;

import uk.co.strangeskies.gears.utilities.Decorator;


public class InvertibleFunctionComposition<T, F> extends
    Decorator<TransparentInvertibleFunctionComposition<T, ?, F>> implements
    InvertibleFunction<T, F> {
  private InvertibleFunctionComposition<F, T> inverse;

  public <I> InvertibleFunctionComposition(
      InvertibleFunction<I, F> firstFunction,
      InvertibleFunction<T, I> secondFunction) {
    super(new TransparentInvertibleFunctionComposition<>(firstFunction,
        secondFunction));
  }

  protected InvertibleFunctionComposition(
      InvertibleFunctionComposition<F, T> inverse) {
    super(inverse.getComponent().getInverse());

    this.inverse = inverse;
  }

  @Override
  public T applyTo(F input) {
    return getComponent().applyTo(input);
  }

  @Override
  public InvertibleFunctionComposition<F, T> getInverse() {
    return inverse;
  }
}
