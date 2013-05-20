package uk.co.elionline.gears.entity.scene;

import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.state.StateComponent;

public interface AssemblyContext {
	public AssemblyContext getSupercontext();

	public Set<AssemblyContext> getSubcontexts(Assemblage subassemblageMatch,
			Assemblage... subassemblageMatchPattern);

	public/* @ReadOnly */Entity getEntity();

	public <D> /* @ReadOnly */D getData(StateComponent<D> state);

	public <T> /* @ReadOnly */T getValue(AssemblageVariable<T> variable);
}
