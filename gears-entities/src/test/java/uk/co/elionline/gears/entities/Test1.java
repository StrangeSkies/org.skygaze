package uk.co.elionline.gears.entities;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilderFactory;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.gears.entities.behaviour.impl.BehaviourComponentBuilderFactoryImpl;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.management.EntityProcessingContext;
import uk.co.elionline.gears.entities.management.impl.collections.CollectionsEntityManager;
import uk.co.elionline.gears.entities.processing.PeriodicProcessor;
import uk.co.elionline.gears.entities.processing.scheduling.LinearScheduler;
import uk.co.elionline.gears.entities.state.StateComponent;
import uk.co.elionline.gears.entities.state.StateComponentBuilder;
import uk.co.elionline.gears.entities.state.StateComponentBuilderFactory;
import uk.co.elionline.gears.entities.state.impl.StateComponentBuilderFactoryImpl;
import uk.co.elionline.gears.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.utilities.CopyFactory;

public class Test1 {
	private final EntityManager entityManager;
	private final BehaviourComponentBuilderFactory behaviourComponentBuilderFactory;
	private final StateComponentBuilderFactory stateComponentBuilderFactory;

	public Test1() {
		entityManager = new CollectionsEntityManager();
		entityManager.getStateManager().setLockingEnabled(false);

		behaviourComponentBuilderFactory = new BehaviourComponentBuilderFactoryImpl();
		stateComponentBuilderFactory = new StateComponentBuilderFactoryImpl();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public <D> StateComponentBuilder<D> getStateComponentBuilder() {
		return stateComponentBuilderFactory.begin();
	}

	public <D> BehaviourComponentBuilder getBehaviourComponentBuilder() {
		return behaviourComponentBuilderFactory.begin();
	}

	private void run() {
		PeriodicProcessor processor = new PeriodicProcessor(new LinearScheduler());
		processor.setPeriodFrequency(5);
		getEntityManager().getBehaviourManager().setDefaultProcessor(processor);

		final StateComponent<Vector2<DoubleValue>> position = this
				.<Vector2<DoubleValue>> getStateComponentBuilder().name("Position")
				.description("Position of entity")
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.create();

		final StateComponent<Vector2<DoubleValue>> velocity = this
				.<Vector2<DoubleValue>> getStateComponentBuilder().name("Velocity")
				.description("Velocity of entity").readDependencies(position)
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.create();

		final BehaviourComponent movement = getBehaviourComponentBuilder()
				.name("Movement").description("Moves entity by velocity")
				.readDependencies(velocity).writeDependencies(position)
				.process(new BehaviourComponentProcess() {
					@Override
					public void process(Set<? extends UUID> entities,
							EntityProcessingContext context) {
						for (UUID entity : entities) {
							context.getStateManager().getData(entity, position)
									.add(context.getStateManager().getData(entity, velocity));
						}
					}
				}).create();
		getEntityManager().getBehaviourManager().addUniversal(movement);

		UUID entity1 = getEntityManager().create();
		getEntityManager().getStateManager().attachAndSet(entity1, position,
				new Vector2<>(DoubleValue.factory(), 10, 20));

		UUID entity2 = getEntityManager().create();
		getEntityManager().getStateManager().attach(entity2, position);
		getEntityManager().getStateManager().attachAndSet(entity2, velocity,
				new Vector2<>(DoubleValue.factory(), 2, 2));

		getEntityManager().startProcessing();
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getEntityManager().stopProcessing();
	}

	public static void main(String... args) {
		new Test1().run();
	}
}
