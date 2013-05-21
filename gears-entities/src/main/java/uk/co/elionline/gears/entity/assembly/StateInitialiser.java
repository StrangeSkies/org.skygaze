package uk.co.elionline.gears.entity.assembly;

public interface StateInitialiser<D> {
	public void initialise(D data, AssemblyContext context);
}
