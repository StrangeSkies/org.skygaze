package uk.co.elionline.emerge.mathematics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import uk.co.elionline.emerge.utilities.Copyable;
import uk.co.elionline.emerge.utilities.NaturalComparator;
import uk.co.elionline.emerge.utilities.Property;
import uk.co.elionline.emerge.utilities.Self;

public class Range<T> implements Self<Range<T>>, Copyable<Range<T>> {
	private T from;
	private T to;

	private boolean fromInclusive;
	private boolean toInclusive;

	private final Comparator<? super T> comparator;

	protected Range(T from, T to, Comparator<? super T> comparator) {
		this.from = from;
		this.to = to;

		this.fromInclusive = true;
		this.toInclusive = true;

		this.comparator = comparator;
	}

	public static <T> Range<T> create(T from, T to,
			Comparator<? super T> comparator) {
		return new Range<T>(from, to, comparator);
	}

	public static <T extends Comparable<? super T>> Range<T> create(T from, T to) {
		return new Range<T>(from, to, new NaturalComparator<T>());
	}

	public T getFrom() {
		return from;
	}

	public T getTo() {
		return to;
	}

	public Range<T> setFrom(T from) {
		this.from = from;

		return this;
	}

	public Range<T> setTo(T to) {
		this.to = to;

		return this;
	}

	public boolean isFromInclusive() {
		return fromInclusive;
	}

	public boolean isToInclusive() {
		return toInclusive;
	}

	public Range<T> setFromInclusive(boolean fromInclusive) {
		this.fromInclusive = fromInclusive;

		return this;
	}

	public Range<T> setToInclusive(boolean toInclusive) {
		this.toInclusive = toInclusive;

		return this;
	}

	public Range<T> setInclusive(boolean fromInclusive, boolean toInclusive) {
		return setFromInclusive(fromInclusive).setToInclusive(toInclusive);
	}

	public Comparator<? super T> getComparator() {
		return comparator;
	}

	public boolean isEmpty() {
		return !fromInclusive && !toInclusive && from.equals(to);
	}

	public boolean contains(final T value) {
		return containsComparable(new Comparable<T>() {
			@Override
			public int compareTo(T other) {
				return comparator.compare(value, other);
			}
		});
	}

	public boolean containsComparable(Comparable<? super T> value) {
		int fromCompare = value.compareTo(from);
		int toCompare = value.compareTo(to);

		if (fromInclusive) {
			if (toInclusive) {
				return fromCompare >= 0 && toCompare <= 0;
			} else {
				return fromCompare >= 0 && toCompare < 0;
			}
		} else {
			if (toInclusive) {
				return fromCompare > 0 && toCompare <= 0;
			} else {
				return fromCompare > 0 && toCompare < 0;
			}
		}
	}

	public boolean containsAll(Collection<? extends T> values) {
		for (T value : values) {
			if (!contains(value)) {
				return false;
			}
		}
		return true;
	}

	public boolean containsAllComparables(
			Collection<? extends Comparable<? super T>> values) {
		for (Comparable<? super T> value : values) {
			if (!containsComparable(value)) {
				return false;
			}
		}
		return true;
	}

