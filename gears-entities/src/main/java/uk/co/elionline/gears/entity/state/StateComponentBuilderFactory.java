package uk.co.elionline.gears.entity.state;

public interface StateComponentBuilderFactory {
	public <D> StateComponentBuilder<D> begin();
}
