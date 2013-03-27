package uk.co.elionline.emerge.mathematics.expressions;

import uk.co.elionline.emerge.mathematics.functions.ConditionalOperation;
import uk.co.elionline.emerge.mathematics.logic.BooleanValue;

public class ConditionalExpression<O> extends
		UnaryOperationExpression<O, /*@ReadOnly*/BooleanValue> {

	public ConditionalExpression(
			Expression<? extends /*@ReadOnly*/BooleanValue> condition, O valueWhenFulfilled,
			O valueWhenUnfulfilled) {
		super(condition, new ConditionalOperation<>(valueWhenFulfilled,
				valueWhenUnfulfilled));
	}

	public final Expression<? extends /*@ReadOnly*/BooleanValue> getCondition() {
		return getOperand();
	}

	@SuppressWarnings("unchecked")
	public final O getValueWhenFulfilled() {
		return ((ConditionalOperation<O>) getOperation().getValue())
				.getValueWhenFulfilled();
	}

	@SuppressWarnings("unchecked")
	public final O getValueWhenUnfulfilled() {
		return ((ConditionalOperation<O>) getOperation().getValue())
				.getValueWhenUnfulfilled();
	}
}
