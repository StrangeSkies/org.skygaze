package uk.co.elionline.emerge.mathematics.functions;

public abstract class AssignmentOperation<A, O> implements
    BinaryOperation<A, A, O> {
  public abstract void assign(A assignee, O assignment);

  @Override
  public final A apply(A assignee, O assignment) {
    assign(assignee, assignment);

    return assignee;
  }
}
