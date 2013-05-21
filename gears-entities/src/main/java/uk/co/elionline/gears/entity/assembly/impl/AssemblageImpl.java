package uk.co.elionline.gears.entity.assembly.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.entity.assembly.Assemblage;
import uk.co.elionline.gears.entity.assembly.StateInitialiser;
import uk.co.elionline.gears.entity.assembly.Variable;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.collections.ArrayListMultiHashMap;
import uk.co.elionline.gears.utilities.collections.ListMultiMap;

public class AssemblageImpl implements Assemblage {
	private Assemblage base;

	private final Set<Assemblage> subassemblages;

	private final Set<BehaviourComponent> behaviourComponents;

	private final Set<StateComponent<?>> stateComponents;
	private final ListMultiMap<StateComponent<?>, ? extends StateInitialiser<?>> statePreparators;

	private final Set<Variable<?>> variables;

	protected AssemblageImpl() {
		subassemblages = new HashSet<>();

		behaviourComponents = new HashSet<>();

		stateComponents = new HashSet<>();
		statePreparators = new ArrayListMultiHashMap<>();

		variables = new HashSet<>();
	}

	@Override
	public Assemblage getBase() {
		return base;
	}

	private void setBase(Assemblage base) {
		this.base = base;
	}

	@Override
	public Assemblage derive() {
		AssemblageImpl derivation = new AssemblageImpl();
		derivation.setBase(this);

		return derivation;
	}

	@Override
	public Set<Assemblage> getSubassemblages() {
		return subassemblages;
	}

	@Override
	public Set<Variable<?>> getVariables() {
		return variables;
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return behaviourComponents;
	}

	@Override
	public Set<StateComponent<?>> getStates() {
		return stateComponents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> List<StateInitialiser<D>> getInitialisers(
			final StateComponent<D> state) {
		return (List<StateInitialiser<D>>) statePreparators.get(state);
	}

	@Override
	public Assemblage copy() {
		AssemblageImpl copy = new AssemblageImpl();

		copy.setBase(getBase());
		copy.getBehaviours().addAll(getBehaviours());
		copy.getStates().addAll(getStates());
		for (StateComponent<?> state : statePreparators.keySet()) {
			setCopyPreparators(copy, state);
		}
		copy.getVariables().addAll(getVariables());
		copy.getSubassemblages().addAll(getSubassemblages());

		return copy;
	}

	private <T> void setCopyPreparators(AssemblageImpl copy,
			StateComponent<T> state) {
		copy.getInitialisers(state).addAll(getInitialisers(state));
	}
}
