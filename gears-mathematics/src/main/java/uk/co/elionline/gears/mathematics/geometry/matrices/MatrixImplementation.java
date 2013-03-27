package uk.co.elionline.gears.mathematics.geometry.matrices;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import uk.co.elionline.gears.mathematics.expressions.CompoundExpression;
import uk.co.elionline.gears.mathematics.expressions.IdentityExpression;
import uk.co.elionline.gears.mathematics.functions.AssignmentOperation;
import uk.co.elionline.gears.mathematics.functions.BinaryOperation;
import uk.co.elionline.gears.mathematics.functions.Function;
import uk.co.elionline.gears.mathematics.functions.NumberToValueFunction;
import uk.co.elionline.gears.mathematics.functions.UnaryOperation;
import uk.co.elionline.gears.mathematics.functions.collections.ListTransformationView;
import uk.co.elionline.gears.mathematics.functions.collections.UnmodifiableListFunction;
import uk.co.elionline.gears.mathematics.geometry.DimensionalityException;
import uk.co.elionline.gears.mathematics.values.DoubleArrayListView;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.DoubleValueFactory;
import uk.co.elionline.gears.mathematics.values.FloatArrayListView;
import uk.co.elionline.gears.mathematics.values.FloatValue;
import uk.co.elionline.gears.mathematics.values.FloatValueFactory;
import uk.co.elionline.gears.mathematics.values.IntArrayListView;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.IntValueFactory;
import uk.co.elionline.gears.mathematics.values.LongArrayListView;
import uk.co.elionline.gears.mathematics.values.LongValue;
import uk.co.elionline.gears.mathematics.values.LongValueFactory;
import uk.co.elionline.gears.mathematics.values.Value;
import uk.co.elionline.gears.utilities.Factory;
import uk.co.elionline.gears.utilities.collections.MergeIndicesListView;
import uk.co.elionline.gears.utilities.collections.NullPointerInCollectionException;

