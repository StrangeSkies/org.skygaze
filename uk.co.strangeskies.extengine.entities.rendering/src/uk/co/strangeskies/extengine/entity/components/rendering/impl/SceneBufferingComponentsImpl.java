package uk.co.strangeskies.extengine.entity.components.rendering.impl;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.extengine.entity.components.rendering.SceneBufferingComponents;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponentBuilder;
import uk.co.strangeskies.extengine.rendering.buffering.SceneBuffer;
import uk.co.strangeskies.extengine.rendering.buffering.SceneInterpolator;

public class SceneBufferingComponentsImpl implements SceneBufferingComponents {
	private final StateComponent<SceneBuffer> sceneBufferState;

	private final StateComponent<SceneInterpolator> interpolatableSceneBufferState;

	private final BehaviourComponent bufferingBehaviour;

	private final BehaviourComponentBuilder behaviourComponentBuilder;

	public SceneBufferingComponentsImpl(
			BehaviourComponentBuilder behaviourComponentBuilder,
			StateComponentBuilder stateComponentBuilder,
			SceneInterpolator sceneInterpolator) {
		sceneBufferState = stateComponentBuilder.configure()
				.data(() -> (SceneBuffer) sceneInterpolator)
				.name("Renderable in 2D")
				.description("Data for 2D rendering of entities").create();

		interpolatableSceneBufferState = stateComponentBuilder.configure()
				.data(() -> sceneInterpolator)
				.name("Buffer for interpolatable rendering data")
				.description("Data for 2D rendering of entities").create();

		bufferingBehaviour = behaviourComponentBuilder
				.configure()
				.name("Buffering")
				.description("Behaviour for buffering renderable data")
				.process(
						context -> {
							for (SceneBuffer buffer : context.entity().state()
									.getAllData(sceneBufferState)) {
								buffer.push();
							}
							for (SceneBuffer buffer : context.entity().state()
									.getAllData(interpolatableSceneBufferState)) {
								buffer.push();
							}
						})
				.optionalWriteDependencies(sceneBufferState,
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
	public BehaviourComponent getBufferingBehaviour(SceneBuffer buffer) {
		return behaviourComponentBuilder
				.configure()
				.process(context -> buffer.push())
				.name("Buffering Single")
				.description(
						"Behaviour for buffering renderable data prepared using a specific SceneBuffer")
				.create();
	}
}
