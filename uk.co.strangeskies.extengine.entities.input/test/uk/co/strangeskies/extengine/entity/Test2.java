package uk.co.strangeskies.extengine.entity;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentConfigurator;
import uk.co.strangeskies.extengine.entity.behaviour.impl.BehaviourComponentBuilderImpl;
import uk.co.strangeskies.extengine.entity.management.EntityBehaviourManager;
import uk.co.strangeskies.extengine.entity.management.EntityManager;
import uk.co.strangeskies.extengine.entity.management.EntityStateManager;
import uk.co.strangeskies.extengine.entity.management.impl.collections.EntityManagerImpl;
import uk.co.strangeskies.extengine.entity.processing.Processor;
import uk.co.strangeskies.extengine.entity.processing.impl.ProcessorImpl;
import uk.co.strangeskies.extengine.entity.scheduling.schedulers.PeriodicScheduler;
import uk.co.strangeskies.extengine.entity.scheduling.terminating.schedulers.LinearScheduler;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponentBuilder;
import uk.co.strangeskies.extengine.entity.state.StateComponentConfigurator;
import uk.co.strangeskies.extengine.entity.state.impl.StateComponentBuilderImpl;
import uk.co.strangeskies.mathematics.geometry.matrix.building.MatrixBuilder;
import uk.co.strangeskies.mathematics.geometry.matrix.building.impl.MatrixBuilderImpl;
import uk.co.strangeskies.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.mathematics.graph.impl.GraphBuilderImpl;
import uk.co.strangeskies.mathematics.values.DoubleValue;

public class Test2 {
	private final EntityManager entityManager;
	private final BehaviourComponentBuilder behaviourComponentBuilderFactory;
	private final StateComponentBuilder stateComponentBuilder;
	private final MatrixBuilder matrixBuilder;

	public Test2() {
		entityManager = new EntityManagerImpl();

		behaviourComponentBuilderFactory = new BehaviourComponentBuilderImpl();
		stateComponentBuilder = new StateComponentBuilderImpl();

		matrixBuilder = new MatrixBuilderImpl();
	}

	private EntityManager entity() {
		return entityManager;
	}

	private EntityBehaviourManager behaviour() {
		return entityManager.behaviour();
	}

	private EntityStateManager state() {
		return entityManager.state();
	}

	private StateComponentConfigurator<Object, Object> stateBuilder() {
		return stateComponentBuilder.configure();
	}

	private BehaviourComponentConfigurator behaviourBuilder() {
		return behaviourComponentBuilderFactory.configure();
	}

	private MatrixBuilder matrices() {
		return matrixBuilder;
	}

	public void run() {
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler(
				new GraphBuilderImpl()));
		scheduler.setPeriodFrequency(5);
		behaviour().setDefaultScheduler(scheduler);

		StateComponent<Vector2<DoubleValue>, Vector2<DoubleValue>> position = stateBuilder()
				.data(() -> matrices().doubles().vector2()).name("Position")
				.description("Position of entity").create();

		StateComponent<Vector2<DoubleValue>, Vector2<DoubleValue>> velocity = stateBuilder()
				.data(() -> matrices().doubles().vector2()).name("Velocity")
				.description("Velocity of entity").create();

		BehaviourComponent movement = behaviourBuilder()
				.process(
						context -> {
							for (Entity entity : context.participatingEntities())
								context.state().getData(entity, position)
										.add(context.state().getReadOnlyData(entity, velocity));
						}).name("Movement").description("Basic movement of entity")
				.readDependencies(velocity).writeDependencies(position).create();
		behaviour().addUniversal(movement);

		BehaviourComponent reportPosition = behaviourBuilder()
				.process(
						context -> {
							for (Entity entity : context.participatingEntities())
								System.out.println(context.state()
										.getReadOnlyData(entity, position).toString());
							System.out.println();
						}).name("Position Report").description("Report position of entity")
				.readDependencies(position).behaviourDependencies(movement).create();
		behaviour().addUniversal(reportPosition);

		Entity entity1 = entity().create();
		state().attach(entity1, position).setData(1, 2);
		state().attach(entity1, velocity).setData(1, 1);

		Entity entity2 = entity().create();
		state().attach(entity2, position);
		state().attach(entity2, velocity).setData(10, 20);

		Processor processor = new ProcessorImpl();
		processor.startProcessing(entity());
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
