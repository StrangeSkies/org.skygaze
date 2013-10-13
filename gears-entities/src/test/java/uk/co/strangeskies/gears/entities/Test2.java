package uk.co.strangeskies.gears.entities;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.assembly.Assembler;
import uk.co.strangeskies.gears.entity.assembly.impl.AssemblerImpl;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcess;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.strangeskies.gears.entity.behaviour.impl.BehaviourComponentBuilderImpl;
import uk.co.strangeskies.gears.entity.management.EntityManager;
import uk.co.strangeskies.gears.entity.management.impl.collections.EntityManagerImpl;
import uk.co.strangeskies.gears.entity.processing.Processor;
import uk.co.strangeskies.gears.entity.processing.impl.ProcessorImpl;
import uk.co.strangeskies.gears.entity.scheduling.schedulers.PeriodicScheduler;
import uk.co.strangeskies.gears.entity.scheduling.terminating.schedulers.LinearScheduler;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.entity.state.StateComponentBuilder;
import uk.co.strangeskies.gears.entity.state.impl.StateComponentBuilderImpl;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.builder.impl.MatrixBuilderImpl;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.gears.mathematics.values.DoubleValue;
import uk.co.strangeskies.gears.utilities.CopyFactory;
import uk.co.strangeskies.gears.utilities.Factory;

public class Test2 {
	private final EntityManager entityManager;
	private final BehaviourComponentBuilder behaviourComponentBuilderFactory;
	private final StateComponentBuilder stateComponentBuilder;
	private final Assembler assembler;
	private final MatrixBuilderImpl matrixBuilder;

	public Test2() {
		entityManager = new EntityManagerImpl();

		behaviourComponentBuilderFactory = new BehaviourComponentBuilderImpl();
		stateComponentBuilder = new StateComponentBuilderImpl();

		matrixBuilder = new MatrixBuilderImpl();

		assembler = new AssemblerImpl();
	}

	public EntityManager entities() {
		return entityManager;
	}

	public Assembler assembler() {
		return assembler;
	}

	public StateComponentBuilder stateBuilder() {
		return stateComponentBuilder;
	}

	public BehaviourComponentBuilder behaviourBuilder() {
		return behaviourComponentBuilderFactory;
	}

	public MatrixBuilderImpl matrices() {
		return matrixBuilder;
	}

	public void run() {
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler());
		scheduler.setPeriodFrequency(5);
		entities().behaviour().setDefaultScheduler(scheduler);

		final StateComponent<Vector2<DoubleValue>> position = stateBuilder()
				.data(new Factory<Vector2<DoubleValue>>() {
					@Override
					public Vector2<DoubleValue> create() {
						return matrices().doubles().vector2();
					}
				}).name("Position").description("Position of entity").create();

		final StateComponent<Vector2<DoubleValue>> velocity = stateBuilder()
				.data(new CopyFactory<>(matrices().doubles().vector2()))
				.name("Velocity").description("Velocity of entity")

				.create();

		final BehaviourComponent movement = behaviourBuilder()
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (Entity entity : context.getEntities()) {
							context.entities().state().getData(entity, position)
									.add(context.entities().state().getData(entity, velocity));
						}
					}
				}).name("Movement").description("Basic movement of entity")
				.readDependencies(velocity).writeDependencies(position).create();
		entities().behaviour().addUniversal(movement);

		final BehaviourComponent reportPosition = behaviourBuilder()
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (Entity entity : context.getEntities()) {
							System.out.println(context.entities().state()
									.getData(entity, position).toString());
						}
					}
				}).name("Movement").description("Basic movement of entity")
				.readDependencies(position).create();
		entities().behaviour().addUniversal(reportPosition);

		reportPosition.addBehaviourDependencies(movement);

		Entity entity1 = entities().create();
		entities().state().attach(entity1, position).setData(1, 2);

		Entity entity2 = entities().create();
		entities().state().attach(entity2, position);

		Processor processor = new ProcessorImpl();
		processor.startProcessing(entities());
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		processor.stopProcessing();
	}

	public static void main(String... args) {
		new Test2().run();
	}
}
