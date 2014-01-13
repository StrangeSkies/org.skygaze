package uk.co.strangeskies.gears.utilities.functions;

import java.util.function.Function;

public class ConditionFunction<O> implements
		Function</* @ReadOnly */Boolean, O> {
	private final O valueWhenFulfilled;
	private final O valueWhenUnfulfilled;

	public ConditionFunction(O valueWhenFulfilled, O valueWhenUnfulfilled) {
		this.valueWhenFulfilled = valueWhenFulfilled;
		this.valueWhenUnfulfilled = valueWhenUnfulfilled;
	}

	@Override
	public O apply(/* @ReadOnly */Boolean operand) {
		if (operand) {
			return valueWhenFulfilled;
		} else {
			return valueWhenUnfulfilled;
		}
	}

	public final O getValueWhenFulfilled() {
		return valueWhenFulfilled;
	}

	public final O getValueWhenUnfulfilled() {
		return valueWhenUnfulfilled;
	}
}
