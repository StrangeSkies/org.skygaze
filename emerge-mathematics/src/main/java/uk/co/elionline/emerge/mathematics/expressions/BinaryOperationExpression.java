package uk.co.elionline.emerge.mathematics.expressions;

import uk.co.elionline.emerge.mathematics.functions.BinaryOperation;

public class BinaryOperationExpression<R, O1, O2> extends CompoundExpression<R> {
	private Expression<? extends O1> firstOperand;
	private Expression<? extends O2> secondOperand;
	private Expression<? extends BinaryOperation<? extends R, ? super O1, ? super O2>> operation;

	public BinaryOperationExpression(
			Expression<? extends O1> firstOperand,
			Expression<? extends O2> secondOperand,
			Expression<? extends BinaryOperation<? extends R, ? super O1, ? super O2>> operation) {
		super(firstOperand, secondOperand);

		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;

		this.operation = operation;
	}

	public BinaryOperationExpression(Expression<? extends O1> firstOperand,
			Expression<? extends O2> secondOperand,
			BinaryOperation<? extends R, ? super O1, ? super O2> operation) {
		this(firstOperand, secondOperand, new ImmutableExpression<>(operation));
	}

	public Expression<? extends BinaryOperation<? extends R, ? super O1, ? super O2>> getOperation() {
		return operation;
	}

	public Expression<? extends O1> getFirstOperand() {
		return firstOperand;
	}

	public Expression<? extends O2> getSecondOperand() {
		return secondOperand;
	}

	public void setOperands(Expression<? extends O1> firstOperand,
			Expression<? extends O2> secondOperand) {
		if (this.firstOperand != firstOperand) {
			if (this.secondOperand != secondOperand) {
				getDependencies().remove(this.firstOperand);
				getDependencies().remove(this.secondOperand);

				this.firstOperand = firstOperand;
				this.secondOperand = secondOperand;

				getDependencies().add(this.firstOperand);
				getDependencies().add(this.secondOperand);

				update();
			} else {
				setFirstOperand(firstOperand);
			}
		} else {
			if (this.secondOperand != secondOperand) {
				setSecondOperand(secondOperand);
			}
		}
	}

	public void setFirstOperand(Expression<? extends O1> operand) {
		if (this.firstOperand != operand) {
			if (firstOperand != secondOperand) {
				getDependencies().remove(firstOperand);
			}

			firstOperand = operand;
			getDependencies().add(firstOperand);

			update();
		}
	}

	public void setSecondOperand(Expression<? extends O2> operand) {
		if (this.secondOperand != operand) {
			if (firstOperand != secondOperand) {
				getDependencies().remove(secondOperand);
			}

			secondOperand = operand;
			getDependencies().add(secondOperand);

			update();
		}
	}

	@Override
	protected R evaluate() {
		return operation.getValue().apply(firstOperand.getValue(),
				secondOperand.getValue());
	}
}
