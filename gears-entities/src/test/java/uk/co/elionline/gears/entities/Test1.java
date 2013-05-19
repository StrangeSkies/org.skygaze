package uk.co.elionline.gears.entities;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilderFactory;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entity.behaviour.impl.BehaviourComponentBuilderFactoryImpl;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.management.EntityStateManager;
import uk.co.elionline.gears.entity.management.impl.collections.CollectionsEntityManager;
import uk.co.elionline.gears.entity.processing.Processor;
import uk.co.elionline.gears.entity.processing.impl.ProcessorImpl;
import uk.co.elionline.gears.entity.scheduling.schedulers.PeriodicScheduler;
import uk.co.elionline.gears.entity.scheduling.terminating.schedulers.LinearScheduler;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.entity.state.StateComponentBuilderFactory;
import uk.co.elionline.gears.entity.state.impl.StateComponentBuilderFactoryImpl;
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

	public EntityManager entities() {
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
		entities().behaviour().setDefaultScheduler(scheduler);

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
						for (Entity entity : context.getEntities()) {
							EntityStateManager stateManager = context.entities()
									.state();

							System.out.println(stateManager.getData(entity, position).add(
									stateManager.getData(entity, velocity)));
						}
					}
				}).create();
		entities().behaviour().addUniversal(movement);

		Entity entity1 = entities().create();
		entities().state().attach(entity1, position)
				.set(new Vector2<>(DoubleValue.factory(), 10, 20));

		Entity entity2 = entities().create();
		entities().state().attach(entity2, position);
		entities().state().attach(entity2, velocity)
				.set(new Vector2<>(DoubleValue.factory(), 2, 2));

		Processor processingContext = new ProcessorImpl();
		processingContext.startProcessing(entities());
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
