package uk.co.elionline.emerge.mathematics.expressions.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.utilities.Observer;

public class ExpressionList<T> extends ArrayList<Expression<? extends T>>
		implements ExpressionCollection<ExpressionList<T>, T> {
	private static final long serialVersionUID = 1L;

	private final Observer<Void> dependencyObserver;

	private final Set<Observer<? super Void>> observers;

	public ExpressionList() {
		dependencyObserver = new Observer<Void>() {
			@Override
			public void notify(Void message) {
				postUpdate();
			}
		};

		observers = new TreeSet<>();
	}

	@SafeVarargs
	public ExpressionList(Expression<? extends T>... expressions) {
		this();

		addAll(expressions);
	}

	public ExpressionList(Collection<Expression<? extends T>> expressions) {
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

		if (changed) {
			postUpdate();
		}

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

		if (changed) {
			postUpdate();
		}

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
		clear(false);

		for (Expression<? extends T> expression : expressions) {
			if (super.add(expression)) {
				expression.addObserver(dependencyObserver);
			}
		}

		postUpdate();
	}

	@SuppressWarnings("unchecked")
	public final void set(Expression<? extends T>... expressions) {
		clear(false);

		for (Expression<? extends T> expression : expressions) {
			if (super.add(expression)) {
				expression.addObserver(dependencyObserver);
			}
		}

		postUpdate();
	}

	@Override
	public final boolean retainAll(Collection<?> c) {
		TreeSet<Expression<? extends T>> toRemove = new TreeSet<>();

		toRemove.addAll(this);
		toRemove.removeAll(c);

		return removeAll(toRemove);
	}

	@Override
	public final Collection<Expression<? extends T>> getUnmodifiableView() {
		return Collections.unmodifiableList(this);
	}

	@Override
	public final ExpressionList<T> getValue() {
		return this;
	}

	@Override
	public final ExpressionList<T> getDecoupledValue() {
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
	public final ExpressionList<T> getThis() {
		return this;
	}

	@Override
	public final ExpressionList<T> copy() {
		return new ExpressionList<>(this);
	}

	@Override
	public final void add(int index, Expression<? extends T> expression) {
		super.add(index, expression);

		expression.addObserver(dependencyObserver);

		postUpdate();
	}

	@Override
	public final boolean addAll(int index,
			Collection<? extends Expression<? extends T>> expressions) {
		for (Expression<? extends T> expression : expressions) {
			add(index++, expression);
			expression.addObserver(dependencyObserver);
		}

		postUpdate();

		return !expressions.isEmpty();
	}

	@Override
	public final Expression<? extends T> remove(int index) {
		Expression<? extends T> removed = super.remove(index);

		removed.removeObserver(dependencyObserver);

		postUpdate();

		return removed;
	}

	@Override
	public final Expression<? extends T> set(int index,
			Expression<? extends T> expression) {
		Expression<? extends T> removed = super.remove(index);
		super.add(index, expression);

		removed.removeObserver(dependencyObserver);
		expression.addObserver(dependencyObserver);

		postUpdate();

		return removed;
	}
}
