package uk.co.strangeskies.gears.entity.behaviour.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponentConfigurator;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcess;

@Component(service = BehaviourComponentBuilder.class)
public class BehaviourComponentBuilderImpl implements BehaviourComponentBuilder {
	@Override
	public BehaviourComponentConfigurator process(BehaviourProcess process) {
		return new BehaviourComponentConfiguratorImpl(process);
	}
}