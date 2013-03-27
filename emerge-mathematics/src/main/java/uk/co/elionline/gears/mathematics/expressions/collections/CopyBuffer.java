package uk.co.elionline.gears.mathematics.expressions.collections;

import uk.co.elionline.gears.mathematics.functions.CopyFunction;
import uk.co.elionline.gears.utilities.Copyable;

public class CopyBuffer<T extends Copyable<? extends T>> extends
    OperationApplicationBuffer<T, T> {
  public CopyBuffer(T front, T back) {
    super(front, back, new CopyFunction<T, T>());
  }

  public CopyBuffer(T value) {
    super(value, new CopyFunction<T, T>());
  }

  public CopyBuffer(DoubleBuffer<? extends T, ? extends T> doubleBuffer) {
    super(doubleBuffer.getFront(), doubleBuffer.getBack(),
        new CopyFunction<T, T>());
  }
}
