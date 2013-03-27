package uk.co.elionline.emerge.mathematics.logic;

import uk.co.elionline.emerge.mathematics.Negatable;
import uk.co.elionline.emerge.mathematics.expressions.Expression;
import uk.co.elionline.emerge.mathematics.expressions.ExpressionImplementation;

public class BooleanValue extends ExpressionImplementation<BooleanValue>
		implements Negatable<BooleanValue, BooleanValue> {
	private boolean value;

	public BooleanValue() {
	}

	public BooleanValue(Boolean value) {
		this.value = value;
	}

	public BooleanValue(Expression<? extends BooleanValue> expression) {
		value = expression.getValue().getBooleanValue();
	}

	@Override
	public final BooleanValue getValue() {
		return this;
	}

	public final BooleanValue getDecoupledValue() {
		return copy();
	}

	@Override
	public final BooleanValue getThis() {
		return null;
	}

	@Override
	public final BooleanValue copy() {
		return new BooleanValue(this);
	}

	public final BooleanValue getConst() {
		return this;
	}

	public final boolean getBooleanValue() {
		return value;
	}

	public final void setValue(boolean value) {
		this.value = value;
	}

	public final void setValue(Boolean value) {
		this.value = value;
	}

	public final void setValue(Expression<? extends BooleanValue> value) {
		this.value = value.getValue().getBooleanValue();
	}

	public final void setValue(Condition condition) {
		value = condition.isFulfilled();
	}

	@Override
	public final BooleanValue getNegated() {
		return copy().negate();
	}

	@Override
	public final BooleanValue negate() {
		value = !value;

		return this;
	}

	public final BooleanValue getAnd(Expression<? extends BooleanValue> expression) {
		return copy().and(expression);
	}

	public final BooleanValue getOr(Expression<? extends BooleanValue> expression) {
		return copy().or(expression);
	}

	public final BooleanValue getXor(Expression<? extends BooleanValue> expression) {
		return copy().xor(expression);
	}

	public final BooleanValue getNot() {
		return copy().not();
	}

	public final BooleanValue getNand(
			Expression<? extends BooleanValue> expression) {
		return copy().nand(expression);
	}

	public final BooleanValue getNor(Expression<? extends BooleanValue> expression) {
		return copy().nor(expression);
	}

	public final BooleanValue getXnor(
			Expression<? extends BooleanValue> expression) {
		return copy().xnor(expression);
	}

	public final BooleanValue and(Expression<? extends BooleanValue> expression) {
		value = value && expression.getValue().getBooleanValue();

		return this;
	}

	public final BooleanValue or(Expression<? extends BooleanValue> expression) {
		value = value || expression.getValue().getBooleanValue();

		return this;
	}

	public final BooleanValue xor(Expression<? extends BooleanValue> expression) {
		value = value ^ expression.getValue().getBooleanValue();

		return this;
	}

	public final BooleanValue not() {
		value = !value;

		return this;
	}

	public final BooleanValue nand(Expression<? extends BooleanValue> expression) {
		value = !(value && expression.getValue().getBooleanValue());

		return this;
	}

	public final BooleanValue nor(Expression<? extends BooleanValue> expression) {
		value = !(value || expression.getValue().getBooleanValue());

		return this;
	}

	public final BooleanValue xnor(Expression<? extends BooleanValue> expression) {
		value = value == expression.getValue().getBooleanValue();

		return this;
	}
}
