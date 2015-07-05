package uk.co.strangeskies.extengine.entity.assembly;

import java.util.List;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponent;

public interface AssemblageView {
	public List<Assemblage> getComposition();

	public abstract Set<Assemblage> getSubassemblages();

	public abstract AssemblageView getSubassemblage(
			AssemblageView subassemblageMatch);

	public abstract Set<Assemblage> getSubassemblages(
			AssemblageView subassemblageMatch);

	public abstract Set<Variable<?>> getVariables();

	public abstract Set<BehaviourComponent> getBehaviours();

	public abstract Set<StateComponent<?, ?>> getStates();

	public abstract <D> List<StateInitialiser<D>> getInitialisers(
			StateComponent<D, ?> state);
}
