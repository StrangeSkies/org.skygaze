package uk.co.strangeskies.gears.utilities.factory;

public abstract class Configurator<T> implements Factory<T> {
	private boolean stale = false;

	public final T create() {
		prepare();
		T created;
		try {
			if (!assertReady())
				throw new IncompleteBuildStateException(this);
			if (stale)
				throw new StaleBuilderStateException(this);

			created = tryCreate();
			if (!assertValid(created)) {
				throw new InvalidBuildStateException(this);
			}
		} catch (Exception e) {
			throw new InvalidBuildStateException(this, e);
		}

		stale = true;
		created(created);

		return created;
	}

	protected void prepare() {
	}

	protected void created(T created) {
	}

	public abstract T tryCreate();

	public boolean isComplete() {
		return stale;
	}

	public void assertNotStale() {
		if (stale)
			throw new StaleBuilderStateException(this);
	}

	public void assertComplete() {
		if (!stale)
			throw new IncompleteBuildStateException(this);
	}

	protected boolean assertReady() {
		return true;
	}

	protected boolean assertValid(T attempt) {
		return true;
	}
}
