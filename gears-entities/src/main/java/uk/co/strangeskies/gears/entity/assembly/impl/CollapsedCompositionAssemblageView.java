package uk.co.strangeskies.gears.entity.assembly.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.strangeskies.gears.entity.assembly.Assemblage;
import uk.co.strangeskies.gears.entity.assembly.StateInitialiser;
import uk.co.strangeskies.gears.entity.assembly.Variable;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.mathematics.functions.Function;
import uk.co.strangeskies.gears.utilities.Decorator;
import uk.co.strangeskies.gears.utilities.Property;
import uk.co.strangeskies.gears.utilities.collections.ListDecorator;
import uk.co.strangeskies.gears.utilities.collections.SetDecorator;

public class CollapsedCompositionAssemblageView extends Decorator<Assemblage>
		implements
		/*@ReadOnly*/Assemblage {
	public CollapsedCompositionAssemblageView(Assemblage assemblage) {
		super(assemblage);
	}

	protected <C extends Collection<T>, T> C collapseIntoCollection(C collection,
			Function<? extends Collection<? extends T>, Assemblage> collectionFunction) {
		List<Assemblage> composition = new ArrayList<>();
		composition.add(getComponent());

		while (!composition.isEmpty()) {
			Assemblage assemblage = composition.get(0);
			composition.remove(0);
			collection.addAll(collectionFunction.applyTo(assemblage));

			composition.addAll(0, assemblage.getComposition());
		}

		return collection;
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
				return collapseIntoCollection(new ArrayList<Assemblage>(),
						new Function<List<Assemblage>, Assemblage>() {
							@Override
							public List<Assemblage> applyTo(Assemblage input) {
								return input.getComposition();
							}
						});
			}
		});
	}

	@Override
	public Assemblage getCollapsedCompositionView() {
		return this;
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
						new Function<Set<Assemblage>, Assemblage>() {
							@Override
							public Set<Assemblage> applyTo(Assemblage input) {
								return input.getSubassemblages();
							}
						});
			}
		});
	}

	@Override
	public Assemblage getSubassemblage(Assemblage subassemblageMatch) {
		Set<Assemblage> subassemblages = getSubassemblages(subassemblageMatch);
		if (subassemblages.size() != 1)
			throw new IllegalArgumentException();

		return subassemblages.iterator().next();
	}

	@Override
	public Set<Assemblage> getSubassemblages(final Assemblage subassemblageMatch) {
		return new SetDecorator<>(new Property<Set<Assemblage>, Object>() {
			@Override
			public Set<Assemblage> set(Object to) {
				return null;
			}

			@Override
			public Set<Assemblage> get() {
				return collapseIntoCollection(new HashSet<Assemblage>(),
						new Function<Set<Assemblage>, Assemblage>() {
							@Override
							public Set<Assemblage> applyTo(Assemblage input) {
								return input.getSubassemblages(subassemblageMatch);
							}
						});
			}
		});
	}

	@Override
	public Assemblage overrideSubassemblage(Assemblage subassemblageMatch) {
		// Not callable, read only
		return null;
	}

	@Override
	public Set<Assemblage> overrideSubassemblages(Assemblage subassemblageMatch) {
		// Not callable, read only
		return null;
	}

	@Override
	public void revertOverrides(Assemblage subassemblageMatch) {
		// Not callable, read only
	}

	@Override
	public void revertOverrides() {
		// Not callable, read only
	}

	@Override
	public Set<Variable<?>> getVariables() {
		return new SetDecorator<>(new Property<Set<Variable<?>>, Object>() {
			@Override
			public Set<Variable<?>> set(Object to) {
				return null;
			}

			@Override
			public Set<Variable<?>> get() {
				return collapseIntoCollection(new HashSet<Variable<?>>(),
						new Function<Set<Variable<?>>, Assemblage>() {
							@Override
							public Set<Variable<?>> applyTo(Assemblage input) {
								return input.getVariables();
							}
						});
			}
		});
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return new SetDecorator<>(new Property<Set<BehaviourComponent>, Object>() {
			@Override
			public Set<BehaviourComponent> set(Object to) {
				return null;
			}

			@Override
			public Set<BehaviourComponent> get() {
				return collapseIntoCollection(new HashSet<BehaviourComponent>(),
						new Function<Set<BehaviourComponent>, Assemblage>() {
							@Override
							public Set<BehaviourComponent> applyTo(Assemblage input) {
								return input.getBehaviours();
							}
						});
			}
		});
	}

	@Override
	public Set<StateComponent<?>> getStates() {
		return new SetDecorator<>(new Property<Set<StateComponent<?>>, Object>() {
			@Override
			public Set<StateComponent<?>> set(Object to) {
				return null;
			}

			@Override
			public Set<StateComponent<?>> get() {
				return collapseIntoCollection(new HashSet<StateComponent<?>>(),
						new Function<Set<StateComponent<?>>, Assemblage>() {
							@Override
							public Set<StateComponent<?>> applyTo(Assemblage input) {
								return input.getStates();
							}
						});
			}
		});
	}

	@Override
	public <D> List<StateInitialiser<D>> getInitialisers(
			final StateComponent<D> state) {
		return new ListDecorator<>(
				new Property<List<StateInitialiser<D>>, Object>() {
					@Override
					public List<StateInitialiser<D>> set(Object to) {
						return null;
					}

					@Override
					public List<StateInitialiser<D>> get() {
						return collapseIntoCollection(new ArrayList<StateInitialiser<D>>(),
								new Function<List<StateInitialiser<D>>, Assemblage>() {
									@Override
									public List<StateInitialiser<D>> applyTo(Assemblage input) {
										return input.getInitialisers(state);
									}
								});
					}
				});
	}

}
