package uk.co.strangeskies.gears.mathematics.functions;

public class TransparentFunctionComposition<T, I, F> implements Function<T, F> {
  private final Function<? extends I, ? super F> firstFunction;
  private final Function<? extends T, ? super I> secondFunction;
  private I intermediateResult;

  public TransparentFunctionComposition(
      Function<? extends I, ? super F> firstFunction,
      Function<? extends T, ? super I> secondFunction) {
    this.firstFunction = firstFunction;
    this.secondFunction = secondFunction;
  }

  @Override
  public T applyTo(F input) {
    intermediateResult = firstFunction.applyTo(input);
    return secondFunction.applyTo(intermediateResult);
  }

  public I getIntermediateResult() {
    return intermediateResult;
  }

  public final Function<? extends I, ? super F> getFirstFunction() {
    return firstFunction;
  }

  public final Function<? extends T, ? super I> getSecondFunction() {
    return secondFunction;
  }
}
