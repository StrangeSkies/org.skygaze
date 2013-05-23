package uk.co.elionline.gears.entity.assembly;

import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.state.StateComponent;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface Assemblage {
	public Set</* @ReadOnly */Assemblage> getBaseAssemblages();

	public Set<Assemblage> getSubassemblages();

	public Set<Variable<?>> getVariables();

	public Set<BehaviourComponent> getBehaviours();

	public Set<StateComponent<?>> getStates();

	public <D> List<StateInitialiser<D>> getInitialisers(StateComponent<D> state);
}
