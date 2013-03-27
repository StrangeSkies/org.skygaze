package uk.co.elionline.emerge.mathematics;

public interface Incrementor<T> {
	public T increment(T value);

	public T decrement(T value);

	public T getIncremented(T value);

	public T getDecremented(T value);
}
