package uk.co.elionline.gears.entities.state;

public interface StateComponentBuilderFactory {
	public <D> StateComponentBuilder<D> begin();
}
