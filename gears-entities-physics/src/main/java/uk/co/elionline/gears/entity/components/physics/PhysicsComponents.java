package uk.co.elionline.gears.entity.components.physics;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilderFactory;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilderFactory;
import uk.co.elionline.gears.physics.PhysicsBody;
import uk.co.elionline.gears.physics.PhysicsSpace;
import uk.co.elionline.gears.utilities.CopyFactory;
import uk.co.elionline.gears.utilities.Factory;

public class PhysicsComponents {
	private final StateComponent<PhysicsBody> physicsBodyState;
	private final StateComponent<PhysicsSpace> physicsSpaceState;

	private final BehaviourComponent physicsBehaviour;

	public PhysicsComponents(Factory<PhysicsSpace> physicsSpaceFactory,
			BehaviourComponentBuilderFactory behaviourComponentBuilderFactory,
			StateComponentBuilderFactory stateComponentBuilderFactory) {
		physicsBodyState = stateComponentBuilderFactory.<PhysicsBody> begin()
				.name("Physics Body State")
				.description("The state of a body which can physically interact")
				.dataFactory(new CopyFactory<>(new PhysicsBody())).create();

		physicsSpaceState = stateComponentBuilderFactory.<PhysicsSpace> begin()
				.name("Physics Space State")
				.description("The state of a self contained physics space")
				.writeDependencies(physicsBodyState).dataFactory(physicsSpaceFactory)
				.create();

		physicsBehaviour = behaviourComponentBuilderFactory.begin()
				.name("Physics Behaviour").description("The behaviour of a cursor")
				.writeDependencies(physicsBodyState).process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext processingContext) {
						for (Entity entity : processingContext.getEntities()) {
							processingContext.entities().state()
									.getData(entity, getPhysicsBodyState());
						}
					}
				}).create();
	}

	public final StateComponent<PhysicsBody> getPhysicsBodyState() {
		return physicsBodyState;
	}

	public final StateComponent<PhysicsSpace> getPhysicsSpaceState() {
		return physicsSpaceState;
	}

	public final BehaviourComponent getPhysicsBehaviour() {
		return physicsBehaviour;
	}
}
