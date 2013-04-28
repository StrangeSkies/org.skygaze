package uk.co.elionline.gears.entities.components.rendering;

import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilderFactory;
import uk.co.elionline.gears.entities.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entities.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentBuilderFactory;
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
						for (UUID entity : processingContext.getEntities()) {
							RenderableState2DData data = processingContext.getEntityManager()
									.getStateManager().getData(entity, getRenderableState2D());
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
