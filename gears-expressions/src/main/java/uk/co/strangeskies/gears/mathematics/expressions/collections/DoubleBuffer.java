package uk.co.strangeskies.gears.mathematics.expressions.collections;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;

public interface DoubleBuffer<T, R> extends Expression<R> {
	public abstract R setFront(R front);

	public abstract T setBack(T back);

	public abstract void set(T value);

	public abstract void set(DoubleBuffer<? extends T, ? extends R> value);

	public abstract R getFront();

	public abstract T getBack();

	public abstract Expression<T> getBackExpression();

	public abstract void push();

	public abstract boolean isFlat();
}