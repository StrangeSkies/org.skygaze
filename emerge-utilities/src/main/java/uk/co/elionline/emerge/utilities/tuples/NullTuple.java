package uk.co.elionline.emerge.utilities.tuples;

public final class NullTuple extends Tuple<Void, Tuple<?, ?>> {
  private NullTuple() {
    super(null, null);
  }
}
