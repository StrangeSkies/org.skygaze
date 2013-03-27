package uk.co.elionline.emerge.entities.components.physics;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.emerge.entities.LockedEntityManager;
import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;
import uk.co.elionline.emerge.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.emerge.entities.state.StateComponent;
import uk.co.elionline.emerge.physics.PhysicsBody;
import uk.co.elionline.emerge.physics.PhysicsSpace;
import uk.co.elionline.emerge.utilities.CopyFactory;
import uk.co.elionline.emerge.utilities.Factory;

public class PhysicsComponents {
	private final StateComponent<PhysicsBody> physicsBodyState;
	private final StateComponent<PhysicsSpace> physicsSpaceState;

	private final BehaviourComponent physicsBehaviour;

	public PhysicsComponents(Factory<PhysicsSpace> physicsSpaceFactory) {
		physicsBodyState = StateComponent.<PhysicsBody> builder()
				.name("Physics Body State")
				.description("The state of a body which can physically interact")
				.dataFactory(new CopyFactory<>(new PhysicsBody())).create();

		physicsSpaceState = StateComponent.<PhysicsSpace> builder()
				.name("Physics Space State")
				.description("The state of a self contained physics space")
				.writeDependencies(physicsBodyState).dataFactory(physicsSpaceFactory)
				.create();

		physicsBehaviour = BehaviourComponent.builder().name("Physics Behaviour")
				.description("The behaviour of a cursor")
				.writeDependencies(physicsBodyState)
				.process(new BehaviourComponentProcess() {
					@Override
					public void process(Set<? extends UUID> entities,
							LockedEntityManager entityManager) {
						for (UUID entity : entities) {
							PhysicsBody physicsBodyData = entityManager.getStateManager()
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
