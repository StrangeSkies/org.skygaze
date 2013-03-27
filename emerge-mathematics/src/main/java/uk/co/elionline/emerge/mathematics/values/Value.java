package uk.co.elionline.emerge.mathematics.values;

import java.util.Set;
import java.util.TreeSet;

import uk.co.elionline.emerge.mathematics.Addable;
import uk.co.elionline.emerge.mathematics.Incrementable;
import uk.co.elionline.emerge.mathematics.Multipliable;
import uk.co.elionline.emerge.mathematics.Negatable;
import uk.co.elionline.emerge.mathematics.Scalable;
import uk.co.elionline.emerge.mathematics.Subtractable;
import uk.co.elionline.emerge.mathematics.expressions.Variable;
import uk.co.elionline.emerge.utilities.Copyable;
import uk.co.elionline.emerge.utilities.IdentityComparator;
import uk.co.elionline.emerge.utilities.Observer;
import uk.co.elionline.emerge.utilities.Property;
import uk.co.elionline.emerge.utilities.Self;

public abstract class Value<S extends Value<S>> extends Number implements
		Multipliable<S, Value<?>>, Addable<S, Value<?>>, Negatable<S, S>,
		Subtractable<S, Value<?>>, Scalable<S>, Property<S, Value<?>>,
		Incrementable<S>, Self<S>, Variable<S>, Copyable<S>, Comparable<Value<?>> {
	private static final long serialVersionUID = -979949605176385397L;

	private final Set<Observer<? super Void>> observers;

	public Value() {
		observers = new TreeSet<Observer<? super Void>>(new IdentityComparator<>());
		setValue(0);
	}

	public Value(Number value) {
		observers = new TreeSet<Observer<? super Void>>(new IdentityComparator<>());
		setValue(value);
	}

	public Value(Value<?> value) {
		observers = new TreeSet<Observer<? super Void>>(new IdentityComparator<>());
		set(value);
	}

	@Override
	public int compareTo(Value<?> other) {
		int comparison = compareToAtSupportedPrecision(other);

		if (comparison == 0) {
			comparison = -other.compareToAtSupportedPrecision(this);
		}

		return comparison;
	}

	@Override
	public abstract S negate();

	public abstract S reciprocate();

	public S getReciprocal() {
		return copy().reciprocate();
	}

	@Override
	public final S getAdded(Value<?> value) {
		return copy().add(value);
	}

	@Override
	public final S getSubtracted(Value<?> value) {
		return copy().subtract(value);
	}

	@Override
	public final S getMultiplied(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public final S getDivided(Value<?> value) {
		return copy().divide(value);
	}

	@Override
	public final S getMultiplied(int value) {
		return copy().multiply(value);
	}

	@Override
	public final S getMultiplied(long value) {
		return copy().multiply(value);
	}

	@Override
	public final S getMultiplied(float value) {
		return copy().multiply(value);
	}

	@Override
	public final S getMultiplied(double value) {
		return copy().multiply(value);
	}

	@Override
	public final S getDivided(int value) {
		return copy().divide(value);
	}

	@Override
	public final S getDivided(long value) {
		return copy().divide(value);
	}

	@Override
	public final S getDivided(float value) {
		return copy().divide(value);
	}

	@Override
	public final S getDivided(double value) {
		return copy().divide(value);
	}

	@Override
	public final S getNegated() {
		return copy().negate();
	}

	@Override
	public abstract String toString();

	@Override
	public abstract S set(Value<?> value);

	public abstract S setValue(Number value);

	@SuppressWarnings("unchecked")
	@Override
	public final S getThis() {
		return (S) this;
	}

	@Override
	public abstract boolean equals(Object that);

	protected abstract boolean equals(Value<?> that);

	public abstract S unitInTheLastPlaceAbove();

	public abstract S unitInTheLastPlaceBelow();

	public final S unitInTheLastPlaceLarger() {
		return maximum(unitInTheLastPlaceAbove(), unitInTheLastPlaceBelow());
	}

	public final S unitInTheLastPlaceSmaller() {
		return minimum(unitInTheLastPlaceAbove(), unitInTheLastPlaceBelow());
	}

	public static <S extends Value<S>> S maximum(S first, S second) {
		if (first.compareTo(second) > 0) {
			return first;
		} else {
			return second;
		}
	}

	public static <S extends Value<S>> S minimum(S first, S second) {
		if (first.compareTo(second) < 0) {
			return first;
		} else {
			return second;
		}
	}

	@Override
	public final S getIncremented() {
		return copy().increment();
	}

	@Override
	public final S getDecremented() {
		return copy().decrement();
	}

	@Override
	public final S get() {
		return getThis();
	}

	@Override
	public final S getValue() {
		return getThis();
	}

	@Override
	public final S getDecoupledValue() {
		return copy();
	}

	protected final void postUpdate() {
		for (Observer<? super Void> observer : observers) {
			observer.notify(null);
		}
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
	public abstract S multiply(Value<?> value);

	@Override
	public abstract S divide(Value<?> value);

	public abstract boolean equals(double value);

	public abstract boolean equals(float value);

	public abstract boolean equals(int value);

	public abstract boolean equals(long value);

	public abstract int compareToAtSupportedPrecision(/*@ReadOnly*/Value<?> other);

	public abstract int getMultipliedPrimitive(int value);

	public abstract long getMultipliedPrimitive(long value);

	public abstract float getMultipliedPrimitive(float value);

	public abstract double getMultipliedPrimitive(double value);

	public abstract int getDividedPrimitive(int value);

	public abstract long getDividedPrimitive(long value);

	public abstract float getDividedPrimitive(float value);

	public abstract double getDividedPrimitive(double value);

	@Override
	public abstract int intValue();

	@Override
	public abstract long longValue();

	@Override
	public abstract float floatValue();

	@Override
	public abstract double doubleValue();
}
