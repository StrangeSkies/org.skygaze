package uk.co.elionline.emerge.mathematics.expressions;

import uk.co.elionline.emerge.utilities.Observer;
import uk.co.elionline.emerge.utilities.Self;

public class ImmutableExpression<T> implements Self<ImmutableExpression<T>>,
		Expression<T> {
	private final T value;

	public ImmutableExpression(T value) {
		this.value = value;
	}

	@Override
	public final ImmutableExpression<T> getThis() {
		return this;
	}

	@Override
	public final ImmutableExpression<T> copy() {
		return this;
	}

	@Override
	public final boolean addObserver(Observer<? super Void> observer) {
		return false;
	}

	@Override
	public final boolean removeObserver(Observer<? super Void> observer) {
		return false;
	}

	@Override
	public final void clearObservers() {
	}

	@Override
	public final T getValue() {
		return value;
	}
}
