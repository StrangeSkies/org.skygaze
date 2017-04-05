package uk.co.strangeskies.extengine.entity.test;

import java.util.Random;

import org.junit.Test;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.Processor;
import uk.co.strangeskies.extengine.entity.assembly.Pattern;
import uk.co.strangeskies.extengine.entity.assembly.Variable;
import uk.co.strangeskies.extengine.entity.assembly.impl.PatternImpl;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentConfigurator;
import uk.co.strangeskies.extengine.entity.behaviour.impl.BehaviourComponentBuilderImpl;
import uk.co.strangeskies.extengine.entity.impl.ProcessorImpl;
import uk.co.strangeskies.extengine.entity.impl.collections.EntityComponentSystemImpl;
import uk.co.strangeskies.extengine.entity.scheduling.schedulers.LinearScheduler;
import uk.co.strangeskies.extengine.entity.scheduling.schedulers.PeriodicScheduler;
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
import uk.co.strangeskies.utilities.Property;

public class Test1 {
	private final EntityComponentSystem entityManager;
	private final BehaviourComponentBuilder behaviourComponentBuilderFactory;
	private final StateComponentBuilder stateComponentBuilder;
	private final MatrixBuilder matrixBuilder;

	public Test1() {
		entityManager = new EntityComponentSystemImpl();

		behaviourComponentBuilderFactory = new BehaviourComponentBuilderImpl();
		stateComponentBuilder = new StateComponentBuilderImpl();

		matrixBuilder = new MatrixBuilderImpl();
	}

	public EntityComponentSystem entities() {
		return entityManager;
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
	public void run() {
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler(new GraphBuilderImpl()));
		scheduler.setPeriodLengthMilliseconds(250);
		entities().behaviour().setDefaultScheduler(scheduler);

		/*
		 * State Components
		 */
		StateComponent<Vector2<DoubleValue>, Vector2<DoubleValue>> position = stateBuilder().name("Position")
				.description("Position of entity").data(matrices().doubles()::vector2).create();

		StateComponent<Vector2<DoubleValue>, Vector2<DoubleValue>> velocity = stateBuilder().name("Velocity")
				.description("Velocity of entity").data(matrices().doubles()::vector2).readDependencies(position).create();

		StateComponent<Property<String, String>, Property<String, String>> parrot = stateBuilder().name("Parrot")
				.description("A parrot which knows a word")
				.<Property<String, String>, Property<String, String>>data(() -> new IdentityExpression<>("Squawk")).create();

		/*
		 * Behaviour Components
		 */
		BehaviourComponent movement = behaviourBuilder().name("Movement").description("Moves entity by velocity")
				.process(context -> {
					for (Entity entity : context.participatingEntities()) {
						System.out.println(
								context.state().getData(entity, position).add(context.state().getReadOnlyData(entity, velocity)));
					}
				}).readDependencies(velocity).writeDependencies(position).create();
		entities().behaviour().addUniversal(movement);

		BehaviourComponent parrotting = behaviourBuilder().name("Parrotting").description("Parrot makes a noise")
				.process(context -> {
					for (Entity entity : context.participatingEntities()) {
						System.out.println(context.state().getReadOnlyData(entity, parrot).get());
					}
				}).readDependencies(parrot).behaviourDependencies(movement).create();
		entities().behaviour().addUniversal(parrotting);

		/*
		 * Patterns
		 */
		Pattern pattern1 = new PatternImpl("pattern one");
		Variable<IntValue> numberVariable = new Variable<>("number", new IntValue(new Random().nextInt()));
		pattern1.getVariables().add(numberVariable);
		pattern1.getStates().add(position);
		pattern1.getStates().add(parrot);
		pattern1.getStates().add(velocity);
		pattern1.getInitialisers(velocity).add((data, context) -> data.setData(0.5, 0.5));
		pattern1.getInitialisers(position).add((data, context) -> data.setData(5, 5));

		Pattern pattern2 = new PatternImpl("pattern two");
		pattern2.getComposition().add(pattern1);
		pattern2.getStates().add(velocity);
		pattern2.getInitialisers(position).add((data, context) -> data.setData(12, 12));
		pattern2.getInitialisers(velocity).add((data, context) -> data
				.add(context.getSupercontext().getData(position).getSubtracted(matrices().doubles().vector2().setData(2, 2))));
		pattern2.getChildren().add(pattern1);

		pattern1.getInitialisers(parrot).add((data, context) -> {
			try {
				data.set("Child position: " + context.getSubcontext(pattern1).getData(position) + " num "
						+ context.getValue(numberVariable));
			} catch (IllegalArgumentException e) {
				data.set("Parent num: " + context.getSupercontext().getValue(numberVariable) + " num: "
						+ context.getValue(numberVariable));
			}
		});

		Pattern pattern3 = new PatternImpl("pattern three");
		pattern3.getVariables().add(numberVariable);
		pattern3.getStates().add(position);
		pattern3.getInitialisers(position).add((data, context) -> data
				.set(context.getSubcontext(pattern2, pattern1).getData(position).getMultiplied(3)));
		pattern3.getChildren().add(pattern2);

		pattern3.assemble(entities());

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
