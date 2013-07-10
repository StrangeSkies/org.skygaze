package uk.co.elionline.gears.entity.state.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.entity.state.StateComponentConfigurator;
import uk.co.elionline.gears.utilities.Factory;

@Component(service = StateComponentBuilder.class)
public class StateComponentBuilderImpl implements StateComponentBuilder {
	@Override
	public <D> StateComponentConfigurator<D> data(
			Factory<? extends D> dataFactory) {
		return new StateComponentConfiguratorImpl<>(dataFactory);
	}
}
