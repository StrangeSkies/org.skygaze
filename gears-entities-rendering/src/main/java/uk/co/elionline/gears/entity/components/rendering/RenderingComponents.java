package uk.co.elionline.gears.entity.components.rendering;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilderFactory;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilderFactory;
import uk.co.elionline.gears.rendering.Renderer2D;
import uk.co.elionline.gears.utilities.DefaultContructorFactory;

public class RenderingComponents {
	private final StateComponent<RenderableState2DData> renderableState2D;

	private final BehaviourComponent renderingBehaviour2D;

	public RenderingComponents(final Renderer2D renderer,
			BehaviourComponentBuilderFactory behaviourComponentBuilderFactory,
			StateComponentBuilderFactory stateComponentBuilderFactory) {
		renderableState2D = stateComponentBuilderFactory
				.<RenderableState2DData> begin()
				.name("Renderable in 2D")
				.description("Data for 2D rendering of entities")
				.dataFactory(
						new DefaultContructorFactory<>(RenderableState2DData.class))
				.create();

		renderingBehaviour2D = behaviourComponentBuilderFactory.begin()
				.name("Rendering").description("Behaviour for rendering entities")
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext processingContext) {
						for (Entity entity : processingContext.getEntities()) {
							RenderableState2DData data = processingContext.entities()
									.state().getData(entity, getRenderableState2D());
							renderer.setTransformation2(data.getTransformation());
						}
					}
				}).create();
	}

	public BehaviourComponent getRenderingBehaviour2D() {
		return renderingBehaviour2D;
	}

	public StateComponent<RenderableState2DData> getRenderableState2D() {
		return renderableState2D;
	}
}
