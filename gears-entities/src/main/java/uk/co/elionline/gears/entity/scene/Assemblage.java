package uk.co.elionline.gears.entity.scene;

import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.Copyable;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface Assemblage extends Copyable<Assemblage> {
	public/* @ReadOnly */Assemblage getBase();

	public Assemblage derive();

	public Set<Assemblage> getSubassemblages();

	public Set<AssemblageVariable<?>> getVariables();

	public Set<BehaviourComponent> getBehaviours();

	public Set<StateComponent<?>> getStates();

	public Set<StateComponent<?>> getPreparatorStates();

	public <D> List<StatePreparator<D>> getPreparators(StateComponent<D> state);
}
