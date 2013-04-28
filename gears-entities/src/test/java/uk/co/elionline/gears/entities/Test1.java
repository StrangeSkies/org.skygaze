package uk.co.elionline.gears.entities;

import java.util.UUID;

import uk.co.elionline.gears.entities.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entities.behaviour.BehaviourComponentBuilderFactory;
import uk.co.elionline.gears.entities.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entities.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entities.behaviour.impl.BehaviourComponentBuilderFactoryImpl;
import uk.co.elionline.gears.entities.management.EntityManager;
import uk.co.elionline.gears.entities.management.EntityStateManager;
import uk.co.elionline.gears.entities.management.impl.collections.CollectionsEntityManager;
import uk.co.elionline.gears.entities.processing.Processor;
import uk.co.elionline.gears.entities.processing.impl.ProcessorImpl;
import uk.co.elionline.gears.entities.scheduling.PeriodicScheduler;
import uk.co.elionline.gears.entities.scheduling.terminating.LinearScheduler;
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
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler());
		scheduler.setPeriodFrequency(5);
		getEntityManager().getBehaviourManager().setDefaultScheduler(scheduler);

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
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (UUID entity : context.getEntities()) {
							EntityStateManager stateManager = context.getEntityManager()
									.getStateManager();

							System.out.println(stateManager.getData(entity, position).add(
									stateManager.getData(entity, velocity)));
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

		Processor processingContext = new ProcessorImpl();
		processingContext.startProcessing(getEntityManager());
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		processingContext.stopProcessing();
	}

	public static void main(String... args) {
		new Test1().run();
	}
}
