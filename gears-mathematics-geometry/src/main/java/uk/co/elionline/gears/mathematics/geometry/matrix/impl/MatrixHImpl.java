package uk.co.elionline.gears.mathematics.geometry.matrix.impl;

import java.util.List;

import uk.co.elionline.gears.mathematics.functions.collections.ListTransformOnceView;
import uk.co.elionline.gears.mathematics.functions.collections.ListTransformationFunction;
import uk.co.elionline.gears.mathematics.functions.collections.SubListFunction;
import uk.co.elionline.gears.mathematics.geometry.matrix.MatrixH;
import uk.co.elionline.gears.mathematics.geometry.matrix.MatrixNN;
import uk.co.elionline.gears.mathematics.geometry.matrix.MatrixS;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.VectorH;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.VectorH.Type;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.impl.VectorHNImpl;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;
import uk.co.elionline.gears.utilities.collections.SubList;

public abstract class MatrixHImpl<S extends MatrixH<S, V>, V extends Value<V>>
		extends /*@ReadOnly*/MatrixImpl<S, V> implements MatrixH<S, V> {
	public MatrixHImpl(int size, Order order, Factory<V> valueFactory) {
		super(size + 1, size, order, valueFactory);

		for (int i = 0; i < getProjectedDimensions(); i++) {
			getElement(i, i).setValue(1);
		}
	}

	public MatrixHImpl(int size, Factory<V> valueFactory) {
		super(size + 1, size, valueFactory);

		for (int i = 0; i < getProjectedDimensions(); i++) {
			getElement(i, i).setValue(1);
		}
	}

	public MatrixHImpl(Order order, List<? extends List<? extends V>> values) {
		super(order, values);
	}

	public MatrixHImpl(List<? extends List<? extends V>> values) {
		super(values);
	}

	@Override
	protected void finalise() {
		int size = getColumnSize();

		resizeColumnsImplementation(size + 1);

		getElement(size, size).setValue(1);

		assertIsSquare(this);
	}

	@Override
	public boolean isResizable() {
		return false;
	}

	@Override
	public int getDimensions() {
		return getMinorSize();
	}

	@Override
	public int getProjectedDimensions() {
		return getDimensions() - 1;
	}

	@Override
	public MatrixNN<V> getMutableMatrix() {
		List<List<V>> dataView;

		if (getOrder() == Order.ColumnMajor) {
			dataView = new ListTransformOnceView<List<V>, List<V>>(getData2(),
					new SubListFunction<V>(0, getProjectedDimensions()));
		} else {
			dataView = new SubList<>(getData2(), 0, getProjectedDimensions());
		}

		return new MatrixNNImpl<V>(getOrder(), dataView);
	}

	protected List<List<V>> getTransformationData2() {
		return ListTransformationFunction.applyTo(
				getData2().subList(0, getProjectedDimensions()),
				new SubListFunction<V>(0, getProjectedDimensions()));
	}

	@Override
	public abstract MatrixS<?, V> getTransformationMatrix();

	@Override
	public S translate(Vector<?, ?> translation) {
		getColumnVector(getProjectedDimensions()).translate(translation);

		return getThis();
	}

	@Override
	public S getTranslated(Vector<?, ?> translation) {
		return copy().translate(translation);
	}

	@Override
	public S preTranslate(Vector<?, ?> translation) {
		// TODO implement pre-rotation
		return null;
	}

	@Override
	public S getPreTranslated(Vector<?, ?> translation) {
		return copy().preTranslate(translation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends VectorH<?, V>> getColumnVectors() {
		return (List<? extends VectorH<?, V>>) super.getColumnVectors();
	}

	@Override
	public VectorH<?, V> getColumnVector(int column) {
		return new VectorHNImpl<V>(column == getDimensions() - 1 ? Type.Absolute
				: Type.Relative, getColumnVectorData(column).subList(0,
				getProjectedDimensions()));
	}

	@Override
	public V getDeterminant() {
		return MatrixSImpl.getDeterminant(this);
	}

	@Override
	public S transpose() {
		throw new UnsupportedOperationException();
	}
}
