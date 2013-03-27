package uk.co.elionline.gears.mathematics.functions.collections;

import java.util.Collections;
import java.util.List;

import uk.co.elionline.gears.mathematics.functions.Function;


public class UnmodifiableListFunction<E> implements
    Function<List<E>, List<? extends E>> {
  public UnmodifiableListFunction() {
  }

  @Override
  public final List<E> applyTo(List<? extends E> list) {
    return Collections.<E> unmodifiableList(list);
  }
}
