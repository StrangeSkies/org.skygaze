package uk.co.elionline.gears.entity.state.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.entity.state.StateComponentBuilderFactory;

@Component(service = StateComponentBuilderFactory.class)
public class StateComponentBuilderFactoryImpl implements
		StateComponentBuilderFactory {
	@Override
	public <D> StateComponentBuilder<D> begin() {
		return new StateComponentBuilderImpl<>();
	}
}
