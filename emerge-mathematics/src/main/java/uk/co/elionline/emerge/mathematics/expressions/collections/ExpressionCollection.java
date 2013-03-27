package uk.co.elionline.emerge.mathematics.expressions.collections;

import java.util.Collection;
import java.util.Iterator;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.expressions.Variable;
import uk.co.elionline.emerge.utilities.Self;

public interface ExpressionCollection<S extends ExpressionCollection<S, T>, T>
		extends Collection<Expression<? extends T>>, Self<S>, Variable<S> {
	public boolean contains(Object o);

	public boolean containsAll(Collection<?> c);

	public boolean isEmpty();

	public Iterator<Expression<? extends T>> iterator();

	public int size();

	public Object[] toArray();

	public <A> A[] toArray(A[] a);

	public Collection<Expression<? extends T>> getUnmodifiableView();
}
