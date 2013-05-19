package uk.co.elionline.gears.entity.state;

import uk.co.elionline.gears.entity.Entity;

public interface StateComponentData<D> {
	public Entity getEntity();

	public StateComponent<D> getComponent();

	public D getData();
}
