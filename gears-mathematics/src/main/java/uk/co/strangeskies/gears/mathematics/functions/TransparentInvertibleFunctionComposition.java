package uk.co.strangeskies.gears.mathematics.functions;

public class TransparentInvertibleFunctionComposition<T, I, F> implements
    Function<T, F>, InvertibleFunction<T, F> {
  private InvertibleFunction<I, F> firstFunction;
  private InvertibleFunction<T, I> secondFunction;
  private I intermediateResult;

  private TransparentInvertibleFunctionComposition<F, I, T> inverse;

  public TransparentInvertibleFunctionComposition(
      InvertibleFunction<I, F> firstFunction,
      InvertibleFunction<T, I> secondFunction) {
    this.firstFunction = firstFunction;
    this.secondFunction = secondFunction;

    inverse = new TransparentInvertibleFunctionComposition<F, I, T>(this);
  }

  protected TransparentInvertibleFunctionComposition(
      TransparentInvertibleFunctionComposition<F, I, T> inverse) {
    this.inverse = inverse;

    firstFunction = inverse.getSecondFunction().getInverse();
    secondFunction = inverse.getFirstFunction().getInverse();
  }

  @Override
  public T applyTo(F input) {
    intermediateResult = firstFunction.applyTo(input);
    return secondFunction.applyTo(intermediateResult);
  }

  public I getIntermediateResult() {
    return intermediateResult;
  }

  public InvertibleFunction<I, F> getFirstFunction() {
    return firstFunction;
  }

  public InvertibleFunction<T, I> getSecondFunction() {
    return secondFunction;
  }

  @Override
  public TransparentInvertibleFunctionComposition<F, I, T> getInverse() {
    return inverse;
  }
}
