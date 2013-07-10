package uk.co.elionline.gears.entity.state;

import uk.co.elionline.gears.utilities.Factory;

public interface StateComponentBuilder {
	public <D> StateComponentConfigurator<D> data(Factory<? extends D> dataFactory);
}
