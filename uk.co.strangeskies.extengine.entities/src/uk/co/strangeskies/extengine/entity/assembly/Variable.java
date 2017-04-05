package uk.co.strangeskies.extengine.entity.assembly;

import java.util.function.Function;
import java.util.function.Supplier;

import uk.co.strangeskies.utilities.Copyable;
import uk.co.strangeskies.utilities.Isomorphism;

public class Variable<T> {
	private final String name;
	private final Function<Isomorphism, ? extends T> function;

	public Variable(String name, Copyable<? extends T> model) {
		this(name, i -> i.byIdentity().getCopy(model));
	}

	public Variable(String name, T constant) {
		this(name, i -> i.byIdentity().getMapping(constant, Function.identity()));
	}

	public Variable(String name, Supplier<? extends T> supplier) {
		this(name, i -> i.byIdentity().getMapping(supplier, Supplier::get));
	}

	public Variable(String name, Function<Isomorphism, ? extends T> function) {
		this.name = name;
		this.function = function;
	}

	public String getName() {
		return name;
	}

	public T getValue(Isomorphism context) {
		return function.apply(context);
	}
}
