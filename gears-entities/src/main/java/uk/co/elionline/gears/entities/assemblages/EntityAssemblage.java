package uk.co.elionline.gears.entities.assemblages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.elionline.gears.entities.Entity;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentData;
import uk.co.elionline.gears.mathematics.functions.UnaryManipulation;
import uk.co.elionline.gears.utilities.Described;
import uk.co.elionline.gears.utilities.Named;

/**
 * An EntityAssemblage provides a set of states and behaviours which combine to
 * describe a potential Entity. An assemblage can be assembled onto an empty
 * Entity, or onto an existing Entity, optionally overriding any existing
 * behaviours or states which conflict with those described in the model.
 * 
 * @author Elias N Vasylenko
 * 
 */
public class EntityAssemblage implements Named, Described {
	private final String name;
	private final String description;

	private final List<EntityAssemblage> entityAssemblages;

	private final Set<BehaviourComponent> behaviourComponents;

	private final Set<StateComponent<?>> stateComponents;
	private final Map<StateComponent<?>, StateComponentData<?>> stateComponentData;
	private final List<UnaryManipulation<? extends StateComponentData<?>>> stateManipulations;

	protected EntityAssemblage(String name, String description) {
		this.name = name;
		this.description = description;

		entityAssemblages = new ArrayList<>();

		behaviourComponents = new HashSet<>();

		stateComponents = new HashSet<>();
		stateComponentData = new HashMap<>();
		stateManipulations = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public boolean addBehaviour(BehaviourComponent behaviourComponent) {
		return behaviourComponents.add(behaviourComponent);
	}

	public boolean addBehaviour(BehaviourComponent behaviourComponent,
			boolean overwriteExistingState) {
		return behaviourComponents.add(behaviourComponent);
	}

	public boolean addBehaviours(
			Collection<? extends BehaviourComponent> behaviourComponents) {
		return this.behaviourComponents.addAll(behaviourComponents);
	}

	public boolean addBehaviours(BehaviourComponent... behaviourComponents) {
		return addBehaviours(Arrays.asList(behaviourComponents));
	}

	public boolean removeBehaviour(BehaviourComponent behaviourComponent) {
		return behaviourComponents.remove(behaviourComponent);
	}

	public boolean removeBehaviours(
			Collection<? extends BehaviourComponent> behaviourComponents) {
		return this.behaviourComponents.removeAll(behaviourComponents);
	}

	public boolean removeBehaviours(BehaviourComponent... behaviourComponents) {
		return removeBehaviours(Arrays.asList(behaviourComponents));
	}

	/*-
		@SuppressWarnings("unchecked")
		public <S extends StateComponentData<S>> Factory<? extends StateComponentData<S>> addState(
				StateComponent<S> stateComponent) {
			return (Factory<? extends StateComponentData<S>>) stateComponentData.put(
					stateComponent, stateComponent);
		}

		public void addStates(Collection<? extends StateComponent<?>> stateComponents) {
			for (StateComponent<?> stateComponent : stateComponents) {
				addState(stateComponent);
			}
		}

		public <S extends StateComponentData<S>, C extends StateComponentData<S>> Factory<? extends StateComponentData<S>> addStateFromData(
				StateComponentData<S> stateComponentData) {
			return addStateFromDataFactory(stateComponentData.getComponent(),
					new CopyFactory<>(stateComponentData.copy()));
		}

		public void addStatesFromData(
				Collection<? extends StateComponentData<?>> stateComponents) {
			for (StateComponentData<?> stateComponent : stateComponents) {
				addStateFromData(stateComponent);
			}
		}

		@SuppressWarnings("unchecked")
		public <S extends StateComponentData<S>> Factory<? extends StateComponentData<S>> addStateFromDataFactory(
				StateComponent<S> stateComponent,
				Factory<? extends StateComponentData<S>> stateComponentDataFactory) {
			return (Factory<? extends StateComponentData<S>>) stateComponentData.put(
					stateComponent, stateComponentDataFactory);
		}

		public <S extends StateComponentData<S>, T extends StateComponentData<S>> Factory<? extends StateComponentData<S>> addStateFromDataFactory(
				Factory<T> stateComponentDataFactory) {
			return addStateFromDataFactory(stateComponentDataFactory.create()
					.getComponent(), stateComponentDataFactory);
		}

		public void addStatesFromDataFactories(
				Collection<? extends Factory<? extends StateComponentData<?>>> stateComponentDataFactories) {
			for (Factory<? extends StateComponentData<?>> stateComponentDataFactory : stateComponentDataFactories) {
				addStateFromDataFactory(stateComponentDataFactory);
			}
		}

		@SuppressWarnings("unchecked")
		public <S extends StateComponentData<S>> Factory<? extends StateComponentData<S>> removeState(
				StateComponent<S> stateComponent) {
			overwritingStateComponents.remove(stateComponent);
			return (Factory<? extends StateComponentData<S>>) stateComponentData
					.remove(stateComponent);
		}

		public void removeStates(
				Collection<? extends StateComponent<?>> stateComponents) {
			for (StateComponent<?> stateComponent : stateComponents) {
				removeState(stateComponent);
			}
		}

		public void removeStates(StateComponent<?>... stateComponents) {
			removeStates(Arrays.asList(stateComponents));
		}
	 */
	public Set<BehaviourComponent> getBehaviourComponents() {
		return behaviourComponents;
	}

	public Set<StateComponent<?>> getStateComponents() {
		return stateComponentData.keySet();
	}

	public Entity assembleOnto(Entity entity) {
		// entity.attachAllBehaviours(behaviourComponents);
		// TODO entity.attachAllStateDataCopies(getStateComponentData());

		return entity;
	}

	public EntityAssemblage mergeInto(EntityAssemblage other) {
		other.stateComponentData.putAll(stateComponentData);
		other.behaviourComponents.addAll(behaviourComponents);

		return this;
	}

	public EntityAssemblage getMergedInto(String name, String description,
			EntityAssemblage other) {
		return mergeInto(other.copy(name, description));
	}

	public EntityAssemblage copy(String name, String description) {
		return new EntityAssemblage(name, description).mergeInto(this);
	}
}
