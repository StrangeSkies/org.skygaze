package uk.co.elionline.gears.mathematics.geometry.matrix.builder.impl;

import uk.co.elionline.gears.mathematics.geometry.matrix.builder.ValueMatrixBuilder;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector3;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector4;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl.Vector2Impl;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl.Vector3Impl;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl.Vector4Impl;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;

public class ValueMatrixBuilderImpl<V extends Value<V>> implements
		ValueMatrixBuilder<V> {
	private final Factory<V> valueFactory;

	public ValueMatrixBuilderImpl(Factory<V> valueFactory) {
		this.valueFactory = valueFactory;
	}

	protected Factory<V> getValueFactory() {
		return valueFactory;
	}

	@Override
	public Vector2<V> vector2() {
		return new Vector2Impl<>(getValueFactory());
	}

	@Override
	public Vector3<V> vector3() {
		return new Vector3Impl<>(getValueFactory());
	}

	@Override
	public Vector4<V> vector4() {
		return new Vector4Impl<>(getValueFactory());
	}
}
