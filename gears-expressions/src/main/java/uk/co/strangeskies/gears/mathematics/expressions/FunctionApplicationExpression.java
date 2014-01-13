package uk.co.strangeskies.gears.mathematics.expressions;

import java.util.function.Function;

import uk.co.strangeskies.gears.utilities.functions.FunctionApplicationFunction;

public class FunctionApplicationExpression<T, F> extends
		BinaryOperationExpression<F, Function<? super F, ? extends T>, T> {
	public FunctionApplicationExpression(Expression<? extends F> expression,
			Expression<? extends Function<? super F, ? extends T>> function) {
		super(expression, function, new FunctionApplicationFunction<T, F>());
	}

	public FunctionApplicationExpression(F expression,
			Expression<? extends Function<? super F, ? extends T>> function) {
		this(new ImmutableExpression<>(expression), function);
	}

	@Override
	protected T evaluate() {
		return getSecondOperand().getValue().apply(getFirstOperand().getValue());
	}
}
