package uk.co.elionline.gears.entity.scene.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.scene.Assemblage;
import uk.co.elionline.gears.entity.scene.AssemblageVariable;
import uk.co.elionline.gears.entity.scene.AssemblyContext;
import uk.co.elionline.gears.entity.scene.StatePreparator;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.utilities.flowcontrol.FactoryFutureMap;
import uk.co.elionline.gears.utilities.flowcontrol.FutureMap;
import uk.co.elionline.gears.utilities.flowcontrol.FutureMap.Mapping;
import uk.co.elionline.gears.utilities.flowcontrol.StoredFutureMap;

public class AssemblyContextImpl implements AssemblyContext {
	private final Assemblage assemblage;
	private final AssemblyContext parent;

	private EntityManager entities;
	private Entity entity;

	private final FutureMap<StateComponent<?>, Object> stateData;
	private final FutureMap<AssemblageVariable<?>, Object> variableValues;

	private final FutureMap<Assemblage, AssemblyContextImpl> subcontexts;

	public AssemblyContextImpl(Assemblage assemblage) {
		this(assemblage, null);
	}

	protected AssemblyContextImpl(Assemblage assemblage, AssemblyContext parent) {
		this.assemblage = assemblage;
		this.parent = parent;

		stateData = new FutureMap<>(new Mapping<StateComponent<?>, Object>() {
			@Override
			public void prepare(StateComponent<?> key) {
				prepareData(key);
			}

			protected <D> void prepareData(final StateComponent<D> state) {
				for (StatePreparator<D> preparator : AssemblyContextImpl.this.assemblage
						.getPreparators(state)) {
					preparator.prepare(entities.state().getData(entity, state),
							AssemblyContextImpl.this);
				}
			}

			@Override
			public Object get(StateComponent<?> key) {
				return entities.state().getData(entity, key);
			}
		});

		variableValues = new FactoryFutureMap<AssemblageVariable<?>, Object>();

		subcontexts = new StoredFutureMap<Assemblage, AssemblyContextImpl>(
				new StoredFutureMap.Mapping<Assemblage, AssemblyContextImpl>() {
					@Override
					public AssemblyContextImpl prepare(Assemblage key) {
						AssemblyContextImpl assemblyContext = new AssemblyContextImpl(key,
								AssemblyContextImpl.this);
						assemblyContext.assemble(entities);
						return assemblyContext;
					}
				});
	}

	@Override
	public AssemblyContext getSupercontext() {
		return parent;
	}

	@Override
	public Set<AssemblyContext> getSubcontexts(Assemblage subassemblageMatch,
			Assemblage... subassemblageMatchPattern) {
		Set<AssemblyContextImpl> subcontexts = new HashSet<>();
		for (Assemblage subassemblage : assemblage.getSubassemblages()) {
			if (isDerivedFrom(subassemblage, subassemblageMatch)) {
				subcontexts.add(this.subcontexts.get(subassemblage));
			}
		}

		for (Assemblage base : subassemblageMatchPattern) {
			Set<AssemblyContextImpl> nextSubcontexts = new HashSet<>();
			for (AssemblyContextImpl subcontext : subcontexts) {
				for (Assemblage subassemblage : subcontext.assemblage
						.getSubassemblages()) {
					if (isDerivedFrom(subassemblage, subassemblageMatch)) {
						nextSubcontexts.add(subcontext.subcontexts.get(subassemblage));
					}
				}
			}
			subcontexts = nextSubcontexts;
		}

		return Collections.<AssemblyContext> unmodifiableSet(subcontexts);
	}

	protected boolean isDerivedFrom(Assemblage assemblage, Assemblage base) {
		do {
			if (base == assemblage) {
				return true;
			}
			assemblage = assemblage.getBase();
		} while (assemblage != null);

		return false;
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
	public <T> T getValue(AssemblageVariable<T> variable) {
		return (T) variableValues.get(variable);
	}

	protected synchronized void assemble(final EntityManager entities) {
		this.entities = entities;
		entity = entities.create();

		for (final Assemblage subassemblage : assemblage.getSubassemblages()) {
			subcontexts.prepare(subassemblage);
		}

		entities.behaviour().attachAll(entity, assemblage.getBehaviours());
		entities.state().attachAll(entity, assemblage.getStates());

		for (StateComponent<?> state : assemblage.getStates()) {
			stateData.prepare(state);
		}

		for (AssemblageVariable<?> variable : assemblage.getVariables()) {
			variableValues.prepare(variable);
		}
	}
}