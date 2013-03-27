package uk.co.elionline.emerge.mathematics.expressions.collections;

import uk.co.elionline.emerge.mathematics.functions.CloneFunction;

public class CloneBuffer<T extends Cloneable> extends
    OperationApplicationBuffer<T, T> {
  public CloneBuffer(T front, T back) {
    super(front, back, new CloneFunction<T>());
  }

  public CloneBuffer(T value) {
    super(value, new CloneFunction<T>());
  }

  public CloneBuffer(DoubleBuffer<? extends T, ? extends T> doubleBuffer) {
    super(doubleBuffer.getFront(), doubleBuffer.getBack(),
        new CloneFunction<T>());
  }
}
