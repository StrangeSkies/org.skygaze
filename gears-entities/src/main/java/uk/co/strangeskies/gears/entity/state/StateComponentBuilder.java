package uk.co.strangeskies.gears.entity.state;

import uk.co.strangeskies.gears.utilities.Factory;

public interface StateComponentBuilder {
	public <D> StateComponentConfigurator<D> data(Factory<? extends D> dataFactory);
}
