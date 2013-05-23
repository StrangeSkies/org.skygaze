package uk.co.elionline.gears.entity.behaviour.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentConfigurator;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;

@Component(service = BehaviourComponentBuilder.class)
public class BehaviourComponentBuilderImpl implements BehaviourComponentBuilder {
	@Override
	public BehaviourComponentConfigurator process(BehaviourProcess process) {
		return new BehaviourComponentConfiguratorImpl(process);
	}
}