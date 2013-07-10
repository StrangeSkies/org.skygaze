package uk.co.elionline.gears.entity.behaviour.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentConfigurator;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.state.StateComponent;

public class BehaviourComponentConfiguratorImpl implements
		BehaviourComponentConfigurator {
	private String name;
	private String description;

	private final BehaviourProcess process;

	private Collection<? extends BehaviourComponent> behaviourDependencies;
	private Collection<? extends BehaviourComponent> behaviourDependents;

	private Collection<? extends StateComponent<?>> readDependencies;
	private Collection<? extends StateComponent<?>> writeDependencies;

	private Collection<? extends StateComponent<?>> indirectReadDependencies;
	private Collection<? extends StateComponent<?>> indirectWriteDependencies;

	public BehaviourComponentConfiguratorImpl(BehaviourProcess process) {
		name = "";
		description = "";

		this.process = process;

		behaviourDependencies = Collections.emptySet();
		behaviourDependents = Collections.emptySet();

		readDependencies = Collections.emptySet();
		writeDependencies = Collections.emptySet();

		indirectReadDependencies = Collections.emptySet();
		indirectWriteDependencies = Collections.emptySet();
	}

	@Override
	public BehaviourComponentConfigurator name(String name) {
		this.name = name;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator description(String description) {
		this.description = description;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator behaviourDependencies(
			Collection<? extends BehaviourComponent> behaviourDependencies) {
		this.behaviourDependencies = behaviourDependencies;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator behaviourDependencies(
			BehaviourComponent... behaviourDependencies) {
		return behaviourDependencies(Arrays.asList(behaviourDependencies));
	}

	@Override
	public BehaviourComponentConfigurator behaviourDependents(
			Collection<? extends BehaviourComponent> behaviourDependents) {
		this.behaviourDependents = behaviourDependents;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator behaviourDependents(
			BehaviourComponent... behaviourDependents) {
		return behaviourDependents(Arrays.asList(behaviourDependents));
	}

	@Override
	public BehaviourComponentConfigurator readDependencies(
			Collection<? extends StateComponent<?>> readDependencies) {
		this.readDependencies = readDependencies;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator readDependencies(
			StateComponent<?>... readDependencies) {
		return readDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public BehaviourComponentConfigurator writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies) {
		this.writeDependencies = writeDependencies;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator writeDependencies(
			StateComponent<?>... writeDependencies) {
		return writeDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public BehaviourComponentConfigurator indirectReadDependencies(
			Collection<? extends StateComponent<?>> readDependencies) {
		this.indirectReadDependencies = readDependencies;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator indirectReadDependencies(
			StateComponent<?>... readDependencies) {
		return indirectReadDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public BehaviourComponentConfigurator indirectWriteDependencies(
			Collection<? extends StateComponent<?>> writeDependencies) {
		this.indirectWriteDependencies = writeDependencies;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator indirectWriteDependencies(
			StateComponent<?>... writeDependencies) {
		return indirectWriteDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public BehaviourComponent create() {
		return new BehaviourComponentImpl(name, description, process,
				behaviourDependencies, behaviourDependents, readDependencies,
				writeDependencies, indirectReadDependencies, indirectWriteDependencies);
	}
}