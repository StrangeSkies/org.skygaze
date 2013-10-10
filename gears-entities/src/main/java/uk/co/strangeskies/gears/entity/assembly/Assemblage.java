package uk.co.strangeskies.gears.entity.assembly;

import java.util.List;
import java.util.Set;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.state.StateComponent;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface Assemblage {
	public List<Assemblage> getComposition();

	public/*@ReadOnly*/Assemblage getCollapsedCompositionView();

	public Set<Assemblage> getSubassemblages();

	public Assemblage getSubassemblage(Assemblage subassemblageMatch);

	public Set<Assemblage> getSubassemblages(Assemblage subassemblageMatch);

	public Assemblage overrideSubassemblage(
	/*@Mutable Assemblage this, */Assemblage subassemblageMatch);

	public Set<Assemblage> overrideSubassemblages(
	/*@Mutable Assemblage this, */Assemblage subassemblageMatch);

	public void revertOverrides(
	/*@Mutable Assemblage this, */Assemblage subassemblageMatch);

	public void revertOverrides(/*@Mutable Assemblage this*/);

	public Set<Variable<?>> getVariables();

	public Set<BehaviourComponent> getBehaviours();

	public Set<StateComponent<?>> getStates();

	public <D> List<StateInitialiser<D>> getInitialisers(StateComponent<D> state);
}
