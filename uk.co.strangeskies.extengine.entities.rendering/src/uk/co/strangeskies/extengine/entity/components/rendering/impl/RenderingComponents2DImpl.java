package uk.co.strangeskies.extengine.entity.components.rendering.impl;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.extengine.entity.components.rendering.RenderingComponents2D;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponentBuilder;
import uk.co.strangeskies.extengine.rendering.Renderable;
import uk.co.strangeskies.extengine.rendering.Scene;
import uk.co.strangeskies.extengine.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.extengine.rendering.rendering2d.Data2D;
import uk.co.strangeskies.extengine.rendering.rendering2d.Renderer2D;
import uk.co.strangeskies.extengine.rendering.rendering2d.SceneFactory2D;

public class RenderingComponents2DImpl implements RenderingComponents2D {
	private final StateComponent<Renderable<Data2D>, Object> renderableState;

	private final BehaviourComponent renderingBehaviour;

	private final BehaviourComponent bufferingBehaviour;

	private final StateComponent<Camera2D, Object> cameraState;

	private final StateComponent<Scene<Data2D>, Object> sceneState;

	public RenderingComponents2DImpl(final Renderer2D renderer,
			final SceneFactory2D sceneFactory,
			BehaviourComponentBuilder behaviourComponentBuilder,
			StateComponentBuilder stateComponentBuilder) {
		renderableState = stateComponentBuilder.configure()
				.<Renderable<Data2D>, Object> data(sceneFactory::createRenderable)
				.name("Renderable in 2D")
				.description("Data for 2D rendering of entities").create();

		cameraState = stateComponentBuilder.configure()
				.<Camera2D, Object> data(sceneFactory::createCamera)
				.name("Camera in 2D").description("Camera for 2D scenes").create();

		sceneState = stateComponentBuilder.configure()
				.<Scene<Data2D>, Object> data(sceneFactory::createScene)
				.name("Scene in 2D")
				.description("Scene for 2D renderables and cameras").create();

		renderingBehaviour = behaviourComponentBuilder.configure().process(p -> {
			for (Entity entity : p.participatingEntities()) {
				Camera2D data = p.entity().state().getData(entity, cameraState);
				renderer.render(data);
			}
		}).name("Rendering").description("Behaviour for rendering entities")
				.readDependencies(cameraState).create();

		bufferingBehaviour = behaviourComponentBuilder.configure()
				.process(p -> renderer.getBuffer().push())
				.behaviourDependencies(renderingBehaviour).name("Renderer Buffering")
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
	public StateComponent<Renderable<Data2D>, Object> getRenderableState() {
		return renderableState;
	}

	@Override
	public StateComponent<Camera2D, Object> getCameraState() {
		return cameraState;
	}

	@Override
	public StateComponent<Scene<Data2D>, Object> getSceneState() {
		return sceneState;
	}
}
