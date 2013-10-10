package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.SortedSet;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public interface SortedExpressionSet<S extends SortedExpressionSet<S, E>, E extends Expression<?>>
		extends ExpressionSet<S, E>, SortedSet<E> {
}
