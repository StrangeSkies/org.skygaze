package uk.co.strangeskies.extengine.entity.assembly.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import uk.co.strangeskies.extengine.entity.assembly.Assemblage;
import uk.co.strangeskies.extengine.entity.assembly.AssemblageView;
import uk.co.strangeskies.extengine.entity.assembly.CollapsedAssemblageView;
import uk.co.strangeskies.extengine.entity.assembly.StateInitialiser;
import uk.co.strangeskies.extengine.entity.assembly.Variable;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.Decorator;
import uk.co.strangeskies.utilities.collection.decorator.ListDecorator;
import uk.co.strangeskies.utilities.collection.decorator.SetDecorator;

public class CollapsedCompositionAssemblageView extends
		Decorator<AssemblageView> implements
/* @ReadOnly */CollapsedAssemblageView {
	public CollapsedCompositionAssemblageView(AssemblageView assemblage) {
		super(assemblage);
	}

	protected <C extends Collection<T>, T> C collapseIntoCollection(
			C collection,
			Function<AssemblageView, ? extends Collection<? extends T>> collectionFunction) {
		List<AssemblageView> composition = new ArrayList<>();
		composition.add(getComponent());

		while (!composition.isEmpty()) {
			AssemblageView assemblage = composition.get(0);
			composition.remove(0);
			collection.addAll(collectionFunction.apply(assemblage));

			composition.addAll(0, assemblage.getComposition());
		}

		return collection;
	}

	private List<Assemblage> list() {
		return new ArrayList<Assemblage>();
	}

	@Override
	public List<Assemblage> getComposition() {
		return new ListDecorator<>(() -> collapseIntoCollection(list(),
				AssemblageView::getComposition));
	}

	@Override
	public Set<Assemblage> getSubassemblages() {
		return new SetDecorator<>(() -> collapseIntoCollection(new HashSet<>(),
				AssemblageView::getSubassemblages));
	}

	@Override
	public AssemblageView getSubassemblage(AssemblageView subassemblageMatch) {
		Set<Assemblage> subassemblages = getSubassemblages(subassemblageMatch);
		if (subassemblages.size() != 1)
			throw new IllegalArgumentException();

		return subassemblages.iterator().next();
	}

	@Override
	public Set<Assemblage> getSubassemblages(
			final AssemblageView subassemblageMatch) {
		return new SetDecorator<>(() -> collapseIntoCollection(new HashSet<>(),
				input -> input.getSubassemblages(subassemblageMatch)));
	}

	@Override
	public Set<Variable<?>> getVariables() {
		return new SetDecorator<>(() -> collapseIntoCollection(new HashSet<>(),
				AssemblageView::getVariables));
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return new SetDecorator<>(() -> collapseIntoCollection(new HashSet<>(),
				AssemblageView::getBehaviours));
	}

	@Override
	public Set<StateComponent<?>> getStates() {
		return new SetDecorator<>(() -> collapseIntoCollection(new HashSet<>(),
				AssemblageView::getStates));
	}

	@Override
	public <D> List<StateInitialiser<D>> getInitialisers(
			final StateComponent<D> state) {
		return new ListDecorator<>(() -> collapseIntoCollection(
				new ArrayList<>(), input -> input.getInitialisers(state)));
	}
}
