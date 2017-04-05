package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.state.StateComponent;

public interface AssemblyContext {
	public AssemblyContext getSupercontext();

	public/* @ReadOnly */Set<AssemblyContext> getSubcontexts(
			PatternView... childMatchPattern);

	public/* @ReadOnly */AssemblyContext getSubcontext(
			PatternView... childMatchPattern);

	public/* @ReadOnly */Entity getEntity();

	public/* @ReadOnly */PatternView getPattern();

	public <D> /* @ReadOnly */D getData(StateComponent<D, ?> state);

	public <T> /* @ReadOnly */T getValue(Variable<T> variable);
}
