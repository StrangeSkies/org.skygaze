package uk.co.elionline.emerge.utilities.tuples;

public class Unit<H> extends Tuple<H, NullTuple> {
  public Unit(H head) {
    super(head);
  }
}
