package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.Named;

public interface PatternView extends Named {
	Set<Pattern> getComposition();

	Set<Pattern> getChildren();

	PatternView getChild(PatternView childMatch);

	Set<Pattern> getChildren(PatternView childMatch);

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
	 * TODO surely only need one initialiser per state per pattern? though the
	 * view will have more if it collapsed over the composition... how to best
	 * represent this?
	 */
	<D> Set<StateInitialiser<D>> getInitialisers(StateComponent<D, ?> state);

	Entity assemble(EntityComponentSystem entityManager);
}
