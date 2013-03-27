package uk.co.elionline.emerge.mathematics.expressions.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.utilities.IdentityComparator;
import uk.co.elionline.emerge.utilities.Observer;
import uk.co.elionline.emerge.utilities.Self;

public class SortedExpressionSet<T> extends TreeSet<Expression<? extends T>>
		implements ExpressionCollection<SortedExpressionSet<T>, T>,
		Self<SortedExpressionSet<T>> {
	private static final long serialVersionUID = 1L;

	private Observer<Void> dependencyObserver;

	private Set<Observer<? super Void>> observers;

	public SortedExpressionSet(
			Comparator<? super Expression<? extends T>> comparator) {
		super(comparator);

		dependencyObserver = new Observer<Void>() {
			@Override
			public void notify(Void message) {
				postUpdate();
			}
		};

		observers = new TreeSet<>(new IdentityComparator<>());
	}

	public SortedExpressionSet() {
		this((Comparator<Expression<? extends T>>) null);
	}

	@SafeVarargs
	public SortedExpressionSet(Expression<? extends T>... expressions) {
		this();

		addAll(expressions);
	}

	public SortedExpressionSet(Collection<Expression<? extends T>> expressions) {
		this();

		addAll(expressions);
	}

	protected final void postUpdate() {
		for (Observer<? super Void> observer : observers) {
			observer.notify(null);
		}
	}

	@Override
	public final boolean add(Expression<? extends T> expression) {
		boolean added = super.add(expression);

		if (added) {
			expression.addObserver(dependencyObserver);

			postUpdate();
		}

		return added;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final boolean remove(Object expression) {
		boolean removed = super.remove(expression);

		if (removed) {
			((Expression<? extends T>) expression).removeObserver(dependencyObserver);

			postUpdate();
		}

		return removed;
	}

	@Override
	public final boolean addAll(
			Collection<? extends Expression<? extends T>> expressions) {
		boolean changed = false;

		for (Expression<? extends T> expression : expressions) {
			if (super.add(expression)) {
				expression.addObserver(dependencyObserver);
				changed = true;
			}
		}

		if (changed) {
			postUpdate();
		}

		return changed;
	}

	@SuppressWarnings("unchecked")
	public final boolean addAll(Expression<? extends T>... expressions) {
		boolean changed = false;

		for (Expression<? extends T> expression : expressions) {
			if (super.add(expression)) {
				expression.addObserver(dependencyObserver);
				changed = true;
			}
		}

		postUpdate();

		return changed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final boolean removeAll(Collection<?> expressions) {
		boolean changed = false;

		for (Object expression : expressions) {
			if (super.remove(expression)) {
				((Expression<? extends T>) expression)
						.removeObserver(dependencyObserver);
				changed = true;
			}
		}

		if (changed) {
			postUpdate();
		}

		return changed;
	}

	@SuppressWarnings("unchecked")
	public final boolean removeAll(Expression<? extends T>... expressions) {
		boolean changed = false;

		for (Expression<? extends T> expression : expressions) {
			if (super.remove(expression)) {
				expression.removeObserver(dependencyObserver);
				changed = true;
			}
		}

		postUpdate();

		return changed;
	}

	@Override
	public final void clear() {
		clear(true);
	}

	protected final void clear(boolean update) {
		if (!isEmpty()) {
			for (Expression<? extends T> expression : this) {
				expression.removeObserver(dependencyObserver);
			}

			super.clear();

			if (update) {
				postUpdate();
			}
		}
	}

	public final void set(
			Collection<? extends Expression<? extends T>> expressions) {
		retainAll(expressions);
		addAll(expressions);
	}

	@SafeVarargs
	public final void set(Expression<? extends T>... expressions) {
		set(Arrays.asList(expressions));
	}

	@Override
	public final boolean retainAll(Collection<?> expressions) {
		TreeSet<Expression<? extends T>> toRemove = new TreeSet<>();

		for (Expression<? extends T> expression : this) {
			if (!expressions.contains(expression)) {
				toRemove.add(expression);
			}
		}

		return removeAll(toRemove);
	}

	@Override
	public final Collection<Expression<? extends T>> getUnmodifiableView() {
		return Collections.unmodifiableSet(this);
	}

	@Override
	public final SortedExpressionSet<T> getValue() {
		return this;
	}

	@Override
	public final SortedExpressionSet<T> getDecoupledValue() {
		return copy();
	}

	@Override
	public final boolean addObserver(Observer<? super Void> observer) {
		return observers.add(observer);
	}

	@Override
	public final boolean removeObserver(Observer<? super Void> observer) {
		return observers.remove(observer);
	}

	@Override
	public final void clearObservers() {
		observers.clear();
	}

	@Override
	public final SortedExpressionSet<T> getThis() {
		return this;
	}

	@Override
	public final SortedExpressionSet<T> copy() {
		return new SortedExpressionSet<>(this);
	}
}
