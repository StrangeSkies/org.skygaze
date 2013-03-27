package uk.co.elionline.emerge.mathematics.functions;

public interface BinaryOperation<R, O1, O2> {
  public R apply(O1 firstOperand, O2 secondOperand);
}
