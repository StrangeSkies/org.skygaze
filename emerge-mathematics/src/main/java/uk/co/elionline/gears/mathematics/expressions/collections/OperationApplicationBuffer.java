package uk.co.elionline.gears.mathematics.expressions.collections;

import uk.co.elionline.gears.mathematics.expressions.CompoundExpression;
import uk.co.elionline.gears.mathematics.functions.BinaryOperation;
import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.mathematics.functions.TransformationOperation;
import uk.co.elionline.gears.utilities.Utilities;

public class OperationApplicationBuffer<R, T> extends CompoundExpression<R>
		implements DoubleBuffer<R, T> {
	protected class BackExpression extends CompoundExpression<T> {
		@Override
		protected T evaluate() {
			return back;
		}

		@Override
		protected final void update() {
			super.update();
		}
	}

	private R front;
	private T back;
	private boolean isFlat;

	private final BinaryOperation<? extends R, ? super R, ? super T> operation;

	private final BackExpression backExpression;

	public OperationApplicationBuffer(R front, T back,
			BinaryOperation<? extends R, ? super R, ? super T> operation) {
		backExpression = createBackExpression();

		setFront(front);
		setBack(back);

		this.operation = operation;
	}

	public OperationApplicationBuffer(
			DoubleBuffer<? extends R, ? extends T> doubleBuffer,
			BinaryOperation<? extends R, ? super R, ? super T> operation) {
		this(doubleBuffer.getFront(), doubleBuffer.getBack(), operation);
	}

	public OperationApplicationBuffer(
			OperationApplicationBuffer<R, T> doubleBuffer) {
		this(doubleBuffer.getFront(), doubleBuffer.getBack(), doubleBuffer
				.getOperation());
	}

	public OperationApplicationBuffer(R front, T back,
			Function<? extends R, ? super T> function) {
		this(front, back, new TransformationOperation<R, T>(function));
	}

	public OperationApplicationBuffer(T back,
			Function<? extends R, ? super T> function) {
		this(function.applyTo(back), back, new TransformationOperation<R, T>(
				function));
	}

	public OperationApplicationBuffer(
			DoubleBuffer<? extends R, ? extends T> doubleBuffer,
			Function<? extends R, ? super T> function) {
		this(doubleBuffer.getFront(), doubleBuffer.getBack(), function);
	}

	protected BackExpression createBackExpression() {
		return new BackExpression();
	}

	@Override
	public BackExpression getBackExpression() {
		return backExpression;
	}

	public BinaryOperation<? extends R, ? super R, ? super T> getOperation() {
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
		getBackExpression().update();
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
	public void set(DoubleBuffer<? extends R, ? extends T> value) {
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
