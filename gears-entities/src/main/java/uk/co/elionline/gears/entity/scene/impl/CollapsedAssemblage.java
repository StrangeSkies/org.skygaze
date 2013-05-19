package uk.co.elionline.gears.entity.scene.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.scene.Assemblage;
import uk.co.elionline.gears.entity.scene.AssemblageVariable;
import uk.co.elionline.gears.entity.scene.StatePreparator;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.collections.MergedCollectionSet;

public class CollapsedAssemblage implements /*@ReadOnly*/Assemblage {
	private final Assemblage base;
	private Set<Assemblage> effectiveSubassemblages;
	private Set<AssemblageVariable<?>> effectiveVariables;
	private Set<BehaviourComponent> effectiveBehaviours;
	private Set<StateComponent<?>> effectiveStates;

	public CollapsedAssemblage(Assemblage base) {
		this(base, false);
	}

	public CollapsedAssemblage(Assemblage base, boolean lazy) {
		this.base = base;
	}

	@Override
	public Assemblage copy() {
		return new CollapsedAssemblage(getBase());
	}

	@Override
	public Assemblage getBase() {
		return base;
	}

	@Override
	public Assemblage derive() {
		return base.derive();
	}

	@Override
	public/*@ReadOnly*/Set<Assemblage> subassemblages() {
		if (effectiveSubassemblages == null) {
			collapseEffectiveSubassemblages();
		}
		return effectiveSubassemblages;
	}

	private void collapseEffectiveSubassemblages() {
		Assemblage base = getBase();
		Set<Set<Assemblage>> allSubassemblages = new HashSet<>();
		do {
			allSubassemblages.add(base.subassemblages());
			base = base.getBase();
		} while (base != null);
		effectiveSubassemblages = new MergedCollectionSet<>(allSubassemblages);
	}

	@Override
	public/*@ReadOnly*/Set<AssemblageVariable<?>> variables() {
		if (effectiveVariables == null) {
			collapseEffectiveVariables();
		}
		return effectiveVariables;
	}

	private void collapseEffectiveVariables() {
		Assemblage base = getBase();
		Set<Set<AssemblageVariable<?>>> allVariables = new HashSet<>();
		do {
			allVariables.add(base.variables());
			base = base.getBase();
		} while (base != null);
		effectiveVariables = new MergedCollectionSet<>(allVariables);
	}

	@Override
	public/*@ReadOnly*/Set<BehaviourComponent> behaviours() {
		if (effectiveBehaviours == null) {
			collapseEffectiveBehaviours();
		}
		return effectiveBehaviours;
	}

	private void collapseEffectiveBehaviours() {
		Assemblage base = getBase();
		Set<Set<BehaviourComponent>> allBehaviours = new HashSet<>();
		do {
			allBehaviours.add(base.behaviours());
			base = base.getBase();
		} while (base != null);
		effectiveBehaviours = new MergedCollectionSet<>(allBehaviours);
	}

	@Override
	public/*@ReadOnly*/Set<StateComponent<?>> states() {
		if (effectiveStates == null) {
			collapseEffectiveStates();
		}
		return effectiveStates;
	}

	private void collapseEffectiveStates() {
		Assemblage base = getBase();
		Set<Set<StateComponent<?>>> allStates = new HashSet<>();
		do {
			allStates.add(base.states());
			base = base.getBase();
		} while (base != null);
		effectiveStates = new MergedCollectionSet<>(allStates);
	}

	@Override
	public <D> /*@ReadOnly*/List<StatePreparator<D>> getPreparators(
			StateComponent<D> state) {
		return;
	}
}
