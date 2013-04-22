package uk.co.elionline.gears.entities.components.rendering;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.management.EntityManagerProcessingContext;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.rendering.Renderer2D;
import uk.co.elionline.gears.utilities.DefaultContructorFactory;

public class RenderingComponents {
	private final StateComponent<RenderableState2DData> renderableState2D;

	private final BehaviourComponent renderingBehaviour2D;

	public RenderingComponents(final Renderer2D renderer) {
		renderableState2D = StateComponent
				.<RenderableState2DData> builder()
				.name("Renderable in 2D")
				.description("Data for 2D rendering of entities")
				.dataFactory(
						new DefaultContructorFactory<>(RenderableState2DData.class))
				.create();

		renderingBehaviour2D = BehaviourComponent.builder().name("Rendering")
				.description("Behaviour for rendering entities")
				.process(new BehaviourComponentProcess() {
					@Override
					public void process(Set<? extends UUID> entities,
							EntityManagerProcessingContext context) {
						for (UUID entity : entities) {
							RenderableState2DData data = context.getStateManager().getData(
									entity, getRenderableState2D());
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
