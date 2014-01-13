package uk.co.strangeskies.gears.entity.behaviour.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponentConfigurator;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcess;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.strangeskies.gears.entity.state.StateComponent;

public class BehaviourComponentConfiguratorImpl implements
		BehaviourComponentConfigurator {
	private String name;
	private String description;

	private BehaviourProcess process;

	private Collection<? extends BehaviourComponent> behaviourDependencies;
	private Collection<? extends BehaviourComponent> behaviourDependents;

	private Collection<? extends StateComponent<?>> readDependencies;
	private Collection<? extends StateComponent<?>> writeDependencies;

	private Collection<? extends StateComponent<?>> optionalReadDependencies;
	private Collection<? extends StateComponent<?>> optionalWriteDependencies;

	public BehaviourComponentConfiguratorImpl() {
		name = "";
		description = "";

		process = new BehaviourProcess() {
			@Override
			public void process(BehaviourProcessingContext context) {
			}
		};

		behaviourDependencies = Collections.emptySet();
		behaviourDependents = Collections.emptySet();

		readDependencies = Collections.emptySet();
		writeDependencies = Collections.emptySet();

		optionalReadDependencies = Collections.emptySet();
		optionalWriteDependencies = Collections.emptySet();
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
	public BehaviourComponentConfigurator optionalReadDependencies(
			Collection<? extends StateComponent<?>> readDependencies) {
		this.optionalReadDependencies = readDependencies;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator optionalReadDependencies(
			StateComponent<?>... readDependencies) {
		return optionalReadDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public BehaviourComponentConfigurator optionalWriteDependencies(
			Collection<? extends StateComponent<?>> writeDependencies) {
		this.optionalWriteDependencies = writeDependencies;

		return this;
	}

	@Override
	public BehaviourComponentConfigurator optionalWriteDependencies(
			StateComponent<?>... writeDependencies) {
		return optionalWriteDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public BehaviourComponent create() {
		return new BehaviourComponentImpl(name, description, process,
				behaviourDependencies, behaviourDependents, readDependencies,
				writeDependencies, optionalReadDependencies, optionalWriteDependencies);
	}

	@Override
	public BehaviourComponentConfigurator process(BehaviourProcess process) {
		this.process = process;
		return this;
	}
}