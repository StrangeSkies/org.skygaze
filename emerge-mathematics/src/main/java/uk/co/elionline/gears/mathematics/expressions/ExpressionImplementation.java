package uk.co.elionline.gears.mathematics.expressions;

import java.util.Set;
import java.util.TreeSet;

import uk.co.elionline.gears.utilities.IdentityComparator;
import uk.co.elionline.gears.utilities.Observer;

public abstract class ExpressionImplementation<T> implements Expression<T> {
	private final Set<Observer<? super Void>> observers;

	public ExpressionImplementation() {
		observers = new TreeSet<Observer<? super Void>>(new IdentityComparator<>());
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

	protected final void postUpdate() {
		for (Observer<? super Void> observer : observers) {
			observer.notify(null);
		}
	}
}
