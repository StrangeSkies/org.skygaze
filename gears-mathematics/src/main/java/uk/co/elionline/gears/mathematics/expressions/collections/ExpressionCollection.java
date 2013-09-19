package uk.co.elionline.gears.mathematics.expressions.collections;

import java.util.Collection;
import java.util.Iterator;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.expressions.Variable;
import uk.co.elionline.gears.utilities.Self;

public interface ExpressionCollection<S extends ExpressionCollection<S, E>, E extends Expression<?>>
		extends Collection<E>, Self<S>, Variable<S> {
	@Override
	public boolean contains(Object o);

	@Override
	public boolean containsAll(Collection<?> c);

	@Override
	public boolean isEmpty();

	@Override
	public Iterator<E> iterator();

	@Override
	public int size();

	@Override
	public Object[] toArray();

	@Override
	public <A> A[] toArray(A[] a);

	public Collection<E> getUnmodifiableView();

	public void set(Collection<? extends E> expressions);
}
