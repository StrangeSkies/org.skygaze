package uk.co.strangeskies.gears.entity.components.rendering.impl;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcess;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.strangeskies.gears.entity.components.rendering.RenderingComponents2D;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.entity.state.StateComponentBuilder;
import uk.co.strangeskies.gears.rendering.Renderable;
import uk.co.strangeskies.gears.rendering.Scene;
import uk.co.strangeskies.gears.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;
import uk.co.strangeskies.gears.rendering.rendering2d.Renderer2D;
import uk.co.strangeskies.gears.rendering.rendering2d.SceneFactory2D;
import uk.co.strangeskies.utilities.factory.Factory;

public class RenderingComponents2DImpl implements RenderingComponents2D {
	private final StateComponent<Renderable<Data2D>> renderableState;

	private final BehaviourComponent renderingBehaviour;

	private final BehaviourComponent bufferingBehaviour;

	private final StateComponent<Camera2D> cameraState;

	private final StateComponent<Scene<Data2D>> sceneState;

	public RenderingComponents2DImpl(final Renderer2D renderer,
			final SceneFactory2D sceneFactory,
			BehaviourComponentBuilder behaviourComponentBuilder,
			StateComponentBuilder stateComponentBuilder) {
		renderableState = stateComponentBuilder.configure()
				.data(new Factory<Renderable<Data2D>>() {
					@Override
					public Renderable<Data2D> create() {
						return sceneFactory.createRenderable();
					}
				}).name("Renderable in 2D")
				.description("Data for 2D rendering of entities").create();

		cameraState = stateComponentBuilder.configure()
				.data(new Factory<Camera2D>() {
					@Override
					public Camera2D create() {
						return sceneFactory.createCamera();
					}
				}).name("Camera in 2D").description("Camera for 2D scenes").create();

		sceneState = stateComponentBuilder.configure()
				.data(new Factory<Scene<Data2D>>() {
					@Override
					public Scene<Data2D> create() {
						return sceneFactory.createScene();
					}
				}).name("Scene in 2D")
				.description("Scene for 2D renderables and cameras").create();

		renderingBehaviour = behaviourComponentBuilder
				.configure()
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext processingContext) {
						for (Entity entity : processingContext.getEntities()) {
							Camera2D data = processingContext.entity().state()
									.getData(entity, cameraState);
							renderer.render(data);
						}
					}
				}).name("Rendering").description("Behaviour for rendering entities")
				.readDependencies(cameraState).create();

		bufferingBehaviour = behaviourComponentBuilder.configure()
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext processingContext) {
						renderer.getBuffer().push();
					}
				}).behaviourDependencies(renderingBehaviour).name("Renderer Buffering")
				.description("Behaviour for pushing rendering buffer").create();
	}

	@Override
	public BehaviourComponent getRenderingBehaviour() {
		return renderingBehaviour;
	}

	@Override
	public BehaviourComponent getBufferingBehaviour() {
		return bufferingBehaviour;
	}

	@Override
	public StateComponent<Renderable<Data2D>> getRenderableState() {
		return renderableState;
	}

	@Override
	public StateComponent<Camera2D> getCameraState() {
		return cameraState;
	}

	@Override
	public StateComponent<Scene<Data2D>> getSceneState() {
		return sceneState;
	}
}
