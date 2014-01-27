package uk.co.strangeskies.gears.utilities.factory;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Configurator<T> implements Factory<T> {
	private boolean complete = false;

	private final Supplier<T> supplier;
	private final BooleanSupplier preparator;
	private final Predicate<T> validator;

	public Configurator(Supplier<T> supplier) {
		this(supplier, null, null);
	}

	public Configurator(Supplier<T> supplier, BooleanSupplier preparator,
			Predicate<T> validator) {
		this.supplier = supplier;
		this.preparator = preparator;
		this.validator = validator;
	}

	@Override
	public final T create() {
		if (complete)
			throw new StaleBuilderStateException(this);

		T created;
		try {
			if (preparator != null && !preparator.getAsBoolean())
				throw new IncompleteBuildStateException(this);

			created = supplier.get();
			if (validator != null && !validator.test(created)) {
				throw new InvalidBuildStateException(this);
			}
		} catch (Exception e) {
			throw new InvalidBuildStateException(this, e);
		}

		complete = true;

		return created;
	}

	public final boolean isComplete() {
		return complete;
	}

	public final void assertNotStale() {
		if (complete)
			throw new StaleBuilderStateException(this);
	}

	public final void assertComplete() {
		if (!complete)
			throw new IncompleteBuildStateException(this);
	}
}
