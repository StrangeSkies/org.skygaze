package uk.co.strangeskies.gears.entity.components.rendering.impl;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcess;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.strangeskies.gears.entity.components.rendering.SceneBufferingComponents;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.entity.state.StateComponentBuilder;
import uk.co.strangeskies.gears.rendering.buffering.SceneBuffer;
import uk.co.strangeskies.gears.rendering.buffering.SceneInterpolator;
import uk.co.strangeskies.gears.rendering.buffering.impl.SceneBufferImpl;
import uk.co.strangeskies.gears.rendering.buffering.impl.SceneInterpolatorImpl;
import uk.co.strangeskies.gears.utilities.Factory;

public class SceneBufferingComponentsImpl implements SceneBufferingComponents {
	private final StateComponent<SceneBuffer> sceneBufferState;

	private final StateComponent<SceneInterpolator> interpolatableSceneBufferState;

	private final BehaviourComponent bufferingBehaviour;

	private final BehaviourComponentBuilder behaviourComponentBuilder;

	public SceneBufferingComponentsImpl(
			BehaviourComponentBuilder behaviourComponentBuilder,
			StateComponentBuilder stateComponentBuilder) {
		sceneBufferState = stateComponentBuilder.data(new Factory<SceneBuffer>() {
			@Override
			public SceneBuffer create() {
				return new SceneBufferImpl();
			}
		}).name("Renderable in 2D")
				.description("Data for 2D rendering of entities").create();

		interpolatableSceneBufferState = stateComponentBuilder
				.data(new Factory<SceneInterpolator>() {
					@Override
					public SceneInterpolator create() {
						return new SceneInterpolatorImpl();
					}
				}).name("Buffer for interpolatable rendering data")
				.description("Data for 2D rendering of entities").create();

		bufferingBehaviour = behaviourComponentBuilder
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (SceneBuffer buffer : context.entities().state()
								.getAllData(sceneBufferState)) {
							buffer.push();
						}
						for (SceneBuffer buffer : context.entities().state()
								.getAllData(interpolatableSceneBufferState)) {
							buffer.push();
						}
					}
				})
				.name("Buffering")
				.description("Behaviour for buffering renderable data")
				.indirectWriteDependencies(sceneBufferState,
						interpolatableSceneBufferState).create();

		this.behaviourComponentBuilder = behaviourComponentBuilder;
	}

	@Override
	public StateComponent<SceneBuffer> getSceneBufferState() {
		return sceneBufferState;
	}

	@Override
	public StateComponent<SceneInterpolator> getInterpolatableSceneBufferState() {
		return interpolatableSceneBufferState;
	}

	@Override
	public BehaviourComponent getBufferingBehaviour() {
		return bufferingBehaviour;
	}

	@Override
	public BehaviourComponent getBufferingBehaviour(final SceneBuffer buffer) {
		return behaviourComponentBuilder
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						buffer.push();
					}
				})
				.name("Buffering Single")
				.description(
						"Behaviour for buffering renderable data prepared using a specific SceneBuffer")
				.create();
	}
}
