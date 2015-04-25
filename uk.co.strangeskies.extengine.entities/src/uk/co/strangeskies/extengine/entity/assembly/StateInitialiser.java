package uk.co.strangeskies.extengine.entity.assembly;

public interface StateInitialiser<D> {
	public void initialise(D data, AssemblyContext context);
}
