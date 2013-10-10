package uk.co.strangeskies.gears.mathematics.functions;

import java.lang.reflect.InvocationTargetException;

public class CloneFunction<T extends Cloneable> implements Function<T, T> {
  @SuppressWarnings("unchecked")
  @Override
  public T applyTo(T input) {
    try {
      return (T) input.getClass().getMethod("clone").invoke(input);
    } catch (IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
