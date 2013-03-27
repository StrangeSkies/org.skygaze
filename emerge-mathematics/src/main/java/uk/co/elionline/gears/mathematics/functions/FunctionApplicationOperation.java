package uk.co.elionline.gears.mathematics.functions;

public class FunctionApplicationOperation<T, F> implements
    BinaryOperation<T, F, Function<? extends T, ? super F>> {
  @Override
  public T apply(F firstOperand, Function<? extends T, ? super F> secondOperand) {
    return secondOperand.applyTo(firstOperand);
  }
}
