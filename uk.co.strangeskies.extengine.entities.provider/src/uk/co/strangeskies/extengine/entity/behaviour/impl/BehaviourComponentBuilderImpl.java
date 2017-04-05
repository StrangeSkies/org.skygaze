package uk.co.strangeskies.extengine.entity.behaviour.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentConfigurator;

@Component(service = BehaviourComponentBuilder.class)
public class BehaviourComponentBuilderImpl implements BehaviourComponentBuilder {
	@Override
	public BehaviourComponentConfigurator configure() {
		return new BehaviourComponentConfiguratorImpl();
	}
}