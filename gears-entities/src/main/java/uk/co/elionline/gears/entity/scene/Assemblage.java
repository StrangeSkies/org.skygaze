package uk.co.elionline.gears.entity.scene;

import java.util.Set;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.Copyable;

/**
 * An EntityAssemblage provides a set of states and behaviours which combine to
 * describe a potential Entity. An assemblage can be assembled onto an empty
 * Entity, or onto an existing Entity, optionally overriding any existing
 * behaviours or states which conflict with those described in the model.
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface Assemblage extends Copyable<Assemblage> {
	public/*@ReadOnly*/Assemblage getBase();

	public Assemblage derive();

	public Set<Assemblage> subassemblages();

	public Set<AssemblageVariable<?>> variables();

	public Set<BehaviourComponent> behaviours();

	public Set<StateComponent<?>> states();
}
