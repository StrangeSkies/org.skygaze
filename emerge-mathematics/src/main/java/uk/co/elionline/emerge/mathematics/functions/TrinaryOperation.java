package uk.co.elionline.emerge.mathematics.functions;

public interface TrinaryOperation<R, O1, O2, O3> {
  public R apply(O1 firstOperand, O2 secondOperand, O3 thirdOperand);
}
