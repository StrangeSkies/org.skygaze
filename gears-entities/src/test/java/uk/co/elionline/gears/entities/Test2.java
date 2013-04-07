package uk.co.elionline.gears.entities;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.EntityManager;
import uk.co.elionline.gears.entities.EntityManagerProcessingContext;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.gears.entities.implementations.collections.CollectionsEntityManager;
import uk.co.elionline.gears.entities.processing.PeriodicProcessor;
import uk.co.elionline.gears.entities.processing.scheduling.LinearScheduler;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.utilities.CopyFactory;

public class Test2 {
	EntityManager entityManager;

	public Test2() {
		entityManager = new CollectionsEntityManager();
		entityManager.getStateManager().setLockingEnabled(false);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void run() {
		PeriodicProcessor processor = new PeriodicProcessor(new LinearScheduler());
		processor.setPeriodFrequency(5);
		getEntityManager().getBehaviourManager().setDefaultProcessor(processor);

		final StateComponent<Vector2<DoubleValue>> position = StateComponent
				.<Vector2<DoubleValue>> builder().name("Position")
				.description("Position of entity")
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.create();

		final StateComponent<Vector2<DoubleValue>> velocity = StateComponent
				.<Vector2<DoubleValue>> builder().name("Velocity")
				.description("Velocity of entity")
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.create();

		final BehaviourComponent movement = BehaviourComponent.builder()
				.name("Movement").description("Basic movement of entity")
				.readDependencies(velocity).writeDependencies(position)
				.process(new BehaviourComponentProcess() {
					@Override
					public void process(Set<? extends UUID> entities,
							EntityManagerProcessingContext context) {
						for (UUID entity : entities) {
							context.getStateManager().getData(entity, position)
									.add(context.getStateManager().getData(entity, velocity));
						}
					}
				}).create();
		getEntityManager().getBehaviourManager().addUniversal(movement);

		final BehaviourComponent reportPosition = BehaviourComponent.builder()
				.name("Movement").description("Basic movement of entity")
				.readDependencies(position).process(new BehaviourComponentProcess() {
					@Override
					public void process(Set<? extends UUID> entities,
							EntityManagerProcessingContext context) {
						for (UUID entity : entities) {
							System.out.println(context.getStateManager()
									.getData(entity, position).toString());
						}
					}
				}).create();
		getEntityManager().getBehaviourManager().addUniversal(reportPosition);

		reportPosition.addBehaviourDependencies(movement);

		UUID entity1 = getEntityManager().create();
		getEntityManager().getStateManager().attach(entity1, position);
		getEntityManager().getStateManager().attachAndSet(entity1, velocity,
				new Vector2<>(DoubleValue.factory(), 1, 2));

		UUID entity2 = getEntityManager().create();
		getEntityManager().getStateManager().attach(entity2, position);

		getEntityManager().startProcessing();
		synchronized (this) {
			try {
				wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		getEntityManager().stopProcessing();
	}

	public static void main(String... args) {
		new Test2().run();
	}
}
