package uk.co.elionline.gears.entity.scene;

public interface StatePreparator<D> {
	public void prepare(D data, AssemblyContext context);
}
