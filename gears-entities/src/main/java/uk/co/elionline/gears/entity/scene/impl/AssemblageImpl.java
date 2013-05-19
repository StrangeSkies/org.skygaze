package uk.co.elionline.gears.entity.scene.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.scene.Assemblage;
import uk.co.elionline.gears.entity.scene.AssemblageVariable;
import uk.co.elionline.gears.entity.scene.StatePreparator;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.collections.ArrayListMultiHashMap;
import uk.co.elionline.gears.utilities.collections.ListMultiMap;

/**
 * An EntityAssemblage provides a set of states and behaviours which combine to
 * describe a potential Entity. An assemblage can be assembled onto an empty
 * Entity, or onto an existing Entity, optionally overriding any existing
 * behaviours or states which conflict with those described in the model.
 * 
 * @author Elias N Vasylenko
 * 
 */
public class AssemblageImpl implements Assemblage {
	private Assemblage base;

	private final Set<Assemblage> subassemblages;

	private final Set<BehaviourComponent> behaviourComponents;

	private final Set<StateComponent<?>> stateComponents;
	private final ListMultiMap<StateComponent<?>, StatePreparator<?>> statePreparators;

	private final Set<AssemblageVariable<?>> variables;

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
	public Set<Assemblage> subassemblages() {
		return subassemblages;
	}

	@Override
	public Set<AssemblageVariable<?>> variables() {
		return variables;
	}

	@Override
	public Set<BehaviourComponent> behaviours() {
		return behaviourComponents;
	}

	@Override
	public Set<StateComponent<?>> states() {
		return stateComponents;
	}

	@Override
	public <D> List<StatePreparator<D>> getPreparators(StateComponent<D> state) {
		@SuppressWarnings("unchecked")
		List<StatePreparator<D>> preparators = (List<StatePreparator<D>>) statePreparators
				.get(state);

		if (preparators == null) {
			preparators = Collections.emptyList();
		} else {
			preparators = Collections.unmodifiableList(preparators);
		}

		return preparators;
	}

	@Override
	public Assemblage copy() {
		AssemblageImpl copy = new AssemblageImpl();

		copy.setBase(getBase());
		copy.behaviours().addAll(behaviours());
		copy.states().addAll(states());
		copy.preparators().putAll(preparators());
		copy.variables().addAll(variables());
		copy.subassemblages().addAll(subassemblages());

		return copy;
	}
}
