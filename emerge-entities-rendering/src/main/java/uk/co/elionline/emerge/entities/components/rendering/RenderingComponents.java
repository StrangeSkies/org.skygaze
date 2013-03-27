package uk.co.elionline.emerge.entities.components.rendering;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.emerge.entities.LockedEntityManager;
import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;
import uk.co.elionline.emerge.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.emerge.entities.state.StateComponent;
import uk.co.elionline.emerge.rendering.Renderer2D;
import uk.co.elionline.emerge.utilities.DefaultContructorFactory;

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
							LockedEntityManager context) {
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
