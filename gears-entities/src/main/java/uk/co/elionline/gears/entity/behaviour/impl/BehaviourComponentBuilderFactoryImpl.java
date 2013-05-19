package uk.co.elionline.gears.entity.behaviour.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilderFactory;

@Component(service = BehaviourComponentBuilderFactory.class)
public class BehaviourComponentBuilderFactoryImpl implements
		BehaviourComponentBuilderFactory {
	@Override
	public BehaviourComponentBuilder begin() {
		return new BehaviourComponentBuilderImpl();
	}
}