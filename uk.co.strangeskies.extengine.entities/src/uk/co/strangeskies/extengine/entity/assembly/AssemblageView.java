package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.management.EntityManager;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.Named;

public interface AssemblageView extends Named {
	Set<Assemblage> getComposition();

	Set<Assemblage> getSubassemblages();

	AssemblageView getSubassemblage(AssemblageView subassemblageMatch);

	Set<Assemblage> getSubassemblages(AssemblageView subassemblageMatch);

	/*
	 * TODO make deep-copyable instead of factories? add deep copy to Copyable
	 * interface?
	 */
	Set<Variable<?>> getVariables();

	Set<BehaviourComponent> getBehaviours();

	Set<StateComponent<?, ?>> getStates();

	/*
	 * TODO add if absent
	 * 
	 * TODO surely only need one initialiser per state per assemblage? though the
	 * view will have more if it collapsed over the composition... how to best
	 * represent this?
	 */
	<D> Set<StateInitialiser<D>> getInitialisers(StateComponent<D, ?> state);

	Entity assemble(EntityManager entityManager);
}
