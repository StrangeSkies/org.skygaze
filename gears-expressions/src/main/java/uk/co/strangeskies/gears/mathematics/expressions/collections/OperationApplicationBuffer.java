package uk.co.strangeskies.gears.mathematics.expressions.collections;

import java.util.function.BiFunction;
import java.util.function.Function;

import uk.co.strangeskies.gears.mathematics.expressions.CompoundExpression;
import uk.co.strangeskies.gears.utilities.Utilities;
import uk.co.strangeskies.gears.utilities.functions.TransformationOperation;

public class OperationApplicationBuffer<T, R> extends CompoundExpression<R>
		implements DoubleBuffer<T, R> {
	protected class BackExpression extends CompoundExpression<T> {
		@Override
		protected T evaluate() {
			return back;
		}

		public void forwardUpdate() {
			update();
		}
	}

	private R front;
	private T back;
	private boolean isFlat;

	private final BiFunction<? super R, ? super T, ? extends R> operation;

	private final BackExpression backExpression;

	public OperationApplicationBuffer(R front, T back,
			BiFunction<? super R, ? super T, ? extends R> operation) {
		backExpression = createBackExpression();

		setFront(front);
		setBack(back);

		this.operation = operation;
	}

	public OperationApplicationBuffer(
			DoubleBuffer<? extends T, ? extends R> doubleBuffer,
			BiFunction<? super R, ? super T, ? extends R> operation) {
		this(doubleBuffer.getFront(), doubleBuffer.getBack(), operation);
	}

	public OperationApplicationBuffer(
			OperationApplicationBuffer<T, R> doubleBuffer) {
		this(doubleBuffer.getFront(), doubleBuffer.getBack(), doubleBuffer
				.getOperation());
	}

	public OperationApplicationBuffer(R front, T back,
			Function<? super T, ? extends R> function) {
		this(front, back, new TransformationOperation<R, T>(function));
	}

	public OperationApplicationBuffer(T back,
			Function<? super T, ? extends R> function) {
		this(function.apply(back), back,
				new TransformationOperation<R, T>(function));
	}

	public OperationApplicationBuffer(
			DoubleBuffer<? extends T, ? extends R> doubleBuffer,
			Function<? super T, ? extends R> function) {
		this(doubleBuffer.getFront(), doubleBuffer.getBack(), function);
	}

	protected BackExpression createBackExpression() {
		return new BackExpression();
	}

	@Override
	public BackExpression getBackExpression() {
		return backExpression;
	}

	public BiFunction<? super R, ? super T, ? extends R> getOperation() {
		return operation;
	}

	@Override
	protected R evaluate() {
		return getFront();
	}

	/**
	 * This must be called to notify the buffer if the front item is mutable and
	 * has been modified externally.
	 */
	public void invalidateFront() {
		update();
		isFlat = false;
	}

	/**
	 * This must be called to notify the buffer if the back item is mutable and
	 * has been modified externally.
	 */
	public void invalidateBack() {
		getBackExpression().forwardUpdate();
		isFlat = false;
	}

	@Override
	public R setFront(R front) {
		R previous = this.front;
		this.front = front;

		if (!Utilities.areEqual(this.front, previous)) {
			invalidateFront();
		}

		return previous;
	}

	@Override
	public T setBack(T back) {
		T previous = this.back;
		this.back = back;

		if (!Utilities.areEqual(this.back, previous)) {
			invalidateBack();
		}

		return previous;
	}

	@Override
	public void set(T value) {
		setBack(value);
		push();
		isFlat = true;
	}

	@Override
	public void set(DoubleBuffer<? extends T, ? extends R> value) {
		setFront(value.getFront());
		setBack(value.getBack());

		isFlat = value.isFlat();
	}

	@Override
	public R getFront() {
		return front;
	}

	@Override
	public T getBack() {
		return back;
	}

	@Override
	public void push() {
		if (!isFlat) {
			setFront(getOperation().apply(getFront(), getBack()));
			invalidateFront();
			isFlat = true;
		}
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}

		if (!(that instanceof OperationApplicationBuffer<?, ?>)) {
			return false;
		}

		DoubleBuffer<?, ?> thatDoubleBuffer = (DoubleBuffer<?, ?>) that;

		R thisFront = this.getFront();
		T thisBack = this.getBack();
		Object thatFront = thatDoubleBuffer.getFront();
		Object thatBack = thatDoubleBuffer.getBack();

		return Utilities.areEqual(thisFront, thatFront)
				&& Utilities.areEqual(thisBack, thatBack);
	}

	@Override
	public int hashCode() {
		int hashCode = getFront().hashCode() + getBack().hashCode() * 29;

		return hashCode;
	}

	@Override
	public boolean isFlat() {
		return isFlat;
	}

	@Override
	public String toString() {
		return "[" + getBack() + "] => [" + getFront() + "]";
	}
}
