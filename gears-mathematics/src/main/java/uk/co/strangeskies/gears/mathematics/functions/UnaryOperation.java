package uk.co.strangeskies.gears.mathematics.functions;

public interface UnaryOperation<R, O> {
  public R apply(O operand);
}
