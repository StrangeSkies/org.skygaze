package uk.co.elionline.gears.mathematics.geometry.matrix.builder.impl;

import uk.co.elionline.gears.mathematics.geometry.matrix.builder.MatrixBuilder;
import uk.co.elionline.gears.mathematics.geometry.matrix.builder.ValueMatrixBuilder;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.FloatValue;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.LongValue;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class MatrixBuilderImpl implements MatrixBuilder {
	@Override
	public ValueMatrixBuilder<IntValue> ints() {
		return new ValueMatrixBuilderImpl<>(IntValue.factory());
	}

	@Override
	public ValueMatrixBuilder<LongValue> longs() {
		return new ValueMatrixBuilderImpl<>(LongValue.factory());
	}

	@Override
	public ValueMatrixBuilder<FloatValue> floats() {
		return new ValueMatrixBuilderImpl<>(FloatValue.factory());
	}

	@Override
	public ValueMatrixBuilder<DoubleValue> doubles() {
		return new ValueMatrixBuilderImpl<>(DoubleValue.factory());
	}

	@Override
	public <V extends Value<V>> ValueMatrixBuilder<V> values(
			Factory<V> valueFactory) {
		return new ValueMatrixBuilderImpl<>(valueFactory);
	}
}
