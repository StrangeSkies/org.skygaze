package uk.co.elionline.gears.mathematics.expressions.collections;

import java.util.List;

import uk.co.elionline.gears.mathematics.expressions.Expression;

public interface ExpressionList<S extends ExpressionList<S, E>, E extends Expression<?>>
		extends List<E>, ExpressionCollection<S, E> {
}
