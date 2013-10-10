package uk.co.strangeskies.gears.mathematics.expressions.collections;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.functions.BinaryOperation;
import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.utilities.Observer;

public class ExpressionBuffer<T, F extends Expression<?>> extends
		OperationApplicationBuffer<T, F> {
	private Observer<Expression<?>> backObserver;

	public ExpressionBuffer(T front, F back,
			BinaryOperation<? extends T, ? super T, ? super F> operation) {
		super(front, back, operation);
	}

	public ExpressionBuffer(DoubleBuffer<? extends T, ? extends F> doubleBuffer,
			BinaryOperation<? extends T, ? super T, ? super F> operation) {
		super(doubleBuffer, operation);
	}

	public ExpressionBuffer(F back, Function<? extends T, ? super F> function) {
		super(back, function);
	}

	public ExpressionBuffer(T front, F back,
			Function<? extends T, ? super F> function) {
		super(front, back, function);
	}

	public ExpressionBuffer(DoubleBuffer<? extends T, ? extends F> doubleBuffer,
			Function<? extends T, ? super F> function) {
		super(doubleBuffer, function);
	}

	public ExpressionBuffer(OperationApplicationBuffer<T, F> doubleBuffer) {
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

	public Observer<Expression<?>> nextBackObserver() {
		return backObserver = new Observer<Expression<?>>() {
			@Override
			public void notify(Expression<?> message) {
				invalidateBack();
			}
		};
	}

	public Observer<Expression<?>> getBackObserver() {
		return backObserver;
	}
}
