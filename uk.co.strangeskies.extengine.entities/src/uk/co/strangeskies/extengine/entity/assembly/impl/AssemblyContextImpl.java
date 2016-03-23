package uk.co.strangeskies.extengine.entity.assembly.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.assembly.AssemblageView;
import uk.co.strangeskies.extengine.entity.assembly.AssemblyContext;
import uk.co.strangeskies.extengine.entity.assembly.StateInitialiser;
import uk.co.strangeskies.extengine.entity.assembly.Variable;
import uk.co.strangeskies.extengine.entity.management.EntityManager;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.Isomorphism;
import uk.co.strangeskies.utilities.collection.computingmap.FutureMap;

public class AssemblyContextImpl implements AssemblyContext {
	private final AssemblageView assemblage;
	private final CollapsedCompositionAssemblageView collapsedView;
	private final AssemblyContext parent;

	private final EntityManager entities;
	private final Entity entity;

	private final FutureMap<StateComponent<?, ?>, Object> stateData;
	private final FutureMap<Variable<?>, Object> variableValues;

	private final FutureMap<AssemblageView, AssemblyContextImpl> subcontexts;

	public AssemblyContextImpl(AssemblageView assemblage, EntityManager entities) {
		this(assemblage, null, entities);
	}

	protected AssemblyContextImpl(AssemblageView assemblage, AssemblyContext parent, final EntityManager entities) {
		this.assemblage = assemblage;
		collapsedView = new CollapsedCompositionAssemblageView(assemblage);
		this.parent = parent;
		this.entities = entities;
		entity = entities.create();

		stateData = new FutureMap<>(state -> {
			prepareStateData(state);
			return entities.state().getData(entity, state);
		});

		Isomorphism isomorphism = new Isomorphism();
		variableValues = new FutureMap<>(v -> v.getValue(isomorphism));

		subcontexts = new FutureMap<>(k -> {
			AssemblyContextImpl assemblyContext = new AssemblyContextImpl(k, AssemblyContextImpl.this, entities);
			assemblyContext.startAssembly();
			return assemblyContext;
		});
	}

	protected <D> void prepareStateData(StateComponent<D, ?> state) {
		for (StateInitialiser<D> initialiser : collapsedView.getInitialisers(state)) {
			initialiser.initialise(entities.state().getData(entity, state), AssemblyContextImpl.this);
		}
	}

	@Override
	public AssemblyContext getSupercontext() {
		return parent;
	}

	@Override
	public Set<AssemblyContext> getSubcontexts(AssemblageView... subassemblageMatchPattern) {
		Set<AssemblyContextImpl> subcontexts = new HashSet<>();

		if (subassemblageMatchPattern.length == 0) {
			for (AssemblageView subassemblage : this.subcontexts.getKeys()) {
				subcontexts.add(this.subcontexts.putGet(subassemblage));
			}
		} else {
			subcontexts.add(this);

			for (AssemblageView base : subassemblageMatchPattern) {
				Set<AssemblyContextImpl> previousSubcontexts = subcontexts;
				subcontexts = new HashSet<>();
				for (AssemblyContextImpl subcontext : previousSubcontexts) {
					for (AssemblageView subassemblage : subcontext.subcontexts.getKeys()) {
						if (AssemblageImpl.isComposedFrom(subassemblage, base)) {
							subcontexts.add(subcontext.subcontexts.putGet(subassemblage));
						}
					}
				}
			}
		}

		return Collections.<AssemblyContext>unmodifiableSet(subcontexts);
	}

	@Override
	public AssemblyContext getSubcontext(AssemblageView... subassemblageMatchPattern) {
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
	public <D> D getData(StateComponent<D, ?> state) {
		if (!entities.state().has(entity, state)) {
			throw new IllegalArgumentException();
		}
		return (D) stateData.putGet(state);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(Variable<T> variable) {
		if (!collapsedView.getVariables().contains(variable)) {
			throw new IllegalArgumentException();
		}
		return (T) variableValues.putGet(variable);
	}

	public synchronized void startAssembly() {
		for (final AssemblageView subassemblage : collapsedView.getSubassemblages()) {
			subcontexts.put(subassemblage);
		}

		entities.behaviour().attachAll(entity, collapsedView.getBehaviours());

		Set<StateComponent<?, ?>> collapsedStates = collapsedView.getStates();
		entities.state().attachAll(entity, collapsedStates);

		for (StateComponent<?, ?> state : collapsedStates) {
			stateData.put(state);
		}

		for (Variable<?> variable : collapsedView.getVariables()) {
			variableValues.put(variable);
		}
	}

	public Entity assemble() {
		startAssembly();
		waitForAssembly();

		return entity;
	}

	private void waitForAssembly() {
		for (AssemblageView subassemblage : subcontexts.getKeys()) {
			subcontexts.putGet(subassemblage).waitForAssembly();
		}
		stateData.waitForAll();
	}

	@Override
	public AssemblageView getAssemblage() {
		return assemblage;
	}
}
