package uk.co.strangeskies.extengine.entity.state.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.extengine.entity.state.StateComponentBuilder;
import uk.co.strangeskies.extengine.entity.state.StateComponentConfigurator;

@Component(service = StateComponentBuilder.class)
public class StateComponentBuilderImpl implements StateComponentBuilder {
	@Override
	public StateComponentConfigurator<Object> configure() {
		return new StateComponentConfiguratorImpl<>();
	}
}
