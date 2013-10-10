package uk.co.strangeskies.gears.entity.assembly;

public interface StateInitialiser<D> {
	public void initialise(D data, AssemblyContext context);
}
