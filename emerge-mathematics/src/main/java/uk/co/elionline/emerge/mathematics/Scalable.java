package uk.co.elionline.emerge.mathematics;

import uk.co.elionline.emerge.mathematics.values.Value;
import uk.co.elionline.emerge.utilities.Self;

public interface Scalable<S extends Scalable<S>> extends Self<S> {
	public S multiply(Value<?> value);

	public S multiply(int value);

	public S multiply(long value);

	public S multiply(float value);

	public S multiply(double value);

	public S divide(Value<?> value);

	public S divide(int value);

	public S divide(long value);

	public S divide(float value);

	public S divide(double value);

	public S getMultiplied(Value<?> value);

	public S getMultiplied(int value);

	public S getMultiplied(long value);

	public S getMultiplied(float value);

	public S getMultiplied(double value);

	public S getDivided(Value<?> value);

	public S getDivided(int value);

	public S getDivided(long value);

	public S getDivided(float value);

	public S getDivided(double value);
}
