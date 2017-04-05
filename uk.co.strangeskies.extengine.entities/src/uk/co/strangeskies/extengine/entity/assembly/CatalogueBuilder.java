package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;
import uk.co.strangeskies.extengine.entity.state.StateComponent;

/**
 * A catalogue of {@link BehaviourComponent behaviour components},
 * {@link StateComponent state components}, {@link StateInitialiser state
 * initialisers}, {@link Pattern patterns}, and {@link Scheduler schedulers}
 * which can be referenced to build a scene.
 * 
 * @author Elias N Vasylenko
 */
public class CatalogueBuilder {
	private String name;

	private Package namespace;

	private Set<Catalogue> dependencies;

	private Set<BehaviourComponent> behaviours;

	private Set<StateComponent<?, ?>> states;

	private Set<Scheduler> schedulers;

	private Set<Pattern> patterns;

	public void setName(String name) {
		this.name = name;
	}

	public void setNamespace(Package namespace) {
		this.namespace = namespace;
	}

	public void setDependencies(Set<Catalogue> dependencies) {
		this.dependencies = dependencies;
	}

	public void setBehaviours(Set<BehaviourComponent> behaviours) {
		this.behaviours = behaviours;
	}

	public void setStates(Set<StateComponent<?, ?>> states) {
		this.states = states;
	}

	public void setSchedulers(Set<Scheduler> schedulers) {
		this.schedulers = schedulers;
	}

	public void setPatterns(Set<Pattern> patterns) {
		this.patterns = patterns;
	}

	public Catalogue create() {
		/*
		 * TODO check all behaviours / states etc. used in patterns are resolvable
		 * via dependencies
		 */
		return null;
	}
}
