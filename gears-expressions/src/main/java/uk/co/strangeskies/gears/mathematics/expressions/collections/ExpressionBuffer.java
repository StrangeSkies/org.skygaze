package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.function.BiFunction;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.utilities.Observer;

public class ExpressionBuffer<F extends Expression<?>, T> extends
		OperationApplicationBuffer<F, T> {
	private Observer<Expression<?>> backObserver;

	public ExpressionBuffer(T front, F back,
			BiFunction<? super T, ? super F, ? extends T> operation) {
		super(front, back, operation);
	}

	public ExpressionBuffer(DoubleBuffer<? extends F, ? extends T> doubleBuffer,
			BiFunction<? super T, ? super F, ? extends T> operation) {
		super(doubleBuffer, operation);
	}

	public ExpressionBuffer(F back, Function<? super F, ? extends T> function) {
		super(back, function);
	}

	public ExpressionBuffer(T front, F back,
			Function<? super F, ? extends T> function) {
		super(front, back, function);
	}

	public ExpressionBuffer(DoubleBuffer<? extends F, ? extends T> doubleBuffer,
			Function<? super F, ? extends T> function) {
		super(doubleBuffer, function);
	}

	public ExpressionBuffer(OperationApplicationBuffer<F, T> doubleBuffer) {
		super(doubleBuffer);
	}

	@Override
	public F setBack(F next) {
		if (getBack() != null) {
			getBack().removeObserver(getBackObserver());
		}

		if (next != null) {
			next.addObserver(nextBackObserver());
		}

		return super.setBack(next);
	}

	private Observer<Expression<?>> nextBackObserver() {
		return backObserver = new Observer<Expression<?>>() {
			@Override
			public void notify(Expression<?> message) {
				invalidateBack();
			}
		};
	}

	private Observer<Expression<?>> getBackObserver() {
		return backObserver;
	}
}
