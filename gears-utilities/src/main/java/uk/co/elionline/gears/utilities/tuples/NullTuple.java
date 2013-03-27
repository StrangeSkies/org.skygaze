package uk.co.elionline.gears.utilities.tuples;

public final class NullTuple extends Tuple<Void, Tuple<?, ?>> {
  private NullTuple() {
    super(null, null);
  }
}
