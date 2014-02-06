package uk.co.strangeskies.gears.entity.assembly.impl;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import uk.co.strangeskies.gears.entity.assembly.Assemblage;
import uk.co.strangeskies.gears.entity.assembly.AssemblageView;
import uk.co.strangeskies.gears.entity.assembly.CollapsedAssemblageView;
import uk.co.strangeskies.gears.entity.assembly.StateInitialiser;
import uk.co.strangeskies.gears.entity.assembly.Variable;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.utilities.collection.ArrayListMultiHashMap;
import uk.co.strangeskies.gears.utilities.collection.FilteredListDecorator;
import uk.co.strangeskies.gears.utilities.collection.FilteredSetDecorator;
import uk.co.strangeskies.gears.utilities.collection.ListMultiMap;

public class AssemblageImpl implements Assemblage {
	private final List<Assemblage> composition;

	private final Set<Assemblage> subassemblages;
	private final Map<Assemblage, Assemblage> overriddenSubassemblages;

	private final Set<BehaviourComponent> behaviourComponents;

	private final Set<StateComponent<?>> stateComponents;
	private final ListMultiMap<StateComponent<?>, ? extends StateInitialiser<?>> statePreparators;

	private final Set<Variable<?>> variables;

	protected AssemblageImpl() {
		composition = new FilteredListDecorator<Assemblage>(
				new FilteredListDecorator.Filter<Assemblage>() {
					@Override
					public boolean filter(Assemblage assemblage) {
						if (assemblage.getCollapsedCompositionView().getComposition()
								.contains(AssemblageImpl.this)) {
							throw new IllegalArgumentException(
									"Composition assemblage graph cycle detected");
						}
						return true;
					}
				});

		subassemblages = new FilteredSetDecorator<Assemblage>(
				new FilteredSetDecorator.Filter<Assemblage>() {
					@Override
					public boolean filter(Assemblage assemblage) {
						Queue<Assemblage> subassemblages = new ArrayDeque<>();
						subassemblages.add(assemblage);

						while (!subassemblages.isEmpty()) {
							Assemblage subassemblage = subassemblages.poll();

							if (subassemblage.equals(AssemblageImpl.this)) {
								throw new IllegalArgumentException(
										"Subassemblage graph cycle detected");
							}

							subassemblages.addAll(subassemblage.getCollapsedCompositionView()
									.getSubassemblages());
						}

						return true;
					}
				});
		overriddenSubassemblages = new HashMap<>();

		behaviourComponents = new HashSet<>();

		stateComponents = new HashSet<>();
		statePreparators = new ArrayListMultiHashMap<>();

		variables = new HashSet<>();
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
	public List<Assemblage> getComposition() {
		return composition;
	}

	@Override
	public AssemblageView overrideSubassemblage(AssemblageView subassemblageMatch) {
		Assemblage overriddenAssemblage = getSubassemblage(subassemblageMatch);
		Assemblage overridingAssemblage = new AssemblageImpl();
		overridingAssemblage.getComposition().add(overriddenAssemblage);

		overriddenSubassemblages.put(overriddenAssemblage, overridingAssemblage);

		return overridingAssemblage;
	}

	@Override
	public Set<Assemblage> overrideSubassemblages(
			AssemblageView subassemblageMatch) {
		Set<Assemblage> overridingAssemblages = new HashSet<>();

		Set<Assemblage> subassemblages = getSubassemblages(subassemblageMatch);
		for (Assemblage overriddenAssemblage : subassemblages) {
			Assemblage overridingAssemblage = new AssemblageImpl();
			overridingAssemblage.getComposition().add(overriddenAssemblage);

			overriddenSubassemblages.put(overriddenAssemblage, overridingAssemblage);
		}

		return overridingAssemblages;
	}

	@Override
	public void revertOverrides(AssemblageView subassemblageMatch) {
		overriddenSubassemblages.keySet().removeAll(
				getSubassemblages(subassemblageMatch));
	}

	@Override
	public Assemblage getSubassemblage(AssemblageView subassemblageMatch) {
		Set<Assemblage> subassemblages = getSubassemblages(subassemblageMatch);
		if (subassemblages.size() != 1)
			throw new IllegalArgumentException();

		return subassemblages.iterator().next();
	}

	@Override
	public Set<Assemblage> getSubassemblages(AssemblageView subassemblageMatch) {
		Set<Assemblage> subassemblages = new HashSet<>();
		for (Assemblage subassemblage : this.subassemblages)
			if (isComposedFrom(subassemblage, subassemblageMatch))
				subassemblages.add(subassemblage);

		return subassemblages;
	}

	public boolean isComposedFrom(AssemblageView base) {
		return isComposedFrom(this, base);
	}

	public static boolean isComposedFrom(AssemblageView assemblage,
			AssemblageView base) {
		if (assemblage == base) {
			return true;
		}

		Queue<Assemblage> compositionAssemblages = new ArrayDeque<>();
		compositionAssemblages.addAll(assemblage.getComposition());

		while (!compositionAssemblages.isEmpty()) {
			Assemblage compositionAssemblage = compositionAssemblages.poll();
			if (base == compositionAssemblage) {
				return true;
			}
			compositionAssemblages.addAll(compositionAssemblage.getComposition());
		}

		return false;
	}

	@Override
	public void revertOverrides() {
		overriddenSubassemblages.clear();
	}

	@Override
	public CollapsedAssemblageView getCollapsedCompositionView() {
		return new CollapsedCompositionAssemblageView(this);
	}
}
