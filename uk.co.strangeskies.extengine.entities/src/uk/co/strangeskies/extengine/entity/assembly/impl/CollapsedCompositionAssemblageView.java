package uk.co.strangeskies.extengine.entity.assembly.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import uk.co.strangeskies.extengine.entity.assembly.Assemblage;
import uk.co.strangeskies.extengine.entity.assembly.AssemblageView;
import uk.co.strangeskies.extengine.entity.assembly.CollapsedAssemblageView;
import uk.co.strangeskies.extengine.entity.assembly.StateInitialiser;
import uk.co.strangeskies.extengine.entity.assembly.Variable;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.collection.ListDecorator;
import uk.co.strangeskies.utilities.collection.SetDecorator;

public class CollapsedCompositionAssemblageView implements/* @ReadOnly */CollapsedAssemblageView {
	private final AssemblageView component;

	public CollapsedCompositionAssemblageView(AssemblageView assemblage) {
		this.component = assemblage;
	}

	protected AssemblageView getComponent() {
		return component;
	}

	protected <T> List<T> collapseIntoList(
			Function<AssemblageView, ? extends Collection<? extends T>> collectionFunction) {
		return collapseIntoCollection(new ArrayList<>(), collectionFunction, (l, i) -> l.add(0, i));
	}

	protected <T> Set<T> collapseIntoSet(
			Function<AssemblageView, ? extends Collection<? extends T>> collectionFunction) {
		return new LinkedHashSet<>(collapseIntoList(collectionFunction));
	}

	protected <C extends Collection<T>, T> C collapseIntoCollection(C collection,
			Function<AssemblageView, ? extends Collection<? extends T>> collectionFunction, BiConsumer<C, T> add) {
		List<AssemblageView> composition = new ArrayList<>();
		composition.add(getComponent());

		while (!composition.isEmpty()) {
			AssemblageView assemblage = composition.get(0);
			composition.remove(0);
			for (T item : collectionFunction.apply(assemblage))
				add.accept(collection, item);

			composition.addAll(0, assemblage.getComposition());
		}

		return collection;
	}

	@Override
	public List<Assemblage> getComposition() {
		return ListDecorator.over(collapseIntoList(AssemblageView::getComposition));
	}

	@Override
	public Set<Assemblage> getSubassemblages() {
		return SetDecorator.over(collapseIntoSet(a -> a.getSubassemblages()));
	}

	@Override
	public AssemblageView getSubassemblage(AssemblageView subassemblageMatch) {
		Set<Assemblage> subassemblages = getSubassemblages(subassemblageMatch);
		if (subassemblages.size() != 1)
			throw new IllegalArgumentException();

		return subassemblages.iterator().next();
	}

	@Override
	public Set<Assemblage> getSubassemblages(final AssemblageView subassemblageMatch) {
		return SetDecorator.over(collapseIntoSet(input -> input.getSubassemblages(subassemblageMatch)));
	}

	@Override
	public Set<Variable<?>> getVariables() {
		return SetDecorator.over(collapseIntoSet(AssemblageView::getVariables));
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return SetDecorator.over(collapseIntoSet(AssemblageView::getBehaviours));
	}

	@Override
	public Set<StateComponent<?, ?>> getStates() {
		return SetDecorator.over(collapseIntoSet(AssemblageView::getStates));
	}

	@Override
	public <D> List<StateInitialiser<D>> getInitialisers(final StateComponent<D, ?> state) {
		return ListDecorator.over(collapseIntoList(input -> input.getInitialisers(state)));
	}
}
