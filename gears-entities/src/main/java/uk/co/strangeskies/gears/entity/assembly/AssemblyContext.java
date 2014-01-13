package uk.co.strangeskies.gears.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.state.StateComponent;

public interface AssemblyContext {
	public AssemblyContext getSupercontext();

	public/* @ReadOnly */Set<AssemblyContext> getSubcontexts(
			AssemblageView... subassemblageMatchPattern);

	public/* @ReadOnly */AssemblyContext getSubcontext(
			AssemblageView... subassemblageMatchPattern);

	public/* @ReadOnly */Entity getEntity();

	public/* @ReadOnly */AssemblageView getAssemblage();

	public <D> /* @ReadOnly */D getData(StateComponent<D> state);

	public <T> /* @ReadOnly */T getValue(Variable<T> variable);
}
