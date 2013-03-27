package uk.co.elionline.gears.mathematics;

import java.util.ListIterator;

public class RangeIterator<T extends Comparable<? super T> & Incrementable<? extends T>>
		implements ListIterator<T> {
	Range<T> range;
	T position;
	int index;

	public RangeIterator(Range<T> range) {
		this.range = range;
		position = range.getFrom().copy();
	}

	@Override
	public void add(T arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasNext() {
		return range.contains(position);
	}

	@Override
	public boolean hasPrevious() {
		return range.contains(position.getDecremented());
	}

	@Override
	public T next() {
		T previous = position;
		position = position.getIncremented();
		index++;
		return previous;
	}

	@Override
	public int nextIndex() {
		return index;
	}

	@Override
	public T previous() {
		position = position.getDecremented();
		index--;
		return position;
	}

	@Override
	public int previousIndex() {
		return index - 1;
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(T arg0) {
		throw new UnsupportedOperationException();
	}
}
