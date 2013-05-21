package uk.co.elionline.gears.entity.assembly.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.assembly.Assemblage;
import uk.co.elionline.gears.entity.assembly.AssemblyContext;
import uk.co.elionline.gears.entity.assembly.StateInitialiser;
import uk.co.elionline.gears.entity.assembly.Variable;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.FactoryFutureMap;
import uk.co.elionline.gears.utilities.flowcontrol.FutureMap;
import uk.co.elionline.gears.utilities.flowcontrol.FutureMap.Mapping;
import uk.co.elionline.gears.utilities.flowcontrol.StoredFutureMap;

public class AssemblyContextImpl implements AssemblyContext {
	private final Assemblage assemblage;
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
		this.parent = parent;
		this.entities = entities;
		entity = entities.create();

		stateData = new FutureMap<>(new Mapping<StateComponent<?>, Object>() {
			@Override
			public void prepare(StateComponent<?> key) {
				prepareData(key);
			}

			protected <D> void prepareData(final StateComponent<D> state) {
				for (StateInitialiser<D> initialiser : collapseInitialisers(state)) {
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
		subcontexts.add(this);

		for (Assemblage base : subassemblageMatchPattern) {
			Set<AssemblyContextImpl> previousSubcontexts = subcontexts;
			subcontexts = new HashSet<>();
			for (AssemblyContextImpl subcontext : previousSubcontexts) {
				for (Assemblage subassemblage : subcontext.subcontexts.getKeys()) {
					if (AssemblerImpl.isDerivedFrom(subassemblage, base)) {
						subcontexts.add(subcontext.subcontexts.get(subassemblage));
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
		return (D) stateData.get(state);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(Variable<T> variable) {
		return (T) variableValues.get(variable);
	}

	public synchronized void startAssembly() {
		for (final Assemblage subassemblage : assemblage.getSubassemblages()) {
			subcontexts.prepare(subassemblage);
		}

		entities.behaviour().attachAll(entity, collapseBehaviours());

		Set<StateComponent<?>> collapsedStates = collapseStates();
		entities.state().attachAll(entity, collapsedStates);

		for (StateComponent<?> state : collapsedStates) {
			stateData.prepare(state);
		}

		for (Variable<?> variable : collapseVariables()) {
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

	public Set<Variable<?>> collapseVariables() {
		Set<Variable<?>> collapsedVariables = new HashSet<>();

		Assemblage base = assemblage;
		do {
			collapsedVariables.addAll(base.getVariables());
			base = base.getBase();
		} while (base != null);

		return collapsedVariables;
	}

	public Set<BehaviourComponent> collapseBehaviours() {
		Set<BehaviourComponent> collapsedBehaviours = new HashSet<>();

		Assemblage base = assemblage;
		do {
			collapsedBehaviours.addAll(base.getBehaviours());
			base = base.getBase();
		} while (base != null);

		return collapsedBehaviours;
	}

	public Set<StateComponent<?>> collapseStates() {
		Set<StateComponent<?>> collapsedStates = new HashSet<>();

		Assemblage base = assemblage;
		do {
			collapsedStates.addAll(base.getStates());
			base = base.getBase();
		} while (base != null);

		return collapsedStates;
	}

	public <D> List<StateInitialiser<D>> collapseInitialisers(
			StateComponent<D> state) {
		List<StateInitialiser<D>> collapsedInitialisers = new ArrayList<>();

		Assemblage base = assemblage;
		do {
			collapsedInitialisers.addAll(0, base.getInitialisers(state));
			base = base.getBase();
		} while (base != null);

		return collapsedInitialisers;
	}
}
