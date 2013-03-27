package uk.co.elionline.gears.mathematics.functions;

public interface BinaryOperation<R, O1, O2> {
  public R apply(O1 firstOperand, O2 secondOperand);
}
