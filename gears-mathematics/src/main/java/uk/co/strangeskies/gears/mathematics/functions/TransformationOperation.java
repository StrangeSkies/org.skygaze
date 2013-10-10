package uk.co.strangeskies.gears.mathematics.functions;

public class TransformationOperation<T, F> implements BinaryOperation<T, T, F> {
  private final Function<? extends T, ? super F> function;

  public TransformationOperation(Function<? extends T, ? super F> function) {
    this.function = function;
  }

  @Override
  public T apply(T assignee, F assignment) {
    return function.applyTo(assignment);
  }
}
