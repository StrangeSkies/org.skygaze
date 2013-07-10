package uk.co.elionline.gears.entity.assembly.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.assembly.Assemblage;
import uk.co.elionline.gears.entity.assembly.AssemblyContext;
import uk.co.elionline.gears.entity.assembly.StateInitialiser;
import uk.co.elionline.gears.entity.assembly.Variable;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.FactoryFutureMap;
import uk.co.elionline.gears.utilities.flowcontrol.FutureMap;
import uk.co.elionline.gears.utilities.flowcontrol.FutureMap.Mapping;
import uk.co.elionline.gears.utilities.flowcontrol.StoredFutureMap;

public class AssemblyContextImpl implements AssemblyContext {
	private final Assemblage assemblage;
	private final CollapsedCompositionAssemblageView collapsedView;
	private final AssemblyContext parent;

	private final EntityManager entities;
	private final Entity entity;

	private final FutureMap<StateComponent<?>, Object> stateData;
	private final FutureMap<Variable<?>, Object> variableValues;

	private final FutureMap<Assemblage, AssemblyContextImpl> subcontexts;

	public AssemblyContextImpl(Assemblage assemblage, EntityManager entities) {
		this(assemblage, null, entities);
	}

	protected AssemblyContextImpl(Assemblage assemblage, AssemblyContext parent,
			final EntityManager entities) {
		this.assemblage = assemblage;
		collapsedView = new CollapsedCompositionAssemblageView(assemblage);
		this.parent = parent;
		this.entities = entities;
		entity = entities.create();

		stateData = new FutureMap<>(new Mapping<StateComponent<?>, Object>() {
			@Override
			public void prepare(StateComponent<?> key) {
				prepareData(key);
			}

			protected <D> void prepareData(final StateComponent<D> state) {
				for (StateInitialiser<D> initialiser : collapsedView
						.getInitialisers(state)) {
					initialiser.initialise(entities.state().getData(entity, state),
							AssemblyContextImpl.this);
				}
			}

			@Override
			public Object get(StateComponent<?> key) {
				return entities.state().getData(entity, key);
			}
		});

		variableValues = new FactoryFutureMap<Variable<?>, Object>();

		subcontexts = new StoredFutureMap<Assemblage, AssemblyContextImpl>(
				new StoredFutureMap.Mapping<Assemblage, AssemblyContextImpl>() {
					@Override
					public AssemblyContextImpl prepare(Assemblage key) {
						AssemblyContextImpl assemblyContext = new AssemblyContextImpl(key,
								AssemblyContextImpl.this, entities);
						assemblyContext.startAssembly();
						return assemblyContext;
					}
				});
	}

	@Override
	public AssemblyContext getSupercontext() {
		return parent;
	}

	@Override
	public Set<AssemblyContext> getSubcontexts(
			Assemblage... subassemblageMatchPattern) {
		Set<AssemblyContextImpl> subcontexts = new HashSet<>();

		if (subassemblageMatchPattern.length == 0) {
			for (Assemblage subassemblage : this.subcontexts.getKeys()) {
				subcontexts.add(this.subcontexts.get(subassemblage));
			}
		} else {
			subcontexts.add(this);

			for (Assemblage base : subassemblageMatchPattern) {
				Set<AssemblyContextImpl> previousSubcontexts = subcontexts;
				subcontexts = new HashSet<>();
				for (AssemblyContextImpl subcontext : previousSubcontexts) {
					for (Assemblage subassemblage : subcontext.subcontexts.getKeys()) {
						if (AssemblageImpl.isComposedFrom(subassemblage, base)) {
							subcontexts.add(subcontext.subcontexts.get(subassemblage));
						}
					}
				}
			}
		}

		return Collections.<AssemblyContext> unmodifiableSet(subcontexts);
	}

	@Override
	public AssemblyContext getSubcontext(Assemblage... subassemblageMatchPattern) {
		Set<AssemblyContext> subcontexts = getSubcontexts(subassemblageMatchPattern);

		if (subcontexts.size() != 1) {
			throw new IllegalArgumentException();
		}

		return subcontexts.iterator().next();
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> D getData(StateComponent<D> state) {
		if (!entities.state().has(entity, state)) {
			throw new IllegalArgumentException();
		}
		return (D) stateData.get(state);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(Variable<T> variable) {
		if (!collapsedView.getVariables().contains(variable)) {
			throw new IllegalArgumentException();
		}
		return (T) variableValues.get(variable);
	}

	public synchronized void startAssembly() {
		for (final Assemblage subassemblage : collapsedView.getSubassemblages()) {
			subcontexts.prepare(subassemblage);
		}

		entities.behaviour().attachAll(entity, collapsedView.getBehaviours());

		Set<StateComponent<?>> collapsedStates = collapsedView.getStates();
		entities.state().attachAll(entity, collapsedStates);

		for (StateComponent<?> state : collapsedStates) {
			stateData.prepare(state);
		}

		for (Variable<?> variable : collapsedView.getVariables()) {
			variableValues.prepare(variable);
		}
	}

	public void assemble() {
		startAssembly();
		waitForAssembly();
	}

	private void waitForAssembly() {
		for (Assemblage subassemblage : subcontexts.getKeys()) {
			subcontexts.get(subassemblage).waitForAssembly();
		}
		stateData.waitForAll();
	}

	@Override
	public Assemblage getAssemblage() {
		return assemblage;
	}
}
