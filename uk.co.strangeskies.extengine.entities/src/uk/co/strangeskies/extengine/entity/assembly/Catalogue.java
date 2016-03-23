package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;
import uk.co.strangeskies.extengine.entity.state.StateComponent;

/**
 * A catalogue of {@link BehaviourComponent behaviour components},
 * {@link StateComponent state components}, {@link StateInitialiser state
 * initialisers}, {@link Assemblage assemblages}, and {@link Scheduler
 * schedulers} which can be referenced to build a scene.
 * 
 * @author Elias N Vasylenko
 */
public class Catalogue {
	private String name;

	private Package packageLocation;

	private Set<Catalogue> dependencies;

	private Set<BehaviourComponent> behaviours;

	private Set<StateComponent<?, ?>> states;

	private Set<Scheduler> schedulers;

	private Set<Assemblage> assemblages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Package getPackage() {
		return packageLocation;
	}

	public void setPackage(Package packageLocation) {
		this.packageLocation = packageLocation;
	}

	public Set<Catalogue> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Set<Catalogue> dependencies) {
		this.dependencies = dependencies;
	}

	public Set<BehaviourComponent> getBehaviours() {
		return behaviours;
	}

	public void setBehaviours(Set<BehaviourComponent> behaviours) {
		this.behaviours = behaviours;
	}

	public Set<StateComponent<?, ?>> getStates() {
		return states;
	}

	public void setStates(Set<StateComponent<?, ?>> states) {
		this.states = states;
	}

	public Set<Scheduler> getSchedulers() {
		return schedulers;
	}

	public void setSchedulers(Set<Scheduler> schedulers) {
		this.schedulers = schedulers;
	}

	public Set<Assemblage> getAssemblages() {
		return assemblages;
	}

	public void setAssemblages(Set<Assemblage> assemblages) {
		this.assemblages = assemblages;
	}
}
