package uk.co.strangeskies.gears.entity.assembly.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import uk.co.strangeskies.gears.entity.assembly.Assemblage;
import uk.co.strangeskies.gears.entity.assembly.AssemblageView;
import uk.co.strangeskies.gears.entity.assembly.CollapsedAssemblageView;
import uk.co.strangeskies.gears.entity.assembly.StateInitialiser;
import uk.co.strangeskies.gears.entity.assembly.Variable;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.utilities.Decorator;
import uk.co.strangeskies.gears.utilities.Property;
import uk.co.strangeskies.gears.utilities.collections.ListDecorator;
import uk.co.strangeskies.gears.utilities.collections.SetDecorator;

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
		return new ListDecorator<>(new Property<List<Assemblage>, Object>() {
			@Override
			public List<Assemblage> set(Object to) {
				return null;
			}

			@Override
			public List<Assemblage> get() {
				return collapseIntoCollection(list(), input -> input.getComposition());
			}
		});
	}

	@Override
	public Set<Assemblage> getSubassemblages() {
		return new SetDecorator<>(new Property<Set<Assemblage>, Object>() {
			@Override
			public Set<Assemblage> set(Object to) {
				return null;
			}

			@Override
			public Set<Assemblage> get() {
				return collapseIntoCollection(new HashSet<Assemblage>(),
						new Function<AssemblageView, Set<Assemblage>>() {
							@Override
							public Set<Assemblage> apply(AssemblageView input) {
								return input.getSubassemblages();
							}
						});
			}
		});
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
		return new SetDecorator<>(collapseIntoCollection(new HashSet<Assemblage>(),
				new Function<AssemblageView, Set<Assemblage>>() {
					@Override
					public Set<Assemblage> apply(AssemblageView input) {
						return input.getSubassemblages(subassemblageMatch);
					}
				}));
	}

	@Override
	public Set<Variable<?>> getVariables() {
		return new SetDecorator<>(
				CollapsedCompositionAssemblageView.this
						.<Set<Variable<?>>, Variable<?>> collapseIntoCollection(
								new HashSet<Variable<?>>(), input -> input.getVariables()));
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return new SetDecorator<>(collapseIntoCollection(
				new HashSet<BehaviourComponent>(),
				new Function<AssemblageView, Set<BehaviourComponent>>() {
					@Override
					public Set<BehaviourComponent> apply(AssemblageView input) {
						return input.getBehaviours();
					}
				}));
	}

	@Override
	public Set<StateComponent<?>> getStates() {
		return new SetDecorator<>(collapseIntoCollection(
				new HashSet<StateComponent<?>>(),
				new Function<AssemblageView, Set<StateComponent<?>>>() {
					@Override
					public Set<StateComponent<?>> apply(AssemblageView input) {
						return input.getStates();
					}
				}));
	}

	@Override
	public <D> List<StateInitialiser<D>> getInitialisers(
			final StateComponent<D> state) {
		return new ListDecorator<>(collapseIntoCollection(
				new ArrayList<StateInitialiser<D>>(),
				new Function<AssemblageView, List<StateInitialiser<D>>>() {
					@Override
					public List<StateInitialiser<D>> apply(AssemblageView input) {
						return input.getInitialisers(state);
					}
				}));
	}
}
