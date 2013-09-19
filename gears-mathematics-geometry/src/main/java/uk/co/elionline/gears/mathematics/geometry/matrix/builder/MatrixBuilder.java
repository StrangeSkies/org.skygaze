package uk.co.elionline.gears.mathematics.geometry.matrix.builder;

import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.FloatValue;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.LongValue;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public interface MatrixBuilder {
	public ValueMatrixBuilder<IntValue> ints();

	public ValueMatrixBuilder<LongValue> longs();

	public ValueMatrixBuilder<FloatValue> floats();

	public ValueMatrixBuilder<DoubleValue> doubles();

	public <V extends Value<V>> ValueMatrixBuilder<V> values(
			Factory<V> valueFactory);
}
