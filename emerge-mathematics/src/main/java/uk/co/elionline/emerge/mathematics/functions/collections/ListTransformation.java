package uk.co.elionline.emerge.mathematics.functions.collections;

import java.util.ArrayList;
import java.util.List;

import uk.co.elionline.emerge.mathematics.functions.Function;


public class ListTransformation<T> extends ArrayList<T> {
  private static final long serialVersionUID = 1L;

  public <F> ListTransformation(List<? extends F> list,
      Function<? extends T, ? super F> function) {
    for (F from : list) {
      add(function.applyTo(from));
    }
  }
}
