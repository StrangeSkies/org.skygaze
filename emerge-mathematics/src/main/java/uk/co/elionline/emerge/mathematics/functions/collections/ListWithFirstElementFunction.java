package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.Function;


public class ListWithFirstElementFunction<E> implements Function<List<E>, E> {
  @Override
  public List<E> applyTo(E input) {
    List<E> list = new ArrayList<>();
    list.add(input);
    return list;
  }
}
