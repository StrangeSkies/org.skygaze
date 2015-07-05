package uk.co.strangeskies.extengine.entity;

import java.util.Random;

import org.testng.annotations.Test;

import uk.co.strangeskies.extengine.entity.assembly.Assemblage;
import uk.co.strangeskies.extengine.entity.assembly.Assembler;
import uk.co.strangeskies.extengine.entity.assembly.AssemblyContext;
import uk.co.strangeskies.extengine.entity.assembly.StateInitialiser;
import uk.co.strangeskies.extengine.entity.assembly.Variable;
import uk.co.strangeskies.extengine.entity.assembly.impl.AssemblerImpl;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentConfigurator;
import uk.co.strangeskies.extengine.entity.behaviour.impl.BehaviourComponentBuilderImpl;
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
import uk.co.strangeskies.mathematics.expression.IdentityExpression;
import uk.co.strangeskies.mathematics.geometry.matrix.building.MatrixBuilder;
import uk.co.strangeskies.mathematics.geometry.matrix.building.impl.MatrixBuilderImpl;
import uk.co.strangeskies.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.mathematics.graph.impl.GraphBuilderImpl;
import uk.co.strangeskies.mathematics.values.DoubleValue;
import uk.co.strangeskies.mathematics.values.IntValue;
import uk.co.strangeskies.utilities.IdentityProperty;
import uk.co.strangeskies.utilities.Property;

public class Test1 {
	private final EntityManager entityManager;
	private final BehaviourComponentBuilder behaviourComponentBuilderFactory;
	private final StateComponentBuilder stateComponentBuilder;
	private final Assembler assembler;
	private final MatrixBuilder matrixBuilder;

	public Test1() {
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

	public StateComponentConfigurator<Object, Object> stateBuilder() {
		return stateComponentBuilder.configure();
	}

	public BehaviourComponentConfigurator behaviourBuilder() {
		return behaviourComponentBuilderFactory.configure();
	}

	public MatrixBuilder matrices() {
		return matrixBuilder;
	}

	@Test
	private void run() {
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler(
				new GraphBuilderImpl()));
		scheduler.setPeriodLengthMilliseconds(250);
		entities().behaviour().setDefaultScheduler(scheduler);

		/*
		 * State Components
		 */
		StateComponent<Vector2<DoubleValue>, Vector2<DoubleValue>> position = stateBuilder()
				.name("Position").description("Position of entity")
				.data(matrices().doubles()::vector2).create();

		StateComponent<Vector2<DoubleValue>, Vector2<DoubleValue>> velocity = stateBuilder()
				.name("Velocity").description("Velocity of entity")
				.data(matrices().doubles()::vector2).readDependencies(position)
				.create();

		StateComponent<Property<String, String>, Property<String, String>> parrot = stateBuilder()
				.name("Parrot")
				.description("A parrot which knows a word")
				.<Property<String, String>, Property<String, String>> data(
						() -> new IdentityExpression<>("Squawk")).create();

		/*
		 * Behaviour Components
		 */
		BehaviourComponent movement = behaviourBuilder()
				.name("Movement")
				.description("Moves entity by velocity")
				.process(
						context -> {
							for (Entity entity : context.participatingEntities()) {
								System.out.println(context.state().getData(entity, position)
										.add(context.state().getReadOnlyData(entity, velocity)));
							}
						}).readDependencies(velocity).writeDependencies(position).create();
		entities().behaviour().addUniversal(movement);

		BehaviourComponent parrotting = behaviourBuilder()
				.name("Parrotting")
				.description("Parrot makes a noise")
				.process(
						context -> {
							for (Entity entity : context.participatingEntities()) {
								System.out.println(context.state()
										.getReadOnlyData(entity, parrot).get());
							}
						}).readDependencies(parrot).behaviourDependencies(movement)
				.create();
		entities().behaviour().addUniversal(parrotting);

		/*
		 * Assemblages
		 */
		Assemblage assemblage1 = assembler().create();
		Variable<IntValue> numberVariable = () -> new IntValue(
				new Random().nextInt());
		assemblage1.getVariables().add(numberVariable);
		assemblage1.getStates().add(position);
		assemblage1.getStates().add(parrot);
		assemblage1.getStates().add(velocity);
		assemblage1.getInitialisers(velocity).add(
				(data, context) -> data.setData(0.5, 0.5));
		assemblage1.getInitialisers(position).add(
				(data, context) -> data.setData(5, 5));

		Assemblage assemblage2 = assembler().create();
		assemblage2.getComposition().add(assemblage1);
		assemblage2.getStates().add(velocity);
		assemblage2.getInitialisers(position).add(
				(data, context) -> data.setData(12, 12));
		assemblage2.getInitialisers(velocity).add(
				(data, context) -> data.add(context.getSupercontext().getData(position)
						.getSubtracted(matrices().doubles().vector2().setData(2, 2))));
		assemblage2.getSubassemblages().add(assemblage1);

		assemblage1.getInitialisers(parrot).add(
				(data, context) -> {
					try {
						data.set("Child position: "
								+ context.getSubcontext(assemblage1).getData(position)
								+ " num " + context.getValue(numberVariable));
					} catch (IllegalArgumentException e) {
						data.set("Parent num: "
								+ context.getSupercontext().getValue(numberVariable) + " num: "
								+ context.getValue(numberVariable));
					}
				});

		Assemblage assemblage3 = assembler().create();
		assemblage3.getVariables().add(numberVariable);
		assemblage3.getStates().add(position);
		assemblage3.getInitialisers(position).add(
				(data, context) -> data.set(context
						.getSubcontext(assemblage2, assemblage1).getData(position)
						.getMultiplied(3)));
		assemblage3.getSubassemblages().add(assemblage2);

		assembler().assemble(assemblage3, entities());

		/*
		 * Process
		 */
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
		new Test1().run();
	}
}
