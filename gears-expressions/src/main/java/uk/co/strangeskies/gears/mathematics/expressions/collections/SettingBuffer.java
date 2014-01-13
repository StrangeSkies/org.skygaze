package uk.co.strangeskies.gears.mathematics.expressions.collections;

import uk.co.strangeskies.gears.utilities.Property;
import uk.co.strangeskies.gears.utilities.functions.SettingOperation;

public class SettingBuffer<B, F extends Property<?, ? super B>> extends
		OperationApplicationBuffer<B, F> {
	public SettingBuffer(B back, F front) {
		super(front, back, new SettingOperation<F, B>());
	}

	public SettingBuffer(DoubleBuffer<? extends B, ? extends F> doubleBuffer) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				new SettingOperation<F, B>());
	}
}
