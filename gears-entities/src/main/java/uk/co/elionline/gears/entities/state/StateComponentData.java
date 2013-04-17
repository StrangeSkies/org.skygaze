package uk.co.elionline.gears.entities.state;

import java.util.UUID;

import uk.co.elionline.gears.utilities.Property;

public class StateComponentData<D> implements /* @ReadOnly */Property<D, D> {
	private final UUID entity;

	private final StateComponent<D> descriptor;

	private D data;

	public StateComponentData(UUID entity, StateComponent<D> descriptor, D data) {
		this.entity = entity;

		this.descriptor = descriptor;

		this.data = data;
	}

	public final UUID getEntity() {
		return entity;
	}

	public final StateComponent<D> getComponent() {
		return descriptor;
	}

	@Override
	public D set(D to) {
		return data = to;
	}

	@Override
	public final D get() {
		return data;
	}
}
