package uk.co.elionline.gears.utilities;

/**
 * A basic abstract implementation of the decorator pattern. Derived classes
 * should try to implement some common interface with T.
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 */
public abstract class Decorator<T> {
	private final T component;

	public Decorator(T component) {
		this.component = component;
	}

	protected T getComponent() {
		return component;
	}

	@Override
	public String toString() {
		return getComponent().toString();
	}
}
