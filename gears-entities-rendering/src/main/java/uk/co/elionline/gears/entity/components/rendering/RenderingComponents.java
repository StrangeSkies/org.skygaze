package uk.co.elionline.gears.entity.components.rendering;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.rendering.Renderer2D;
import uk.co.elionline.gears.utilities.DefaultContructorFactory;

public class RenderingComponents {
	private final StateComponent<RenderableState2DData> renderableState2D;

	private final BehaviourComponent renderingBehaviour2D;

	public RenderingComponents(final Renderer2D renderer,
			BehaviourComponentBuilder behaviourComponentBuilder,
			StateComponentBuilder stateComponentBuilder) {
		renderableState2D = stateComponentBuilder
				.dataFactory(
						new DefaultContructorFactory<>(RenderableState2DData.class))
				.name("Renderable in 2D")
				.description("Data for 2D rendering of entities").create();

		renderingBehaviour2D = behaviourComponentBuilder
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext processingContext) {
						for (Entity entity : processingContext.getEntities()) {
							RenderableState2DData data = processingContext.entities().state()
									.getData(entity, getRenderableState2D());
							renderer.setTransformation2(data.getTransformation());
						}
					}
				}).name("Rendering").description("Behaviour for rendering entities")
				.create();
	}

	public BehaviourComponent getRenderingBehaviour2D() {
		return renderingBehaviour2D;
	}

	public StateComponent<RenderableState2DData> getRenderableState2D() {
		return renderableState2D;
	}
}
