package uk.co.elionline.emerge.mathematics.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import uk.co.elionline.emerge.mathematics.Range;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector;
import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Factory;
import uk.co.elionline.emerge.utilities.Self;

public abstract class Bounds<S extends Bounds<S, V>, V extends Value<V>>
		implements Self<S> {
	ArrayList<Range<V>> ranges;

	public Bounds(int dimensions, Factory<V> valueFactory) {
		try {
			DimensionalityException.checkValid(dimensions);
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}
		ranges = createDataList(dimensions, valueFactory);
	}

	public Bounds(Vector<?, V> from, Vector<?, V> to) {
		try {
			DimensionalityException.checkEquivalence(from.getDimensions(),
					to.getDimensions());
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}
		ranges = new ArrayList<Range<V>>();

		Iterator<? extends V> fromIterator = from.getData().iterator();
		Iterator<? extends V> toIterator = to.getData().iterator();
		while (fromIterator.hasNext()) {
			ranges.add(Range.create(fromIterator.next(), toIterator.next()));
		}
	}

	public Bounds(Vector<?, V> from, Vector<?, V> to, int dimensions) {
		try {
			DimensionalityException
					.checkEquivalence(from.getDimensions(), dimensions);
			DimensionalityException.checkEquivalence(to.getDimensions(), dimensions);
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}
		ranges = new ArrayList<Range<V>>();

		Iterator<? extends V> fromIterator = from.getData().iterator();
		Iterator<? extends V> toIterator = to.getData().iterator();
		while (fromIterator.hasNext()) {
			ranges.add(Range.create(fromIterator.next(), toIterator.next()));
		}
	}

	public Bounds(Bounds<?, V> other) {
		ranges = new ArrayList<Range<V>>();
		for (Range<V> range : other.getData()) {
			ranges.add(range.copy());
		}
	}

	public Bounds(Bounds<?, V> other, int dimensions) {
		try {
			DimensionalityException
					.checkEquivalence(other.getDimension(), dimensions);
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}
		ranges = new ArrayList<Range<V>>();
		for (Range<V> range : other.getData()) {
			ranges.add(range.copy());
		}
	}

	public ArrayList<Range<V>> createDataList(int size, Factory<V> valueFactory) {
		ArrayList<Range<V>> ranges = new ArrayList<Range<V>>();
		for (int i = 0; i < size; i++) {
			ranges.add(Range.create(valueFactory.create(), valueFactory.create()));
		}
		return ranges;
	}

	public final List<Range<V>> getData() {
		return Collections.unmodifiableList(ranges);
	}

	public final Range<V> getRange(int index) {
		return getData().get(index);
	}

	public int getDimension() {
		return ranges.size();
	}

	public <M extends Vector<M, X>, X extends Value<X>> M getConfined(M value) {
		M confined = value.copy();
		return this.confine(confined);
	}

	public <M extends Vector<M, X>, X extends Value<X>> M confine(M value) {
		try {
			DimensionalityException.checkEquivalence(getDimension(),
					value.getDimensions());
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}
		Iterator<X> valueIterator = value.getData().iterator();
		for (Range<V> range : ranges) {
			range.confineComparable(valueIterator.next());
		}
		return value;
	}

	public S getCombinedWith(Bounds<?, V> other) throws DimensionalityException {
		return copy().combineWith(other);
	}

	public S combineWith(Bounds<?, V> other) {
		try {
			DimensionalityException.checkEquivalence(getDimension(),
					other.getDimension());
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}
		Iterator<? extends Range<V>> otherIterator = other.getData().iterator();
		for (Range<V> range : ranges) {
			range.extendThrough(otherIterator.next());
		}
		return getThis();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final S getThis() {
		return (S) this;
	}
}
