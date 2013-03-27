package uk.co.elionline.gears.mathematics.functions.collections;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.Function;


public class SubListFunction<E> implements Function<List<E>, List<E>> {
  private int fromIndex;
  private int toIndex;

  public SubListFunction(int fromIndex, int toIndex) {
    this.fromIndex = fromIndex;
    this.toIndex = toIndex;
  }

  @Override
  public final List<E> applyTo(List<E> to) {
    return to.subList(fromIndex, toIndex);
  }
}
