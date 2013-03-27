package uk.co.elionline.emerge.mathematics.expressions.collections;

import uk.co.elionline.emerge.mathematics.functions.SettingOperation;
import uk.co.elionline.emerge.utilities.Property;

public class SettingBuffer<T extends Property<?, ? super R>, R> extends
		OperationApplicationBuffer<T, R> {
	public SettingBuffer(T front, R back) {
		super(front, back, new SettingOperation<T, R>());
	}

	public SettingBuffer(DoubleBuffer<? extends T, ? extends R> doubleBuffer) {
		super(doubleBuffer.getFront(), doubleBuffer.getBack(),
				new SettingOperation<T, R>());
	}
}
