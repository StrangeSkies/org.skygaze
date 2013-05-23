package uk.co.elionline.gears.entity.assembly;

import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.state.StateComponent;

public interface AssemblyContext {
	public AssemblyContext getSupercontext();

	public/* @ReadOnly */Set<AssemblyContext> getSubcontexts(
			Assemblage... subassemblageMatchPattern);

	public/* @ReadOnly */AssemblyContext getSubcontext(
			Assemblage... subassemblageMatchPattern);

	public/* @ReadOnly */Entity getEntity();

	public/* @ReadOnly */Assemblage getAssemblage();

	public <D> /* @ReadOnly */D getData(StateComponent<D> state);

	public <T> /* @ReadOnly */T getValue(Variable<T> variable);
}
