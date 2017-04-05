package uk.co.strangeskies.extengine.entity.assembly.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.assembly.Pattern;
import uk.co.strangeskies.extengine.entity.assembly.PatternView;
import uk.co.strangeskies.extengine.entity.assembly.StateInitialiser;
import uk.co.strangeskies.extengine.entity.assembly.Variable;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.collection.SetDecorator;

public class CollapsedCompositionPatternView implements/* @ReadOnly */PatternView {
	private final PatternView component;

	public CollapsedCompositionPatternView(PatternView pattern) {
		this.component = pattern;
	}

	protected PatternView getComponent() {
		return component;
	}

	@Override
	public String getName() {
		return component.getName();
	}

	protected <T> List<T> collapseIntoList(Function<PatternView, ? extends Collection<? extends T>> collectionFunction) {
		return collapseIntoCollection(new ArrayList<>(), collectionFunction, (l, i) -> l.add(0, i));
	}

	protected <T> Set<T> collapseIntoSet(Function<PatternView, ? extends Collection<? extends T>> collectionFunction) {
		return new LinkedHashSet<>(collapseIntoList(collectionFunction));
	}

	protected <C extends Collection<T>, T> C collapseIntoCollection(C collection,
			Function<PatternView, ? extends Collection<? extends T>> collectionFunction, BiConsumer<C, T> add) {
		List<PatternView> composition = new ArrayList<>();
		composition.add(getComponent());

		while (!composition.isEmpty()) {
			PatternView pattern = composition.get(0);
			composition.remove(0);
			for (T item : collectionFunction.apply(pattern))
				add.accept(collection, item);

			composition.addAll(0, pattern.getComposition());
		}

		return collection;
	}

	@Override
	public Set<Pattern> getComposition() {
		return SetDecorator.over(collapseIntoSet(PatternView::getComposition));
	}

	@Override
	public Set<Pattern> getChildren() {
		return SetDecorator.over(collapseIntoSet(a -> a.getChildren()));
	}

	@Override
	public PatternView getChild(PatternView childMatch) {
		Set<Pattern> children = getChildren(childMatch);
		if (children.size() != 1)
			throw new IllegalArgumentException();

		return children.iterator().next();
	}

	@Override
	public Set<Pattern> getChildren(final PatternView childMatch) {
		return SetDecorator.over(collapseIntoSet(input -> input.getChildren(childMatch)));
	}

	@Override
	public Set<Variable<?>> getVariables() {
		return SetDecorator.over(collapseIntoSet(PatternView::getVariables));
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return SetDecorator.over(collapseIntoSet(PatternView::getBehaviours));
	}

	@Override
	public Set<StateComponent<?, ?>> getStates() {
		return SetDecorator.over(collapseIntoSet(PatternView::getStates));
	}

	@Override
	public <D> Set<StateInitialiser<D>> getInitialisers(final StateComponent<D, ?> state) {
		return SetDecorator.over(collapseIntoSet(input -> input.getInitialisers(state)));
	}

	@Override
	public Entity assemble(EntityComponentSystem entityManager) {
		return component.assemble(entityManager);
	}
}
