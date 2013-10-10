package uk.co.strangeskies.gears.mathematics.functions;

import uk.co.strangeskies.gears.utilities.Copyable;


public class CopyFunction<T extends Copyable<? extends T>, C extends Copyable<? extends T>>
    implements Function<T, C> {
  @Override
  public T applyTo(C input) {
    T copy = input.copy();

    return copy;
  }
}
