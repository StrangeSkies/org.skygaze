package uk.co.elionline.emerge.mathematics.values;

public final class DoubleValue extends ContinuousValue<DoubleValue> {
	private static final long serialVersionUID = 5916143538083079342L;

	private double value;

	public DoubleValue() {
	}

	public DoubleValue(Value<?> value) {
		super((Number) value);
	}

	public DoubleValue(Number value) {
		super(value);
	}

	@Override
	public final DoubleValue reciprocate() {
		value = 1 / value;

		return this;
	}

	@Override
	public final DoubleValue add(Value<?> value) {
		this.value += value.doubleValue();
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue subtract(Value<?> value) {
		this.value -= value.doubleValue();
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue negate() {
		value = -value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue multiply(int value) {
		this.value *= value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue multiply(long value) {
		this.value *= value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue multiply(float value) {
		this.value *= value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue multiply(double scalar) {
		this.value *= scalar;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue divide(int value) {
		this.value /= value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue divide(long value) {
		this.value /= value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue divide(float value) {
		this.value /= value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue divide(double value) {
		this.value /= value;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue divide(Value<?> value) {
		this.value = value.getDividedPrimitive(this.value);
		postUpdate();
		return this;
	}

	@Override
	public final double doubleValue() {
		return value;
	}

	@Override
	public final float floatValue() {
		return (float) value;
	}

	@Override
	public final int intValue() {
		return (int) value;
	}

	@Override
	public final long longValue() {
		return (long) value;
	}

	@Override
	public final String toString() {
		return new Double(value).toString();
	}

	@Override
	public final DoubleValue set(Value<?> value) {
		this.value = value.doubleValue();
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue setValue(Number value) {
		this.value = value.doubleValue();
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue multiply(Value<?> value) {
		this.value = value.getMultipliedPrimitive(this.value);
		postUpdate();
		return this;
	}

	@Override
	public final int compareToAtSupportedPrecision(Value<?> other) {
		return new Double(this.value).compareTo(new Double(other.doubleValue()));
	}

	@Override
	public final boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that instanceof Value<?>) {
			return equals((Value<?>) that);
		}
		if (that instanceof Number) {
			return ((Number) that).equals(this.value);
		}
		return false;
	}

	@Override
	protected final boolean equals(Value<?> that) {
		return ((Value<?>) that).equals(this.value);
	}

	@Override
	public final int hashCode() {
		return new Double(value).hashCode();
	}

	@Override
	public final boolean equals(double value) {
		return this.value == value;
	}

	@Override
	public final boolean equals(float value) {
		return this.value == value;
	}

	@Override
	public final boolean equals(int value) {
		return this.value == value;
	}

	@Override
	public final boolean equals(long value) {
		return this.value == value;
	}

	@Override
	public final DoubleValue increment() {
		value++;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue decrement() {
		value--;
		postUpdate();
		return this;
	}

	@Override
	public final DoubleValue copy() {
		return new DoubleValue(this);
	}

	@Override
	public final DoubleValue unitInTheLastPlaceAbove() {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			return this;
		}
		double absoluteValue = Math.abs(value);

		long nextValueLong = Double.doubleToLongBits(absoluteValue) + 1;
		double nextValue = Double.longBitsToDouble(nextValueLong);

		// if ended on bad number go down instead
		if (Double.isNaN(nextValue) || Double.isInfinite(nextValue)) {
			nextValueLong = nextValueLong - 2;
			nextValue = absoluteValue;
			absoluteValue = Double.longBitsToDouble(nextValueLong);
		}

		return new DoubleValue(Math.abs(nextValue - absoluteValue));
	}

	@Override
	public final DoubleValue unitInTheLastPlaceBelow() {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			return this;
		}
		double absoluteValue = Math.abs(value);

		long nextValueLong = Double.doubleToLongBits(absoluteValue) - 1;
		double nextValue = Double.longBitsToDouble(nextValueLong);

		// if ended on bad number go up instead
		if (Double.isNaN(nextValue) || Double.isInfinite(nextValue)) {
			nextValueLong = nextValueLong + 2;
			nextValue = absoluteValue;
			absoluteValue = Double.longBitsToDouble(nextValueLong);
		}

		return new DoubleValue(Math.abs(nextValue - absoluteValue));
	}

	@Override
	public final int getMultipliedPrimitive(int value) {
		return (int) (this.value * value);
	}

	@Override
	public final long getMultipliedPrimitive(long value) {
		return (long) (this.value * value);
	}

	@Override
	public final float getMultipliedPrimitive(float value) {
		return (float) (this.value * value);
	}

	@Override
	public final double getMultipliedPrimitive(double value) {
		return this.value * value;
	}

	@Override
	public final int getDividedPrimitive(int value) {
		return (int) (this.value * value);
	}

	@Override
	public final long getDividedPrimitive(long value) {
		return (long) (this.value * value);
	}

	@Override
	public final float getDividedPrimitive(float value) {
		return (float) (this.value * value);
	}

	@Override
	public final double getDividedPrimitive(double value) {
		return this.value * value;
	}

	public static DoubleValueFactory factory() {
		return DoubleValueFactory.instance();
	}
}