	public boolean containsSome(Collection<? extends T> values) {
		for (T value : values) {
			if (contains(value)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsSomeComparables(
			Collection<? extends Comparable<? super T>> values) {
		for (Comparable<? super T> value : values) {
			if (containsComparable(value)) {
				return true;
			}
		}
		return false;
	}

	public int containsCount(Collection<? extends T> values) {
		int containsCount = 0;
		for (T value : values) {
			if (contains(value)) {
				containsCount++;
			}
		}
		return containsCount;
	}

	public int containsCountComparables(
			Collection<? extends Comparable<? super T>> values) {
		int containsCount = 0;
		for (Comparable<? super T> value : values) {
			if (!containsComparable(value)) {
				containsCount++;
			}
		}
		return containsCount;
	}

	public boolean isValueBelow(T value) {
		if (fromInclusive) {
			return comparator.compare(value, from) < 0;
		} else {
			return comparator.compare(value, from) <= 0;
		}
	}

	public boolean isValueAbove(T value) {
		if (toInclusive) {
			return comparator.compare(value, to) > 0;
		} else {
			return comparator.compare(value, to) >= 0;
		}
	}

	public boolean isValueBelowComparable(Comparable<? super T> value) {
		if (fromInclusive) {
			return value.compareTo(from) < 0;
		} else {
			return value.compareTo(from) <= 0;
		}
	}

	public boolean isValueAboveComparable(Comparable<? super T> value) {
		if (toInclusive) {
			return value.compareTo(to) > 0;
		} else {
			return value.compareTo(to) >= 0;
		}
	}

	public T getConfined(T value) {
		if (isEmpty()) {
			return null;
		}
		if (contains(value)) {
			return value;
		}
		if (comparator.compare(from, value) > 0) {
			return from;
		}
		return to;
	}

	public <M extends Property<? extends T, ? super T>> M confine(M value) {
		if (isEmpty()) {
			return null;
		}
		if (isValueAbove(value.get())) {
			value.set(to);
		} else if (isValueBelow(value.get())) {
			value.set(from);
		}
		return value;
	}

	public <M extends Property<? extends Comparable<? super T>, ? super T>> M confineComparable(
			M value) {
		if (isEmpty()) {
			return null;
		}
		if (isValueAboveComparable(value.get())) {
			value.set(to);
		} else if (isValueBelowComparable(value.get())) {
			value.set(from);
		}
		return value;
	}

	public Range<T> getExtendedThrough(Range<? extends T> other) {
		return copy().extendThrough(other);
	}

	public Range<T> extendThrough(Range<? extends T> other) {
		int compareFrom = comparator.compare(other.getFrom(), getFrom());
		if (compareFrom == 0) {
			setFromInclusive(isFromInclusive() || other.isFromInclusive());
		} else if (compareFrom < 0) {
			setFrom(other.getFrom());
			setFromInclusive(other.isFromInclusive());
		}

		int compareTo = comparator.compare(other.getTo(), getTo());
		if (compareTo == 0) {
			setToInclusive(isToInclusive() || other.isToInclusive());
		} else if (compareTo > 0) {
			setTo(other.getTo());
			setToInclusive(other.isToInclusive());
		}

		return getThis();
	}

	public Range<T> getExtendedThrough(T other, boolean inclusive) {
		return copy().extendThrough(other, inclusive);
	}

	public Range<T> extendThrough(T other, boolean inclusive) {
		int compareFrom = comparator.compare(other, getFrom());
		int compareTo = comparator.compare(other, getTo());

		if (inclusive) {
			if (compareFrom == 0) {
				setFromInclusive(true);
			}
			if (compareTo == 0) {
				setToInclusive(true);
			}
		}

		if (compareFrom < 0) {
			setFrom(other);
			setFromInclusive(inclusive);
		} else if (compareTo > 0) {
			setTo(other);
			setToInclusive(inclusive);
		}

		return getThis();
	}

	@Override
	public Range<T> copy() {
		return new Range<>(from, to, comparator).setInclusive(isFromInclusive(),
				isToInclusive());
	}

	@Override
	public final Range<T> getThis() {
		return this;
	}

	public <S extends Collection<? extends T>> S retainAllWithin(S values) {
		Iterator<? extends T> valueIterator = values.iterator();

		while (valueIterator.hasNext()) {
			if (!contains(valueIterator.next())) {
				valueIterator.remove();
			}
		}

		return values;
	}

	public <U extends T, S extends Collection<U>> List<U> getAllWithin(S values) {
		return retainAllWithin(new ArrayList<>(values));
	}

	public <S extends Collection<? extends T>> S removeAllWithin(S values) {
		Iterator<? extends T> valueIterator = values.iterator();

		while (valueIterator.hasNext()) {
			if (contains(valueIterator.next())) {
				valueIterator.remove();
			}
		}

		return values;
	}

	public <U extends T, S extends Collection<U>> List<U> getWithoutAllWithin(
			S values) {
		return removeAllWithin(new ArrayList<>(values));
	}

	public <S extends Collection<? extends Comparable<? super T>>> S retainAllComparablesWithin(
			S values) {
		Iterator<? extends Comparable<? super T>> valueIterator = values.iterator();

		while (valueIterator.hasNext()) {
			if (!containsComparable(valueIterator.next())) {
				valueIterator.remove();
			}
		}

		return values;
	}

	public <U extends Comparable<? super T>, S extends Collection<U>> List<U> getAllComparablesWithin(
			S values) {
		return retainAllComparablesWithin(new ArrayList<>(values));
	}

	public <S extends Collection<? extends Comparable<? super T>>> S removeAllComparablesWithin(
			S values) {
		Iterator<? extends Comparable<? super T>> valueIterator = values.iterator();

		while (valueIterator.hasNext()) {
			if (containsComparable(valueIterator.next())) {
				valueIterator.remove();
			}
		}

		return values;
	}

	public <U extends Comparable<? super T>, S extends Collection<U>> List<U> getWithoutAllComparablesWithin(
			S values) {
		return removeAllComparablesWithin(new ArrayList<>(values));
	}
}
