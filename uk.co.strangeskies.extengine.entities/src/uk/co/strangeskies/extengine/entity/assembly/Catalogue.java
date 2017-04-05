package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Optional;
import java.util.Set;

import uk.co.strangeskies.extengine.Engine;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.modabi.QualifiedName;

public interface Catalogue {
	static final QualifiedName SCHEMA_NAME = new QualifiedName(Catalogue.class.getSimpleName(), Engine.NAMESPACE);

	String getName();

	Package getNamespace();

	default String getQualifiedName() {
		return getNamespace().getName() + "." + getName();
	}

	Set<Catalogue> getDependencies();

	Set<Catalogue> lookUpDependencies(Package namespace);

	default Optional<Catalogue> lookUpDependency(Package namespace, String name) {
		return lookUpDependencies(namespace).stream().filter(d -> d.getName().equals(name)).findAny();
	}

	Set<BehaviourComponent> getBehaviours();

	default Optional<BehaviourComponent> lookUpBehaviour(Package namespace, String name) {
		return lookUpDependencies(namespace).stream().flatMap(d -> d.getBehaviours().stream())
				.filter(b -> b.getName().equals(name)).findAny();
	}

	Set<StateComponent<?, ?>> getStates();

	default Optional<StateComponent<?, ?>> lookUpState(Package namespace, String name) {
		return lookUpDependencies(namespace).stream().flatMap(d -> d.getStates().stream())
				.filter(b -> b.getName().equals(name)).findAny();
	}

	Set<Scheduler> getSchedulers();

	default Optional<Scheduler> lookUpScheduler(Package namespace, String name) {
		return lookUpDependencies(namespace).stream().flatMap(d -> d.getSchedulers().stream())
				.filter(b -> b.getName().equals(name)).findAny();
	}

	Set<Pattern> getPatterns();

	default Optional<Pattern> lookUpPattern(Package namespace, String name) {
		return lookUpDependencies(namespace).stream().flatMap(d -> d.getPatterns().stream())
				.filter(b -> b.getName().equals(name)).findAny();
	}
}
