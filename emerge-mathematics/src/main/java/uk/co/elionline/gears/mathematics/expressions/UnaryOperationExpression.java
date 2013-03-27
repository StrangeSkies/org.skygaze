package uk.co.elionline.gears.mathematics.expressions;

import uk.co.elionline.gears.mathematics.functions.UnaryOperation;

public class UnaryOperationExpression<R, O> extends CompoundExpression<R> {
	private Expression<? extends O> operand;
	private Expression<? extends UnaryOperation<? extends R, ? super O>> operation;

	public UnaryOperationExpression(Expression<? extends O> operand,
			Expression<? extends UnaryOperation<? extends R, ? super O>> operation) {
		super(operand);

		this.operand = operand;

		this.operation = operation;
	}

	public UnaryOperationExpression(Expression<? extends O> operand,
			UnaryOperation<? extends R, ? super O> operation) {
		this(operand, new ImmutableExpression<>(operation));
	}

	public Expression<? extends UnaryOperation<? extends R, ? super O>> getOperation() {
		return operation;
	}

	public Expression<? extends O> getOperand() {
		return operand;
	}

	public void setOperand(Expression<? extends O> operand) {
		if (this.operand != operand) {
			getDependencies().remove(this.operand);

			this.operand = operand;
			getDependencies().add(this.operand);

			update();
		}
	}

	@Override
	protected R evaluate() {
		return operation.getValue().apply(operand.getValue());
	}
}
