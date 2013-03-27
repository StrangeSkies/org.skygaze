package uk.co.elionline.emerge.utilities;

public interface Property<T extends R, R> {
	public T set(R to);

	public T get();
}
