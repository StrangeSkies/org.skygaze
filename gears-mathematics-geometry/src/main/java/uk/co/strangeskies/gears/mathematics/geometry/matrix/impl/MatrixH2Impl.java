package uk.co.strangeskies.gears.mathematics.geometry.matrix.impl;

import java.util.ArrayList;
import java.util.List;

import uk.co.strangeskies.gears.mathematics.geometry.matrix.Matrix2;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.MatrixH2;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector3;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.VectorH2;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.VectorH.Type;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.impl.Vector3Impl;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.impl.VectorH2Impl;
import uk.co.strangeskies.gears.mathematics.values.Value;
import uk.co.strangeskies.gears.utilities.Factory;

public class MatrixH2Impl<V extends Value<V>> extends
		MatrixHImpl<MatrixH2<V>, V> implements MatrixH2<V> {
	public MatrixH2Impl(Order order, Factory<V> valueFactory) {
		super(2, order, valueFactory);
	}

	public MatrixH2Impl(Factory<V> valueFactory) {
		super(2, valueFactory);
	}

	public MatrixH2Impl(Order order, List<? extends List<? extends V>> values) {
		super(order, values);
	}

	public MatrixH2Impl(List<? extends List<? extends V>> values) {
		super(values);
	}

	@Override
	public Matrix2<V> getTransformationMatrix() {
		return new Matrix2Impl<V>(getOrder(), getTransformationData2());
	}

	@Override
	public MatrixH2<V> copy() {
		return new MatrixH2Impl<V>(getData2());
	}

	@Override
	public MatrixH2<V> getPreRotated(Value<?> value) {
		return copy().preRotate(value);
	}

	@Override
	public MatrixH2<V> getPreRotated(Value<?> angle, Vector2<?> centre) {
		return copy().preRotate(angle, centre);
	}

	@Override
	public MatrixH2<V> getRotated(Value<?> angle) {
		return copy().rotate(angle);
	}

	@Override
	public MatrixH2<V> rotate(Value<?> angle) {
		getTransformationMatrix().rotate(angle);

		return getThis();
	}

	@Override
	public MatrixH2<V> getRotated(Value<?> angle, Vector2<?> centre) {
		return copy().rotate(angle, centre);
	}

	@Override
	public MatrixH2<V> rotate(Value<?> angle, Vector2<?> centre) {
		getTransformationMatrix().rotate(angle, centre);

		return getThis();
	}

	@Override
	public MatrixH2<V> preRotate(Value<?> value) {
		getTransformationMatrix().preRotate(value);

		return getThis();
	}

	@Override
	public MatrixH2<V> preRotate(Value<?> angle, Vector2<?> centre) {
		getTransformationMatrix().preRotate(angle, centre);

		return getThis();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Vector3<V>> getRowVectors() {
		return (List<Vector3<V>>) super.getRowVectors();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<VectorH2<V>> getColumnVectors() {
		return (List<VectorH2<V>>) super.getColumnVectors();
	}

	@Override
	public final Vector3<V> getRowVector(int row) {
		return new Vector3Impl<V>(getRowVectorData(row));
	}

	@Override
	public final VectorH2<V> getColumnVector(int column) {
		return new VectorH2Impl<V>(column == getDimensions() - 1 ? Type.Absolute
				: Type.Relative, getColumnVectorData(getProjectedDimensions()).subList(
				0, getProjectedDimensions()));
	}

	@Override
	public final Vector<?, V> getMajorVector(int index) {
		List<V> majorElements = getData2().get(index);

		if (getOrder() == Order.ColumnMajor) {
			majorElements = majorElements.subList(0, getProjectedDimensions());

			Type newType;
			if (index == getProjectedDimensions()) {
				newType = Type.Absolute;
			} else {
				newType = Type.Relative;
			}

			return new VectorH2Impl<V>(newType, majorElements);
		} else {
			return new Vector3Impl<V>(getData2().get(index));
		}
	}

	@Override
	public final Vector<?, V> getMinorVector(int index) {
		List<V> minorElements = new ArrayList<V>();
		for (List<V> elements : getData2()) {
			minorElements.add(elements.get(index));
		}

		if (getOrder() == Order.RowMajor) {
			minorElements = minorElements.subList(0, getProjectedDimensions());

			Type newType;
			if (index == getProjectedDimensions()) {
				newType = Type.Absolute;
			} else {
				newType = Type.Relative;
			}

			return new VectorH2Impl<V>(newType, minorElements);
		} else {
			return new Vector3Impl<V>(getData2().get(index));
		}
	}
}
