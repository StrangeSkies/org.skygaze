package uk.co.elionline.gears.entities.behaviour.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilderFactory;

@Component(service = BehaviourComponentBuilderFactory.class)
public class BehaviourComponentBuilderFactoryImpl implements
		BehaviourComponentBuilderFactory {
	@Override
	public BehaviourComponentBuilder begin() {
		return new BehaviourComponentBuilderImpl();
	}
}