public abstract class MatrixImplementation<S extends Matrix<S, V>, V extends Value<V>>
		extends CompoundExpression<S> implements Matrix<S, V> {
	private static Order defaultOrder = Order.ColumnMajor;

	private List<List<V>> data;

	private IdentityExpression<Order> order;

	/* All constructors must go through here */
	private MatrixImplementation(Order order) {
		if (order == null) {
			throw new IllegalArgumentException(new NullPointerException());
		}

		data = new ArrayList<>();

		this.order = new IdentityExpression<>(order);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			Factory<V> valueFactory) {
		this(order);

		try {
			if (valueFactory == null) {
				throw new NullPointerException();
			}

			DimensionalityException.checkValid(rows);
			DimensionalityException.checkValid(columns);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		int major;
		int minor;
		if (order == Order.RowMajor) {
			major = columns;
			minor = rows;
		} else {
			major = rows;
			minor = columns;
		}

		for (int i = 0; i < major; i++) {
			List<V> elements = new ArrayList<V>();
			data.add(elements);
			for (int j = 0; j < minor; j++) {
				elements.add(valueFactory.create());
			}
		}

		finalise();
	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory) {
		this(rows, columns, getDefaultOrder(), valueFactory);
	}

	public MatrixImplementation(Factory<V> valueFactory, Matrix<?, ?> other) {
		this(other.getOrder(), valueFactory, other.getData2());
	}

	public MatrixImplementation(Matrix<?, V> other) {
		this(other.getOrder(), other.getData2());
	}

	public <I> MatrixImplementation(int rows, int columns, Order order,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		this(order);

		try {
			if (creationOperation == null || values == null || order == null) {
				throw new NullPointerException();
			}
			NullPointerInCollectionException.checkList(values);

			DimensionalityException.checkValid(rows);
			DimensionalityException.checkValid(columns);
			DimensionalityException.checkEquivalence(rows * columns, values.size());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		int major;
		int minor;
		if (order == Order.RowMajor) {
			major = columns;
			minor = rows;
		} else {
			major = rows;
			minor = columns;
		}

		Iterator<? extends I> valueIterator = values.iterator();
		for (int i = 0; i < major; i++) {
			List<V> elements = new ArrayList<V>();
			data.add(elements);
			for (int j = 0; j < minor; j++) {
				elements.add(creationOperation.apply(valueIterator.next()));
			}
		}

		finalise();
	}

	public <I> MatrixImplementation(int rows, int columns,
			List<? extends I> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		this(rows, columns, getDefaultOrder(), values, creationOperation);
	}

	public <I> MatrixImplementation(Order order,
			List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		this(order);

		try {
			if (creationOperation == null || values == null || order == null) {
				throw new NullPointerException();
			}
			NullPointerInCollectionException.checkList(values);

			DimensionalityException.checkValid(values.size());

			Iterator<? extends List<? extends I>> majorIterator = values.iterator();

			List<? extends I> firstMajor = majorIterator.next();
			DimensionalityException.checkValid(firstMajor.size());

			NullPointerInCollectionException.checkList(firstMajor);

			while (majorIterator.hasNext()) {
				List<? extends I> major = majorIterator.next();
				DimensionalityException.checkEquivalence(firstMajor.size(),
						major.size());

				NullPointerInCollectionException.checkList(major);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		for (List<? extends I> major : values) {
			List<V> elements = new ArrayList<V>();
			data.add(elements);
			for (I value : major) {
				elements.add(creationOperation.apply(value));
			}
		}

		finalise();
	}

	public <I> MatrixImplementation(List<? extends List<? extends I>> values,
			UnaryOperation<? extends V, ? super I> creationOperation) {
		this(getDefaultOrder(), values, creationOperation);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			final Factory<V> valueFactory, List<? extends Value<?>> values) {
		this(rows, columns, order, values, new UnaryOperation<V, Value<?>>() {
			@Override
			public V apply(Value<?> operand) {
				return valueFactory.create().set(operand);
			}
		});
	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory,
			List<? extends Value<?>> values) {
		this(rows, columns, getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(Order order, final Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		this(order, values, new UnaryOperation<V, Value<?>>() {
			@Override
			public V apply(Value<?> operand) {
				return valueFactory.create().set(operand);
			}
		});
	}

	public MatrixImplementation(Factory<V> valueFactory,
			List<? extends List<? extends Value<?>>> values) {
		this(getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			Factory<V> valueFactory, Value<?>... values) {
		this(rows, columns, order, valueFactory, Arrays.asList(values));
	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory,
			Value<?>... values) {
		this(rows, columns, getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			List<? extends Value<V>> values) {
		this(rows, columns, order, values, new UnaryOperation<V, Value<V>>() {
			@Override
			public V apply(Value<V> operand) {
				return operand.copy();
			}
		});
	}

	public MatrixImplementation(int rows, int columns,
			List<? extends Value<V>> values) {
		this(rows, columns, getDefaultOrder(), values);
	}

	public MatrixImplementation(Order order,
			List<? extends List<? extends Value<V>>> values) {
		this(order, values, new UnaryOperation<V, Value<V>>() {
			@Override
			public V apply(Value<V> operand) {
				return operand.copy();
			}
		});
	}

	public MatrixImplementation(List<? extends List<? extends Value<V>>> values) {
		this(getDefaultOrder(), values);
	}

	@SafeVarargs
	public MatrixImplementation(int rows, int columns, Order order,
			Value<V>... values) {
		this(rows, columns, order, Arrays.asList(values));
	}

	@SafeVarargs
	public MatrixImplementation(int rows, int columns, Value<V>... values) {
		this(rows, columns, getDefaultOrder(), values);
	}

	public MatrixImplementation(int rows, int columns,
			final boolean copyByReference, Order order, List<? extends V> values) {
		this(rows, columns, order, values, new UnaryOperation<V, V>() {
			@Override
			public V apply(V operand) {
				if (copyByReference) {
					return operand;
				} else {
					return operand.copy();
				}
			}
		});
	}

	public MatrixImplementation(int rows, int columns,
			final boolean copyByReference, List<? extends V> values) {
		this(rows, columns, copyByReference, getDefaultOrder(), values);
	}

	public MatrixImplementation(final boolean copyByReference, Order order,
			List<? extends List<? extends V>> values) {
		this(order, values, new UnaryOperation<V, V>() {
			@Override
			public V apply(V operand) {
				if (copyByReference) {
					return operand;
				} else {
					return operand.copy();
				}
			}
		});
	}

	public MatrixImplementation(boolean copyByReference,
			List<? extends List<? extends V>> values) {
		this(copyByReference, getDefaultOrder(), values);
	}

	@SafeVarargs
	public MatrixImplementation(int rows, int columns, boolean copyByReference,
			Order order, V... values) {
		this(rows, columns, copyByReference, order, Arrays.asList(values));
	}

	@SafeVarargs
	public MatrixImplementation(int rows, int columns, boolean copyByReference,
			V... values) {
		this(rows, columns, copyByReference, getDefaultOrder(), values);
	}

	@SafeVarargs
	public MatrixImplementation(int rows, int columns, Order order,
			Factory<V> valueFactory, Number... values) {
		this(rows, columns, true, order, new ListTransformationView<>(
				Arrays.asList(values), new NumberToValueFunction<V>(valueFactory)));

	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory,
			Number... values) {
		this(rows, columns, getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			Factory<V> valueFactory, int[] values) {
		this(rows, columns, true, order, new IntArrayListView<>(values,
				valueFactory));
	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory,
			int[] values) {
		this(rows, columns, getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			Factory<V> valueFactory, long[] values) {
		this(rows, columns, true, order, new LongArrayListView<>(values,
				valueFactory));
	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory,
			long[] values) {
		this(rows, columns, getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			Factory<V> valueFactory, float[] values) {
		this(rows, columns, true, order, new FloatArrayListView<>(values,
				valueFactory));
	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory,
			float[] values) {
		this(rows, columns, getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(int rows, int columns, Order order,
			Factory<V> valueFactory, double[] values) {
		this(rows, columns, true, order, new DoubleArrayListView<>(values,
				valueFactory));
	}

	public MatrixImplementation(int rows, int columns, Factory<V> valueFactory,
			double[] values) {
		this(rows, columns, getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(Order order, final Factory<V> valueFactory,
			int[]... values) {
		this(true, order, new ListTransformationView<>(Arrays.asList(values),
				new Function<List<V>, int[]>() {
					@Override
					public List<V> applyTo(int[] input) {
						return new IntArrayListView<>(input, valueFactory);
					};
				}));
	}

	public MatrixImplementation(Factory<V> valueFactory, int[]... values) {
		this(getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(Order order, final Factory<V> valueFactory,
			long[]... values) {
		this(true, order, new ListTransformationView<>(Arrays.asList(values),
				new Function<List<V>, long[]>() {
					@Override
					public List<V> applyTo(long[] input) {
						return new LongArrayListView<>(input, valueFactory);
					};
				}));
	}

	public MatrixImplementation(Factory<V> valueFactory, long[]... values) {
		this(getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(Order order, final Factory<V> valueFactory,
			float[]... values) {
		this(true, order, new ListTransformationView<>(Arrays.asList(values),
				new Function<List<V>, float[]>() {
					@Override
					public List<V> applyTo(float[] input) {
						return new FloatArrayListView<>(input, valueFactory);
					};
				}));
	}

	public MatrixImplementation(Factory<V> valueFactory, float[]... values) {
		this(getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(Order order, final Factory<V> valueFactory,
			double[]... values) {
		this(true, order, new ListTransformationView<>(Arrays.asList(values),
				new Function<List<V>, double[]>() {
					@Override
					public List<V> applyTo(double[] input) {
						return new DoubleArrayListView<>(input, valueFactory);
					};
				}));
	}

	public MatrixImplementation(Factory<V> valueFactory, double[]... values) {
		this(getDefaultOrder(), valueFactory, values);
	}

	public MatrixImplementation(Order order, Factory<V> valueFactory,
			Vector<?, ?>... values) {
		this(order, valueFactory, new ListTransformationView<>(
				Arrays.asList(values),
				new Function<List<? extends Value<?>>, Vector<?, ?>>() {
					@Override
					public List<? extends Value<?>> applyTo(Vector<?, ?> input) {
						return input.getData();
					}
				}));
	}

	public MatrixImplementation(Factory<V> valueFactory, Vector<?, ?>... values) {
		this(getDefaultOrder(), valueFactory, values);
	}

	@SafeVarargs
	public MatrixImplementation(Order order, Vector<?, V>... values) {
		this(order, new ListTransformationView<>(Arrays.asList(values),
				new Function<List<? extends Value<V>>, Vector<?, V>>() {
					@Override
					public List<? extends Value<V>> applyTo(Vector<?, V> input) {
						return input.getData();
					}
				}));
	}

	@SafeVarargs
	public MatrixImplementation(Vector<?, V>... values) {
		this(getDefaultOrder(), values);
	}

	@SafeVarargs
	public MatrixImplementation(boolean copyByReference, Order order,
			Vector<?, V>... values) {
		this(copyByReference, order, new ListTransformationView<>(
				Arrays.asList(values), new Function<List<V>, Vector<?, V>>() {
					@Override
					public List<V> applyTo(Vector<?, V> input) {
						return input.getData();
					}
				}));
	}

	@SafeVarargs
	public MatrixImplementation(boolean copyByReference, Vector<?, V>... values) {
		this(copyByReference, getDefaultOrder(), values);
	}

	protected void finalise() {
		getDependencies().set(getData());
	}

	@Override
	public S get() {
		return getThis();
	}

	public static Order getDefaultOrder() {
		return defaultOrder;
	}

	public static void setDefaultOrder(Order defaultOrder) {
		if (defaultOrder == null) {
			throw new IllegalArgumentException(new NullPointerException());
		}

		MatrixImplementation.defaultOrder = defaultOrder;
	}

	@Override
	public final Order getOrder() {
		return order.getValue();
	}

	@Override
	public final IdentityExpression<Order> getOrderExpression() {
		return order;
	}

	@Override
	public final S setOrder(Order order) {
		return setOrder(order, true);
	}

	/**
	 * Not public by default, as we don't want e.g. a Matrix23 to be able to
	 * change order without also transposing the data such that it conceptually
	 * remains a 2x3 matrix, as opposed to a 3x2 matrix.
	 * 
	 * @param order
	 * @param transposeData
	 */
	protected final S setOrder(Order order, boolean transposeData) {
		if (this.order.getValue() != order) {
			this.order.set(order);
			if (transposeData) {
				transposeImplementation();
			}
		}

		return getThis();
	}

	/**
	 * Not public by default, as we don't want e.g. a Matrix23 to be able to
	 * transpose the data without also changing order such that it conceptually
	 * remains a 2x3 matrix, as opposed to a 3x2 matrix.
	 */
	protected final S transposeImplementation() {
		List<List<V>> transposedData = new ArrayList<List<V>>();
		for (int i = 0; i < getMajorSize(); i++) {
			List<V> elements = new ArrayList<V>();
			transposedData.add(elements);
			for (int j = 0; j < getMinorSize(); j++) {
				elements.add(data.get(j).get(i));
			}
		}
		data.clear();
		data.addAll(transposedData);

		return getThis();
	}

	@Override
	public abstract Matrix<?, V> getTransposed();

	@Override
	public final S negate() {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.negate();
			}
		}
		return getThis();
	}

	@Override
	public final S getNegated() {
		return copy().negate();
	}

	@Override
	public final S multiply(Value<?> scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.multiply(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S multiply(int scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.multiply(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S multiply(long scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.multiply(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S multiply(float scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.multiply(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S multiply(double scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.multiply(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S getMultiplied(Value<?> value) {
		return copy().multiply(value);
	}

	@Override
	public final S getMultiplied(int value) {
		return copy().multiply(value);
	}

	@Override
	public final S getMultiplied(long value) {
		return copy().multiply(value);
	}

	@Override
	public final S getMultiplied(float value) {
		return copy().multiply(value);
	}

	@Override
	public final S getMultiplied(double value) {
		return copy().multiply(value);
	}

	@Override
	public final S divide(Value<?> scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.divide(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S divide(int scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.divide(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S divide(long scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.divide(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S divide(float scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.divide(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S divide(double scalar) {
		for (List<V> elements : data) {
			for (V element : elements) {
				element.divide(scalar);
			}
		}
		return getThis();
	}

	@Override
	public final S getDivided(Value<?> value) {
		return copy().divide(value);
	}

	@Override
	public final S getDivided(int value) {
		return copy().divide(value);
	}

	@Override
	public final S getDivided(long value) {
		return copy().divide(value);
	}

	@Override
	public final S getDivided(float value) {
		return copy().divide(value);
	}

	@Override
	public final S getDivided(double value) {
		return copy().divide(value);
	}

	@Override
	public final S add(Matrix<?, ?> other) {
		operateOnData2(other.getOrder(), other.getData2(),
				new BinaryOperation<V, V, Value<?>>() {
					@Override
					public V apply(V firstOperand, Value<?> secondOperand) {
						return firstOperand.add(secondOperand);
					}
				});

		return getThis();
	}

	@Override
	public final S getAdded(Matrix<?, ?> value) {
		return copy().add(value);
	}

	@Override
	public final S subtract(Matrix<?, ?> other) {
		operateOnData2(other.getOrder(), other.getData2(),
				new BinaryOperation<V, V, Value<?>>() {
					@Override
					public V apply(V firstOperand, Value<?> secondOperand) {
						return firstOperand.subtract(secondOperand);
					}
				});

		return getThis();
	}

	@Override
	public final S getSubtracted(Matrix<?, ?> value) {
		return copy().subtract(value);
	}

	@Override
	public final S multiply(Matrix<?, ?> other) {
		setData2(multiplyData(other.getData2()));

		return getThis();
	}

	@Override
	public final S preMultiply(Matrix<?, ?> other) {
		setData2(preMultiplyData(other.getData2()));

		return getThis();
	}

	public final List<List<Value<V>>> multiplyData(
			List<? extends List<? extends Value<?>>> otherData) {
		// TODO implement multiplication! include isResiseable() in parameter
		// dimensions check...

		return null;
	}

	protected final List<List<Value<V>>> preMultiplyData(
			List<? extends List<? extends Value<?>>> otherData) {
		// TODO as above.

		return null;
	}

	@Override
	public final S getMultiplied(Matrix<?, ?> value) {
		return copy().multiply(value);
	}

	@Override
	public final S getPreMultiplied(Matrix<?, ?> value) {
		return copy().preMultiply(value);
	}

	/**
	 * Get the dimensions as: [columns, rows].
	 * 
	 * @return A vector representing the current dimensions.
	 */
	@Override
	public Vector2<IntValue> getDimensions2() {
		return new Vector2<IntValue>(IntValueFactory.instance(), getRowSize(),
				getColumnSize());
	}

	protected final S resizeRowsImplementation(int dimensions) {
		if (getOrder() == Order.RowMajor) {
			return resizeMajorImplementation(dimensions);
		} else {
			return resizeMinorImplementation(dimensions);
		}
	}

	protected final S resizeColumnsImplementation(int dimensions) {
		if (getOrder() == Order.ColumnMajor) {
			return resizeMajorImplementation(dimensions);
		} else {
			return resizeMinorImplementation(dimensions);
		}
	}

	protected final S resizeMajorImplementation(int dimensions) {
		try {
			DimensionalityException.checkValid(dimensions);
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}

		for (List<V> elements : data) {
			while (dimensions < elements.size()) {
				getDependencies().remove(elements.remove(elements.size() - 1));
			}
			while (dimensions > elements.size()) {
				V element = elements.get(0).copy().setValue(0);
				elements.add(element);
				getDependencies().add(element);
			}
		}

		return getThis();
	}

	protected final S resizeMinorImplementation(int dimensions) {
		try {
			DimensionalityException.checkValid(dimensions);
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}

		while (dimensions < data.size()) {
			getDependencies().removeAll(data.remove(data.size() - 1));
		}
		while (dimensions > data.size()) {
			List<V> elements = new ArrayList<V>();
			for (V element : data.get(0)) {
				elements.add(element.copy().setValue(0));
			}
			data.add(elements);
			getDependencies().addAll(elements);
		}

		return getThis();
	}

	protected final S resizeImplementation(Vector<?, IntValue> dimensions) {
		resizeImplementation(dimensions.getData().get(0).intValue(), dimensions
				.getData().get(1).intValue());

		return getThis();
	}

	protected final S resizeImplementation(int rows, int columns) {
		resizeRowsImplementation(rows);
		resizeColumnsImplementation(columns);

		return getThis();
	}

	@Override
	public final int getDataSize() {
		return getMajorSize() * getMinorSize();
	}

	@Override
	public final int getMajorSize() {
		return data.get(0).size();
	}

	@Override
	public final int getMinorSize() {
		return data.size();
	};

	@Override
	public final int getMajorSize(Order order) {
		if (order == getOrder()) {
			return getMajorSize();
		} else {
			return getMinorSize();
		}
	}

	@Override
	public final int getMinorSize(Order order) {
		if (order == getOrder()) {
			return getMinorSize();
		} else {
			return getMajorSize();
		}
	}

	@Override
	public int getRowSize() {
		return getOrder() == Order.RowMajor ? getMajorSize() : getMinorSize();
	}

	@Override
	public int getColumnSize() {
		return getOrder() == Order.ColumnMajor ? getMajorSize() : getMinorSize();
	}

	@Override
	public boolean isSquare() {
		return getMajorSize() == getMinorSize();
	}

	public static <T extends Matrix<?, ?>> T assertIsSquare(T matrix) {
		try {
			DimensionalityException.checkEquivalence(matrix.getMajorSize(),
					matrix.getMinorSize());
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}

		return matrix;
	}

	public static <T extends Matrix<?, ?>> T assertDimensions(T matrix, int rows,
			int columns) {
		try {
			DimensionalityException.checkEquivalence(matrix.getRowSize(), rows);
			DimensionalityException.checkEquivalence(matrix.getColumnSize(), columns);
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}

		return matrix;
	}

	public static <T extends Matrix<?, ?>> T assertDimensions(T matrix, int size) {
		try {
			DimensionalityException.checkEquivalence(matrix.getMajorSize(), size);
			DimensionalityException.checkEquivalence(matrix.getMinorSize(), size);
		} catch (DimensionalityException e) {
			throw new IllegalArgumentException(e);
		}

		return matrix;
	}

	@Override
	public List<? extends Vector<?, V>> getRowVectors() {
		if (getOrder() == Order.RowMajor) {
			return getMajorVectors();
		} else {
			return getMinorVectors();
		}
	}

	@Override
	public List<? extends Vector<?, V>> getColumnVectors() {
		if (getOrder() == Order.ColumnMajor) {
			return getMajorVectors();
		} else {
			return getMinorVectors();
		}
	}

	@Override
	public Vector<?, V> getRowVector(int row) {
		if (getOrder() == Order.RowMajor) {
			return getMajorVector(row);
		} else {
			return getMinorVector(row);
		}
	}

	protected List<V> getRowVectorData(int row) {
		if (getOrder() == Order.RowMajor) {
			return getMajorVectorData(row);
		} else {
			return getMinorVectorData(row);
		}
	}

	@Override
	public Vector<?, V> getColumnVector(int column) {
		if (getOrder() == Order.ColumnMajor) {
			return getMajorVector(column);
		} else {
			return getMinorVector(column);
		}
	}

	protected List<V> getColumnVectorData(int column) {
		if (getOrder() == Order.ColumnMajor) {
			return getMajorVectorData(column);
		} else {
			return getMinorVectorData(column);
		}
	}

	@Override
	public List<? extends Vector<?, V>> getMajorVectors() {
		return new AbstractList<Vector<?, V>>() {
			@Override
			public Vector<?, V> get(int index) {
				return getMajorVector(index);
			}

			@Override
			public int size() {
				return getMinorSize();
			}
		};
	}

	@Override
	public List<? extends Vector<?, V>> getMinorVectors() {
		return new AbstractList<Vector<?, V>>() {
			@Override
			public Vector<?, V> get(int index) {
				return getMinorVector(index);
			}

			@Override
			public int size() {
				return getMajorSize();
			}
		};
	}

	@Override
	public Vector<?, V> getMajorVector(int index) {
		return new VectorN<V>(true, getMajorVectorData(index));
	}

	protected List<V> getMajorVectorData(int index) {
		return data.get(index);
	}

	@Override
	public Vector<?, V> getMinorVector(int index) {
		return new VectorN<V>(true, getMinorVectorData(index));
	}

	protected List<V> getMinorVectorData(int index) {
		List<V> minorElements = new ArrayList<V>();
		for (List<V> elements : data) {
			minorElements.add(elements.get(index));
		}
		return minorElements;
	}

	@Override
	public final List<List<V>> getData2() {
		return new ListTransformationView<>(data, new UnmodifiableListFunction<V>());
	}

	@Override
	public final List<V> getData() {
		return new MergeIndicesListView<>(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final S getThis() {
		return (S) this;
	}

	@Override
	public final String toString() {
		String string = new String();

		string += "[";

		boolean first = true;
		for (List<V> elements : data) {
			if (first) {
				first = false;
			} else {
				string += ", ";
			}

			Iterator<V> dataIterator = elements.iterator();
			string += "[" + dataIterator.next();
			while (dataIterator.hasNext()) {
				string += ", " + dataIterator.next();
			}
			string += "]";
		}

		string += "]";

		return string;
	}

	@Override
	public final boolean equals(Object that) {
		if (this == that) {
			return true;
		}

		if (!(that instanceof Matrix<?, ?>)) {
			return false;
		}

		Matrix<?, ?> thatMatrix = (Matrix<?, ?>) that;

		if (!getDimensions2().equals(thatMatrix.getDimensions2())) {
			return false;
		}

		boolean equalOrder = getOrder().equals(thatMatrix.getOrder());

		List<? extends List<? extends Value<?>>> thoseElements = thatMatrix
				.getData2();
		int i = 0;
		for (List<V> elements : data) {
			int j = 0;
			for (V element : elements) {
				boolean equalElement;
				if (equalOrder) {
					equalElement = element.equals(thoseElements.get(i).get(j));
				} else {
					equalElement = element.equals(thoseElements.get(j).get(i));
				}
				if (!equalElement) {
					return false;
				}

				j++;
			}

			i++;
		}

		return true;
	}

	@Override
	public final int hashCode() {
		return getRowSize() + getColumnSize() + getOrder().hashCode()
				+ data.hashCode();
	}

	@Override
	public final int compareTo(Matrix<?, ?> other) {
		int comparison;

		comparison = getDimensions2().compareTo(other.getDimensions2());
		if (comparison != 0)
			return comparison;

		boolean equalOrder = getOrder().equals(other.getOrder());

		List<? extends List<? extends Value<?>>> thoseElements = other.getData2();
		int i = 0;
		for (List<V> elements : data) {
			int j = 0;
			for (V element : elements) {
				if (equalOrder) {
					comparison = element.compareTo(thoseElements.get(i).get(j));
				} else {
					comparison = element.compareTo(thoseElements.get(j).get(i));
				}
				if (comparison != 0)
					return comparison;

				j++;
			}

			i++;
		}

		return 0;
	}

	@Override
	public final int[] getData(int[] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}

			DimensionalityException.checkEquivalence(getDimensions2().getX()
					.getMultiplied(getDimensions2().getY()).intValue(), dataArray.length);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> elements : data) {
				for (V element : elements) {
					dataArray[i++] = element.intValue();
				}
			}
		} else {
			int i = 0;
			for (List<V> elements : data) {
				int j = 0;
				for (V element : elements) {
					dataArray[i + j] = element.intValue();
					j += data.size();
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final long[] getData(long[] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}

			DimensionalityException.checkEquivalence(getDimensions2().getX()
					.getMultiplied(getDimensions2().getY()).intValue(), dataArray.length);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> elements : data) {
				for (V element : elements) {
					dataArray[i++] = element.longValue();
				}
			}
		} else {
			int i = 0;
			for (List<V> elements : data) {
				int j = 0;
				for (V element : elements) {
					dataArray[i + j] = element.longValue();
					j += data.size();
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final float[] getData(float[] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}

			DimensionalityException.checkEquivalence(getDimensions2().getX()
					.getMultiplied(getDimensions2().getY()).intValue(), dataArray.length);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> elements : data) {
				for (V element : elements) {
					dataArray[i++] = element.floatValue();
				}
			}
		} else {
			int i = 0;
			for (List<V> elements : data) {
				int j = 0;
				for (V element : elements) {
					dataArray[i + j] = element.floatValue();
					j += data.size();
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final double[] getData(double[] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}

			DimensionalityException.checkEquivalence(getDimensions2().getX()
					.getMultiplied(getDimensions2().getY()).intValue(), dataArray.length);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> elements : data) {
				for (V element : elements) {
					dataArray[i++] = element.doubleValue();
				}
			}
		} else {
			int i = 0;
			for (List<V> elements : data) {
				int j = 0;
				for (V element : elements) {
					dataArray[i + j] = element.doubleValue();
					j += data.size();
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final int[] getIntData(Order order) {
		return getData(new int[getDataSize()], order);
	}

	@Override
	public final long[] getLongData(Order order) {
		return getData(new long[getDataSize()], order);
	}

	@Override
	public final float[] getFloatData(Order order) {
		return getData(new float[getDataSize()], order);
	}

	@Override
	public final double[] getDoubleData(Order order) {
		return getData(new double[getDataSize()], order);
	}

	@Override
	public final int[] getData(int[] dataArray) {
		return getData(dataArray, getOrder());
	}

	@Override
	public final long[] getData(long[] dataArray) {
		return getData(dataArray, getOrder());
	}

	@Override
	public final float[] getData(float[] dataArray) {
		return getData(dataArray, getOrder());
	}

	@Override
	public final double[] getData(double[] dataArray) {
		return getData(dataArray, getOrder());
	}

	@Override
	public final int[] getIntData() {
		return getIntData(getOrder());
	}

	@Override
	public final long[] getLongData() {
		return getLongData(getOrder());
	}

	@Override
	public final float[] getFloatData() {
		return getFloatData(getOrder());
	}

	@Override
	public final double[] getDoubleData() {
		return getDoubleData(getOrder());
	}

	@Override
	protected final S evaluate() {
		return getThis();
	}

	@Override
	public final S getDecoupledValue() {
		return copy();
	}

	@Override
	public final int[][] getData2(int[][] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}
			NullPointerInCollectionException.checkList(dataArray);

			int majorSize;
			int minorSize;

			if (order == getOrder()) {
				majorSize = getMajorSize();
				minorSize = getMinorSize();
			} else {
				majorSize = getMinorSize();
				minorSize = getMajorSize();
			}

			DimensionalityException.checkEquivalence(minorSize, dataArray.length);
			for (int major = 0; major < minorSize; major++) {
				DimensionalityException.checkEquivalence(majorSize,
						dataArray[major].length);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[i][j] = element.intValue();
					j++;
				}
				i++;
			}
		} else {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[j][i] = element.intValue();
					j++;
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final long[][] getData2(long[][] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}
			NullPointerInCollectionException.checkList(dataArray);

			int majorSize;
			int minorSize;

			if (order == getOrder()) {
				majorSize = getMajorSize();
				minorSize = getMinorSize();
			} else {
				majorSize = getMinorSize();
				minorSize = getMajorSize();
			}

			DimensionalityException.checkEquivalence(minorSize, dataArray.length);
			for (int major = 0; major < minorSize; major++) {
				DimensionalityException.checkEquivalence(majorSize,
						dataArray[major].length);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[i][j] = element.longValue();
					j++;
				}
				i++;
			}
		} else {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[j][i] = element.longValue();
					j++;
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final float[][] getData2(float[][] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}
			NullPointerInCollectionException.checkList(dataArray);

			int majorSize;
			int minorSize;

			if (order == getOrder()) {
				majorSize = getMajorSize();
				minorSize = getMinorSize();
			} else {
				majorSize = getMinorSize();
				minorSize = getMajorSize();
			}

			DimensionalityException.checkEquivalence(minorSize, dataArray.length);
			for (int major = 0; major < minorSize; major++) {
				DimensionalityException.checkEquivalence(majorSize,
						dataArray[major].length);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[i][j] = element.floatValue();
					j++;
				}
				i++;
			}
		} else {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[j][i] = element.floatValue();
					j++;
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final double[][] getData2(double[][] dataArray, Order order) {
		try {
			if (dataArray == null || order == null) {
				throw new NullPointerException();
			}
			NullPointerInCollectionException.checkList(dataArray);

			int majorSize;
			int minorSize;

			if (order == getOrder()) {
				majorSize = getMajorSize();
				minorSize = getMinorSize();
			} else {
				majorSize = getMinorSize();
				minorSize = getMajorSize();
			}

			DimensionalityException.checkEquivalence(minorSize, dataArray.length);
			for (int major = 0; major < minorSize; major++) {
				DimensionalityException.checkEquivalence(majorSize,
						dataArray[major].length);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		if (order == getOrder()) {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[i][j] = element.doubleValue();
					j++;
				}
				i++;
			}
		} else {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					dataArray[j][i] = element.doubleValue();
					j++;
				}
				i++;
			}
		}

		return dataArray;
	}

	@Override
	public final int[][] getIntData2(Order order) {
		return getData2(new int[getMajorSize(order)][getMinorSize(order)], order);
	}

	@Override
	public final long[][] getLongData2(Order order) {
		return getData2(new long[getMajorSize(order)][getMinorSize(order)], order);
	}

	@Override
	public final float[][] getFloatData2(Order order) {
		return getData2(new float[getMajorSize(order)][getMinorSize(order)], order);
	}

	@Override
	public final double[][] getDoubleData2(Order order) {
		return getData2(new double[getMajorSize(order)][getMinorSize(order)], order);
	}

	@Override
	public final int[][] getData2(int[][] dataArray) {
		return getData2(dataArray, getOrder());
	}

	@Override
	public final long[][] getData2(long[][] dataArray) {
		return getData2(dataArray, getOrder());
	}

	@Override
	public final float[][] getData2(float[][] dataArray) {
		return getData2(dataArray, getOrder());
	}

	@Override
	public final double[][] getData2(double[][] dataArray) {
		return getData2(dataArray, getOrder());
	}

	@Override
	public final int[][] getIntData2() {
		return getIntData2(getOrder());
	}

	@Override
	public final long[][] getLongData2() {
		return getLongData2(getOrder());
	}

	@Override
	public final float[][] getFloatData2() {
		return getFloatData2(getOrder());
	}

	@Override
	public final double[][] getDoubleData2() {
		return getDoubleData2(getOrder());
	}

	@Override
	public Value<V> getElement(int major, int minor) {
		return data.get(major).get(minor);
	}

	@Override
	public final S set(Matrix<?, ?> to) {
		setOrder(to.getOrder(), false);

		return setData2(to.getData2());
	}

	@Override
	public final <I> S operateOnData(Order order, List<? extends I> itemList,
			BinaryOperation<? extends V, ? super V, ? super I> operator) {
		try {
			if (operator == null || order == null || itemList == null) {
				throw new NullPointerException();
			}

			DimensionalityException.checkEquivalence(getMajorSize() * getMinorSize(),
					itemList.size());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		getDependencies().clear();

		if (order == this.getOrder()) {
			Iterator<? extends I> itemIterator = itemList.iterator();

			for (List<V> major : data) {
				for (V element : major) {
					element = operator.apply(element, itemIterator.next());
				}
			}
		} else {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					element = operator.apply(element,
							itemList.get(i + j * getMajorSize()));
					j++;
				}
				i++;
			}
		}

		getDependencies().addAll(getData());

		return getThis();
	}

	@Override
	public final <I> S operateOnData(List<? extends I> itemList,
			BinaryOperation<? extends V, ? super V, ? super I> operator) {
		return operateOnData(getOrder(), itemList, operator);
	}

	@Override
	public final <I> S operateOnData2(Order order,
			List<? extends List<? extends I>> itemList,
			BinaryOperation<? extends V, ? super V, ? super I> operator) {
		try {
			if (operator == null || order == null || itemList == null) {
				throw new NullPointerException();
			}

			int majorSize;
			int minorSize;

			if (order == getOrder()) {
				majorSize = getMajorSize();
				minorSize = getMinorSize();
			} else {
				majorSize = getMinorSize();
				minorSize = getMajorSize();
			}

			DimensionalityException.checkEquivalence(minorSize, itemList.size());
			for (int major = 0; major < minorSize; major++) {
				DimensionalityException.checkEquivalence(majorSize, itemList.get(major)
						.size());
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		getDependencies().clear();

		if (order == this.getOrder()) {
			Iterator<? extends List<? extends I>> itemListIterator = itemList
					.iterator();

			for (List<V> major : data) {
				Iterator<? extends I> itemIterator = itemListIterator.next().iterator();

				for (V element : major) {
					element = operator.apply(element, itemIterator.next());
				}
			}
		} else {
			int i = 0;
			for (List<V> major : data) {
				int j = 0;
				for (V element : major) {
					element = operator.apply(element, itemList.get(j).get(i));
					j++;
				}
				i++;
			}
		}

		getDependencies().addAll(getData());

		return getThis();
	}

	@Override
	public final <I> S operateOnData2(List<? extends List<? extends I>> itemList,
			BinaryOperation<? extends V, ? super V, ? super I> operator) {
		return operateOnData2(getOrder(), itemList, operator);
	}

	@Override
	public final S operateOnData(UnaryOperation<? extends V, ? super V> operator) {
		try {
			if (operator == null) {
				throw new NullPointerException();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		getDependencies().clear();

		for (List<V> major : data) {
			for (V element : major) {
				element = operator.apply(element);
			}
		}

		getDependencies().addAll(getData());

		return getThis();
	}

	@Override
	public final <I> S setData(Order order, List<? extends I> itemList,
			AssignmentOperation<V, ? super I> operator) {
		return operateOnData(order, itemList, operator);
	}

	@Override
	public final <I> S setData(List<? extends I> itemList,
			AssignmentOperation<V, ? super I> operator) {
		return setData(getOrder(), itemList, operator);
	}

	@Override
	public final <I> S setData2(Order order,
			List<? extends List<? extends I>> itemList,
			AssignmentOperation<V, ? super I> operator) {
		return operateOnData2(order, itemList, operator);
	}

	@Override
	public final <I> S setData2(List<? extends List<? extends I>> itemList,
			AssignmentOperation<V, ? super I> operator) {
		return setData2(getOrder(), itemList, operator);
	}

	@Override
	public final S setData2(final boolean setByReference, Order order,
			List<? extends List<? extends V>> to) {
		if (setByReference) {
			return operateOnData2(order, to, new BinaryOperation<V, V, V>() {
				@Override
				public V apply(V firstOperand, V secondOperand) {
					return secondOperand;
				}
			});
		} else {
			return operateOnData2(order, to, new AssignmentOperation<V, V>() {
				@Override
				public void assign(V firstOperand, V secondOperand) {
					firstOperand.setValue(secondOperand);
				}
			});
		}
	}

	@Override
	public final S setData2(boolean setByReference,
			List<? extends List<? extends V>> to) {
		return setData2(setByReference, getOrder(), to);
	}

	@Override
	public final S setData(final boolean setByReference, Order order,
			List<? extends V> to) {
		if (setByReference) {
			return operateOnData(order, to, new BinaryOperation<V, V, V>() {
				@Override
				public V apply(V firstOperand, V secondOperand) {
					if (secondOperand == null) {
						throw new IllegalArgumentException(new NullPointerException());
					}

					return secondOperand;
				}
			});
		} else {
			return operateOnData(order, to, new AssignmentOperation<V, V>() {
				@Override
				public void assign(V firstOperand, V secondOperand) {
					if (secondOperand == null) {
						throw new IllegalArgumentException(new NullPointerException());
					}

					firstOperand.setValue(secondOperand);
				}
			});
		}
	}

	@Override
	public final S setData(boolean setByReference, List<? extends V> to) {
		return setData(setByReference, getOrder(), to);
	}

	@Override
	public final S setData(boolean setByReference, Order order, Vector<?, V> to) {
		return setData(setByReference, order, to.getData());
	}

	@Override
	public final S setData(boolean setByReference, Vector<?, V> to) {
		return setData(setByReference, to.getData());
	}

	@Override
	public final S setData(Order order, Vector<?, ?> to) {
		return setData(order, to.getData());
	}

	@Override
	public final S setData(Vector<?, ?> to) {
		return setData(to.getData());
	}

	@Override
	@SuppressWarnings("unchecked")
	public final S setData(boolean setByReference, Order order, V... to) {
		return setData(setByReference, order, Arrays.asList(to));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final S setData(boolean setByReference, V... to) {
		return setData(setByReference, Arrays.asList(to));
	}

	@Override
	public final S setData(Order order, Number... values) {
		return setData(order, Arrays.asList(values),
				new AssignmentOperation<V, Number>() {
					@Override
					public void assign(V assignee, Number assignment) {
						if (assignment == null) {
							throw new IllegalArgumentException(new NullPointerException());
						}
						assignee.setValue(assignment);
					}
				});
	}

	@Override
	public final S setData(Number... values) {
		return setData(getOrder(), values);
	}

	@Override
	public final S setData(Order order, Value<?>... to) {
		return setData(order, Arrays.asList(to));
	}

	@Override
	public final S setData(Value<?>... to) {
		return setData(Arrays.asList(to));
	}

	@Override
	public final S setData(Order order, int[] to) {
		return setData(order,
				new IntArrayListView<>(to, IntValueFactory.instance()));
	}

	@Override
	public final S setData(Order order, long[] to) {
		return setData(order,
				new LongArrayListView<>(to, LongValueFactory.instance()));
	}

	@Override
	public final S setData(Order order, float[] to) {
		return setData(order,
				new FloatArrayListView<>(to, FloatValueFactory.instance()));
	}

	@Override
	public final S setData(Order order, double[] to) {
		return setData(order,
				new DoubleArrayListView<>(to, DoubleValueFactory.instance()));
	}

	@Override
	public final S setData(int[] to) {
		return setData(getOrder(), to);
	}

	@Override
	public final S setData(long[] to) {
		return setData(getOrder(), to);
	}

	@Override
	public final S setData(float[] to) {
		return setData(getOrder(), to);
	}

	@Override
	public final S setData(double[] to) {
		return setData(getOrder(), to);
	}

	@Override
	public final S setData2(Order order, int[]... to) {
		return setData2(order, new ListTransformationView<>(Arrays.asList(to),
				new Function<List<IntValue>, int[]>() {
					@Override
					public List<IntValue> applyTo(int[] input) {
						return new IntArrayListView<>(input, IntValueFactory.instance());
					};
				}));
	}

	@Override
	public final S setData2(Order order, long[]... to) {
		return setData2(order, new ListTransformationView<>(Arrays.asList(to),
				new Function<List<LongValue>, long[]>() {
					@Override
					public List<LongValue> applyTo(long[] input) {
						return new LongArrayListView<>(input, LongValueFactory.instance());
					};
				}));
	}

	@Override
	public final S setData2(Order order, float[]... to) {
		return setData2(order, new ListTransformationView<>(Arrays.asList(to),
				new Function<List<FloatValue>, float[]>() {
					@Override
					public List<FloatValue> applyTo(float[] input) {
						return new FloatArrayListView<>(input, FloatValueFactory.instance());
					};
				}));
	}

	@Override
	public final S setData2(Order order, double[]... to) {
		return setData2(order, new ListTransformationView<>(Arrays.asList(to),
				new Function<List<DoubleValue>, double[]>() {
					@Override
					public List<DoubleValue> applyTo(double[] input) {
						return new DoubleArrayListView<>(input, DoubleValueFactory
								.instance());
					};
				}));
	}

	@Override
	public final S setData2(int[]... to) {
		return setData2(getOrder(), to);
	}

	@Override
	public final S setData2(long[]... to) {
		return setData2(getOrder(), to);
	}

	@Override
	public final S setData2(float[]... to) {
		return setData2(getOrder(), to);
	}

	@Override
	public final S setData2(double[]... to) {
		return setData2(getOrder(), to);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final S setData2(boolean copyByReference, Order order,
			Vector<?, V>... to) {
		return setData2(copyByReference, order,
				new ListTransformationView<>(Arrays.asList(to),
						new Function<List<? extends V>, Vector<?, V>>() {
							@Override
							public List<? extends V> applyTo(Vector<?, V> input) {
								return input.getData();
							}
						}));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final S setData2(boolean copyByReference, Vector<?, V>... values) {
		return setData2(getOrder(), values);
	}

	@Override
	public final S setData2(Order order, Vector<?, ?>... values) {
		return setData2(order, new ListTransformationView<>(Arrays.asList(values),
				new Function<List<? extends Value<?>>, Vector<?, ?>>() {
					@Override
					public List<? extends Value<?>> applyTo(Vector<?, ?> input) {
						return input.getData();
					}
				}));
	}

	@Override
	public final S setData2(Vector<?, ?>... values) {
		return setData2(getOrder(), values);
	}

	@Override
	public final S setData2(Order order,
			List<? extends List<? extends Value<?>>> to) {
		return setData2(order, to, new AssignmentOperation<V, Value<?>>() {
			@Override
			public void assign(V assignee, Value<?> assignment) {
				if (assignment == null) {
					throw new IllegalArgumentException(new NullPointerException());
				}

				assignee.set(assignment);
			}
		});
	}

	@Override
	public final S setData2(List<? extends List<? extends Value<?>>> to) {
		return setData2(getOrder(), to);
	}

	@Override
	public final S setData(Order order, List<? extends Value<?>> to) {
		return setData(order, to, new AssignmentOperation<V, Value<?>>() {
			@Override
			public void assign(V assignee, Value<?> assignment) {
				if (assignment == null) {
					throw new IllegalArgumentException(new NullPointerException());
				}

				assignee.set(assignment);
			}
		});
	}

	@Override
	public final S setData(List<? extends Value<?>> to) {
		return setData(getOrder(), to);
	}

	@Override
	public boolean isResizable() {
		return false;
	}
}
