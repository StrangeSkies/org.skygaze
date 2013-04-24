package uk.co.elionline.gears.entities.behaviour.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.gears.entities.state.StateComponent;

public class BehaviourComponentBuilderImpl implements
		BehaviourComponentBuilder {
	private String name;
	private String description;

	private BehaviourComponentProcess process;

	private Collection<? extends BehaviourComponent> behaviourDependencies;
	private Collection<? extends BehaviourComponent> behaviourDependents;

	private Collection<? extends StateComponent<?>> readDependencies;
	private Collection<? extends StateComponent<?>> writeDependencies;

	private Collection<? extends StateComponent<?>> indirectReadDependencies;
	private Collection<? extends StateComponent<?>> indirectWriteDependencies;

	public BehaviourComponentBuilderImpl() {
		name = "";
		description = "";

		this.behaviourDependencies = Collections.emptySet();
		this.behaviourDependents = Collections.emptySet();

		this.readDependencies = Collections.emptySet();
		this.writeDependencies = Collections.emptySet();

		this.indirectReadDependencies = Collections.emptySet();
		this.indirectWriteDependencies = Collections.emptySet();
	}

	@Override
	public BehaviourComponentBuilder process(BehaviourComponentProcess process) {
		this.process = process;

		return this;
	}

	@Override
	public BehaviourComponentBuilder name(String name) {
		this.name = name;

		return this;
	}

	@Override
	public BehaviourComponentBuilder description(String description) {
		this.description = description;

		return this;
	}

	@Override
	public BehaviourComponentBuilder behaviourDependencies(
			Collection<? extends BehaviourComponent> behaviourDependencies) {
		this.behaviourDependencies = behaviourDependencies;

		return this;
	}

	@Override
	public BehaviourComponentBuilder behaviourDependencies(
			BehaviourComponent... behaviourDependencies) {
		return behaviourDependencies(Arrays.asList(behaviourDependencies));
	}

	@Override
	public BehaviourComponentBuilder behaviourDependents(
			Collection<? extends BehaviourComponent> behaviourDependents) {
		this.behaviourDependents = behaviourDependents;

		return this;
	}

	@Override
	public BehaviourComponentBuilder behaviourDependents(
			BehaviourComponent... behaviourDependents) {
		return behaviourDependents(Arrays.asList(behaviourDependents));
	}

	@Override
	public BehaviourComponentBuilder readDependencies(
			Collection<? extends StateComponent<?>> readDependencies) {
		this.readDependencies = readDependencies;

		return this;
	}

	@Override
	public BehaviourComponentBuilder readDependencies(
			StateComponent<?>... readDependencies) {
		return readDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public BehaviourComponentBuilder writeDependencies(
			Collection<? extends StateComponent<?>> writeDependencies) {
		this.writeDependencies = writeDependencies;

		return this;
	}

	@Override
	public BehaviourComponentBuilder writeDependencies(
			StateComponent<?>... writeDependencies) {
		return writeDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public BehaviourComponentBuilder indirectReadDependencies(
			Collection<? extends StateComponent<?>> readDependencies) {
		this.indirectReadDependencies = readDependencies;

		return this;
	}

	@Override
	public BehaviourComponentBuilder indirectReadDependencies(
			StateComponent<?>... readDependencies) {
		return indirectReadDependencies(Arrays.asList(readDependencies));
	}

	@Override
	public BehaviourComponentBuilder indirectWriteDependencies(
			Collection<? extends StateComponent<?>> writeDependencies) {
		this.indirectWriteDependencies = writeDependencies;

		return this;
	}

	@Override
	public BehaviourComponentBuilder indirectWriteDependencies(
			StateComponent<?>... writeDependencies) {
		return indirectWriteDependencies(Arrays.asList(writeDependencies));
	}

	@Override
	public BehaviourComponent create() {
		return new BehaviourComponentImpl(name, description, process,
				behaviourDependencies, behaviourDependents, readDependencies,
				writeDependencies, indirectReadDependencies,
				indirectWriteDependencies);
	}
}