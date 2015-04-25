package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.state.StateComponent;

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
