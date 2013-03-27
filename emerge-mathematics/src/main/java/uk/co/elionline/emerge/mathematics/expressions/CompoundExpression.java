package uk.co.elionline.emerge.mathematics.expressions;


import java.util.Collection;

import uk.co.elionline.emerge.mathematics.expressions.collections.SortedExpressionSet;
import uk.co.elionline.emerge.utilities.IdentityComparator;
import uk.co.elionline.emerge.utilities.Observer;

public abstract class CompoundExpression<T> extends ExpressionImplementation<T> {
	private final SortedExpressionSet<Object> dependencies;

	private T value;

	private boolean evaluated = false;

	public CompoundExpression(Collection<? extends Expression<?>> dependencies) {
		this();

		this.dependencies.addAll(dependencies);
	}

	public CompoundExpression(Expression<?>... dependencies) {
		this();

		this.dependencies.addAll(dependencies);
	}

	public CompoundExpression() {
		dependencies = new SortedExpressionSet<>(new IdentityComparator<>());

		dependencies.addObserver(new Observer<Void>() {
			@Override
			public void notify(Void message) {
				update();
			}
		});
	}

	protected void update() {
		if (evaluated) {
			evaluated = false;
			postUpdate();
		}
	}

	@Override
	public T getValue() {
		if (!evaluated) {
			value = evaluate();
			evaluated = true;
		}
		return value;
	}

	public boolean isEvaluated() {
		return evaluated;
	}

	protected T getCurrentValue() {
		return value;
	}

	protected abstract T evaluate();

	protected SortedExpressionSet<Object> getDependencies() {
		return dependencies;
	}
}
