package uk.co.elionline.gears.entity.management.impl;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentData;

public class StateComponentDataImpl<D> implements StateComponentData<D> {
	private final Entity entity;

	private final StateComponent<D> descriptor;

	private final D data;

	public StateComponentDataImpl(Entity entity, StateComponent<D> descriptor,
			D data) {
		this.entity = entity;

		this.descriptor = descriptor;

		this.data = data;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public StateComponent<D> getComponent() {
		return descriptor;
	}

	@Override
	public D getData() {
		return data;
	}
}
